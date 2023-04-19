package 미니프로젝트;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class View {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int choice = 0;

//		Connection conn = null;
//		PreparedStatement pstm = null;
//		ResultSet rs = null;

		String id = null;
		String pw = null;

		Model m = new Model();

		Controller c = new Controller();

		c.getConn();

		while (true) {
			System.out.print("[1]회원가입 [2]로그인 [3]랭킹조회 [4]종료");
			choice = sc.nextInt();
			if (choice == 1) {
				System.out.print("ID 입력 : ");
				id = sc.next();
				System.out.print("PW 입력 : ");
				pw = sc.next();
				m.join(id, pw);

			} else if (choice == 2) {
				System.out.print("ID 입력 : ");
				id = sc.next();
				System.out.print("PW 입력 : ");
				pw = sc.next();
				if (!c.login(id, pw)) {
					continue;
				}
				System.out.println("=============문제 유형 선택=============");
				System.out.print("[1] 상식 [2] 넌센스 [3] 사자성어 >> ");
				choice = sc.nextInt();

				System.out.println("=============문제 난이도 선택=============");
				System.out.print("[1] 상 [2] 중 [3] 하 >> ");
				int difficulty = sc.nextInt();
				int[] randNum = c.random();
				c.setGScore(0);
				c.getConn();
				for(int i=0; i<c.random().length; i++){
					String ques = c.getQuiz(choice, difficulty,randNum[i]);
					System.out.println(i+1 + "번 " + ques);
					System.out.print("답 : ");
					String ans = sc.next();
					if(c.checkAnswer(ans, choice, difficulty, randNum[i])) {
						System.out.println("정답입니다.");
						c.addScore(difficulty);
						continue;
					} else {
						System.out.println(ques);
						System.out.println("힌트 : " + c.hint(choice, difficulty, randNum[i]));
						System.out.print("답 : ");
						ans = sc.next();
						if(c.checkAnswer(ans, choice, difficulty, randNum[i])) {
							System.out.println("정답입니다.");
						} else {
							System.out.println("틀렸습니다. 다음 문제로");
							c.minusScore(difficulty);
						}				
						
					}
					if(c.getGScore()<0) {
						System.out.println("게임 오버"); break;
					}
					System.out.println();
					
					
				}
				System.out.println("점수 : " + c.getGScore());
				System.out.println("틀린개수 : " + c.fail);
				c.getScore(id, choice, c.getGScore());
				
				System.out.println();
			} else if (choice == 3) {
				System.out.println("=============문제 유형 선택=============");
				System.out.print("[1] 상식 [2] 넌센스 [3] 사자성어 >> ");
				choice = sc.nextInt();
				ArrayList<RankDTO> rd = c.rank(choice);
				int rank = 1;
				
				int preScore = -1;
				int rankNum = 1;
				for( int i = 0; i < rd.size(); i++){
					
//					for(int j=0; j<i; j++) {
						if((rd.get(i).getScore()) == preScore) {
							System.out.println(rankNum + "등 ID : " + rd.get(i).getId() + " 점수 : " + rd.get(i).getScore());
							continue;
						} else {
							
							rankNum = i+1;
							System.out.println(rankNum + "등 ID : " + rd.get(i).getId() + " 점수 : " + rd.get(i).getScore());
						}
						preScore = rd.get(i).getScore();
//					}
									
					
					
					
//					rd1.add(new RankDTO(rd.get(i).getId(), rd.get(i).getScore()));
					
//					if(rd.get(i).getScore()!=rd1.get(0).getScore()) {
//						rank++;
//						rd1.set(0,(rd.get(i).getId(),rd.get(0).getScore()));
//					}
					
//					System.out.println(i + 1 + "등 ID : " + rd.get(i).getId() + " 점수 : " + rd.get(i).getScore());
				}
			} else {
				System.out.println("시스템 종료");
				break;
			}
		}

	}

}
