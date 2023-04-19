package 미니프로젝트;

import java.util.Scanner;

public class View {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int choice = 0;

		String id = null;
		String pw = null;

		Controller c = new Controller();

		c.getConn();

		while (true) {
			System.out.print("[1]회원가입 [2]로그인 [3]랭킹조회 [4]종료");
			choice = sc.nextInt();
			if (choice == 1) {
				System.out.print("");

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
				c.getQuiz(choice, difficulty);

			} else if (choice == 3) {

			} else {
				System.out.println("시스템 종료");
				break;
			}
		}

	}

}
