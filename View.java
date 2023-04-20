import java.util.ArrayList;
import java.util.Scanner;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class View {
	
	static final int BLACK = 0x0;
	static final int DARKBLUE = 0x1;
	static final int DARKGREEN = 0x2;
	static final int DARKCYAN = 0x3;
	static final int DARKRED = 0x4;
	static final int DARKMAGENTA = 0x5;
	static final int DARKYELLOW = 0x6;
	static final int GRAY = 0x7;
	static final int DARKGRAY = 0x8;
	static final int BLUE = 0x9;
	static final int GREEN = 0xA;
	static final int CYAN = 0xB;
	static final int RED = 0xC;
	static final int MAGENTA = 0xD;
	static final int YELLOW = 0xE;
	static final int WHITE = 0xF;
	static final int STD_OUTPUT_HANDLE = -11;


	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Controller c = new Controller();
		Timer ti = new Timer();
		Kernel32 lib = Native.load("kernel32", Kernel32.class);
		
		int choice = 0;

		String id = null;
		String pw = null;

		c.getConn();
		
		lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), YELLOW);
		try {
			System.out.println("                                                                                                                                                       ");
			Thread.sleep(30);
			System.out.println("    ,o888888o.      8 8888      88  8 8888  8888888888',8888'              d888888o.   8 8888        8     ,o888888o.     `8.`888b                 ,8' ");
			Thread.sleep(30);
			System.out.println(" . 8888     `88.    8 8888      88  8 8888         ,8',8888'             .`8888:' `88. 8 8888        8  . 8888     `88.    `8.`888b               ,8'  ");
			Thread.sleep(30);
			System.out.println(",8 8888       `8b   8 8888      88  8 8888        ,8',8888'              8.`8888.   Y8 8 8888        8 ,8 8888       `8b    `8.`888b             ,8'   ");
			Thread.sleep(30);
			System.out.println("88 8888        `8b  8 8888      88  8 8888       ,8',8888'               `8.`8888.     8 8888        8 88 8888        `8b    `8.`888b     .b    ,8'    ");
			Thread.sleep(30);
			System.out.println("88 8888         88  8 8888      88  8 8888      ,8',8888'                 `8.`8888.    8 8888        8 88 8888         88     `8.`888b    88b  ,8'     ");
			Thread.sleep(30);
			System.out.println("88 8888     `8. 88  8 8888      88  8 8888     ,8',8888'                   `8.`8888.   8 8888        8 88 8888         88      `8.`888b .`888b,8'      ");
			Thread.sleep(30);
			System.out.println("88 8888      `8,8P  8 8888      88  8 8888    ,8',8888'                     `8.`8888.  8 8888888888888 88 8888        ,8P       `8.`888b8.`8888'       ");
			Thread.sleep(30);
			System.out.println("`8 8888       ;8P   ` 8888     ,8P  8 8888   ,8',8888'                  8b   `8.`8888. 8 8888        8 `8 8888       ,8P         `8.`888`8.`88'        ");
			Thread.sleep(30);
			System.out.println(" ` 8888     ,88'8.    8888   ,d8P   8 8888  ,8',8888'                   `8b.  ;8.`8888 8 8888        8  ` 8888     ,88'           `8.`8' `8,`'         ");
			Thread.sleep(30);
			System.out.println("    `8888888P'  `8.    `Y88888P'    8 8888 ,8',8888888888888             `Y8888P ,88P' 8 8888        8     `8888888P'              `8.`   `8'          ");
			Thread.sleep(30);
			System.out.println("");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), WHITE);

		while (true) {
			ti = new Timer();
			
			
			System.out.println("┍━━━━━━━━━━━━━━━━━━━━━♬　♪　♬　♪　♬━━━━━━━━━━━━━━━━━━━━━━┑");
			System.out.println("├━♬　♪　[1]회원가입 [2]로그인 [3]랭킹조회 [4]종료 　♪　♬━━┤");
			System.out.println("┕━━━━━━━━━━━━━━━━━━━━━♬　♪　♬　♪　♬━━━━━━━━━━━━━━━━━━━━━━┙");
			System.out.print(">> ");
			choice = sc.nextInt();
			
			if (choice == 1) { // 회원가입
				System.out.println("=============회원가입=============");
				System.out.print("ID 입력 : ");
				id = sc.next();
				System.out.print("PW 입력 : ");
				pw = sc.next();
				
				if(c.join(id, pw) > 0) {
					System.out.println("회원 가입 성공");					
				} else {
					System.out.println("회원 가입 실패");
				}

			} else if (choice == 2) { // 로그인
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
				sc.nextLine();
				int[] randNum = c.random();
				c.setGScore(0);
				c.getConn();
				
				ti.start();
				for (int i = 0; i < c.random().length; i++) { // 게임 시작
					if (ti.getState() == Thread.State.TERMINATED) {
						lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), YELLOW);
						System.out.println("\r\n"
								+ " ::::::::      :::     ::::    ::::  ::::::::::  ::::::::  :::     ::: :::::::::: :::::::::  \r\n"
								+ ":+:    :+:   :+: :+:   +:+:+: :+:+:+ :+:        :+:    :+: :+:     :+: :+:        :+:    :+: \r\n"
								+ "+:+         +:+   +:+  +:+ +:+:+ +:+ +:+        +:+    +:+ +:+     +:+ +:+        +:+    +:+ \r\n"
								+ ":#:        +#++:++#++: +#+  +:+  +#+ +#++:++#   +#+    +:+ +#+     +:+ +#++:++#   +#++:++#:  \r\n"
								+ "+#+   +#+# +#+     +#+ +#+       +#+ +#+        +#+    +#+  +#+   +#+  +#+        +#+    +#+ \r\n"
								+ "#+#    #+# #+#     #+# #+#       #+# #+#        #+#    #+#   #+#+#+#   #+#        #+#    #+# \r\n"
								+ " ########  ###     ### ###       ### ##########  ########      ###     ########## ###    ### ");
						lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), WHITE);
						break;
					}
					
					String ques = c.getQuiz(choice, difficulty, randNum[i]);
					System.out.println("남은 시간 : " + ti.getSec());
					System.out.println(i + 1 + "번 " + ques);
					System.out.print("답 : ");
					String ans = sc.next();
					
					if (c.checkAnswer(ans, choice, difficulty, randNum[i])) { // 정답 확인
						c.play(1);
						lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), GREEN);
						System.out.println("  ______");
						System.out.println(" /  __  \\");
						System.out.println("|  |  |  |");
						System.out.println("|  |  |  |");
						System.out.println("|  `--'  |");
						System.out.println(" \\______/ ");
						lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), WHITE);
						c.addScore(difficulty);
						System.out.println();
						if (ti.getState() == Thread.State.TERMINATED) {
							lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), YELLOW);
							System.out.println("\r\n"
									+ " ::::::::      :::     ::::    ::::  ::::::::::  ::::::::  :::     ::: :::::::::: :::::::::  \r\n"
									+ ":+:    :+:   :+: :+:   +:+:+: :+:+:+ :+:        :+:    :+: :+:     :+: :+:        :+:    :+: \r\n"
									+ "+:+         +:+   +:+  +:+ +:+:+ +:+ +:+        +:+    +:+ +:+     +:+ +:+        +:+    +:+ \r\n"
									+ ":#:        +#++:++#++: +#+  +:+  +#+ +#++:++#   +#+    +:+ +#+     +:+ +#++:++#   +#++:++#:  \r\n"
									+ "+#+   +#+# +#+     +#+ +#+       +#+ +#+        +#+    +#+  +#+   +#+  +#+        +#+    +#+ \r\n"
									+ "#+#    #+# #+#     #+# #+#       #+# #+#        #+#    #+#   #+#+#+#   #+#        #+#    #+# \r\n"
									+ " ########  ###     ### ###       ### ##########  ########      ###     ########## ###    ### ");
							lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), WHITE);
							break;
						}						
						continue;
					} else {	// 틀림
						lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), RED);
						c.play(2);
						System.out.println("__   __");
						System.out.println("\\ \\ / /");
						System.out.println(" \\ V / ");
						System.out.println(" /   \\");
						System.out.println("/ /^\\ \\");
						System.out.println("\\/   \\/");
						lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), WHITE);
						
						System.out.println("남은 시간 : " + ti.getSec());
						System.out.println(ques); // 문제 다시 출력
						System.out.println("힌트 : " + c.hint(choice, difficulty, randNum[i]));
						System.out.print("답 : ");
						if (ti.getState() == Thread.State.TERMINATED) {
							lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), YELLOW);
							System.out.println("\r\n"
									+ " ::::::::      :::     ::::    ::::  ::::::::::  ::::::::  :::     ::: :::::::::: :::::::::  \r\n"
									+ ":+:    :+:   :+: :+:   +:+:+: :+:+:+ :+:        :+:    :+: :+:     :+: :+:        :+:    :+: \r\n"
									+ "+:+         +:+   +:+  +:+ +:+:+ +:+ +:+        +:+    +:+ +:+     +:+ +:+        +:+    +:+ \r\n"
									+ ":#:        +#++:++#++: +#+  +:+  +#+ +#++:++#   +#+    +:+ +#+     +:+ +#++:++#   +#++:++#:  \r\n"
									+ "+#+   +#+# +#+     +#+ +#+       +#+ +#+        +#+    +#+  +#+   +#+  +#+        +#+    +#+ \r\n"
									+ "#+#    #+# #+#     #+# #+#       #+# #+#        #+#    #+#   #+#+#+#   #+#        #+#    #+# \r\n"
									+ " ########  ###     ### ###       ### ##########  ########      ###     ########## ###    ### ");
							lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), WHITE);
							break;
						}
						ans = sc.next();
						
						if (c.checkAnswer(ans, choice, difficulty, randNum[i])) {
							c.play(1);
							lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), GREEN);
							System.out.println("정답입니다.");
							System.out.println("  ______");
							System.out.println(" /  __  \\");
							System.out.println("|  |  |  |");
							System.out.println("|  |  |  |");
							System.out.println("|  `--'  |");
							System.out.println(" \\______/ ");
							lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), WHITE);
							if (ti.getState() == Thread.State.TERMINATED) {
								lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), YELLOW);
								System.out.println("\r\n"
										+ " ::::::::      :::     ::::    ::::  ::::::::::  ::::::::  :::     ::: :::::::::: :::::::::  \r\n"
										+ ":+:    :+:   :+: :+:   +:+:+: :+:+:+ :+:        :+:    :+: :+:     :+: :+:        :+:    :+: \r\n"
										+ "+:+         +:+   +:+  +:+ +:+:+ +:+ +:+        +:+    +:+ +:+     +:+ +:+        +:+    +:+ \r\n"
										+ ":#:        +#++:++#++: +#+  +:+  +#+ +#++:++#   +#+    +:+ +#+     +:+ +#++:++#   +#++:++#:  \r\n"
										+ "+#+   +#+# +#+     +#+ +#+       +#+ +#+        +#+    +#+  +#+   +#+  +#+        +#+    +#+ \r\n"
										+ "#+#    #+# #+#     #+# #+#       #+# #+#        #+#    #+#   #+#+#+#   #+#        #+#    #+# \r\n"
										+ " ########  ###     ### ###       ### ##########  ########      ###     ########## ###    ### ");
								lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), WHITE);
								break;
							}
							
						} else {
							System.out.println("틀렸습니다. 다음 문제로");
							c.play(2);
							lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), RED);
							System.out.println("__   __");
							System.out.println("\\ \\ / /");
							System.out.println(" \\ V / ");
							System.out.println(" /   \\");
							System.out.println("/ /^\\ \\");
							System.out.println("\\/   \\/");
							lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), WHITE);
							
							c.minusScore(difficulty);
							if (ti.getState() == Thread.State.TERMINATED) {
								lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), YELLOW);
								System.out.println("\r\n"
										+ " ::::::::      :::     ::::    ::::  ::::::::::  ::::::::  :::     ::: :::::::::: :::::::::  \r\n"
										+ ":+:    :+:   :+: :+:   +:+:+: :+:+:+ :+:        :+:    :+: :+:     :+: :+:        :+:    :+: \r\n"
										+ "+:+         +:+   +:+  +:+ +:+:+ +:+ +:+        +:+    +:+ +:+     +:+ +:+        +:+    +:+ \r\n"
										+ ":#:        +#++:++#++: +#+  +:+  +#+ +#++:++#   +#+    +:+ +#+     +:+ +#++:++#   +#++:++#:  \r\n"
										+ "+#+   +#+# +#+     +#+ +#+       +#+ +#+        +#+    +#+  +#+   +#+  +#+        +#+    +#+ \r\n"
										+ "#+#    #+# #+#     #+# #+#       #+# #+#        #+#    #+#   #+#+#+#   #+#        #+#    #+# \r\n"
										+ " ########  ###     ### ###       ### ##########  ########      ###     ########## ###    ### ");
								lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), WHITE);
								break;
							}
						}
						System.out.println();

					}
					
					if (c.getGScore() < 0 || (ti.getState() == Thread.State.TERMINATED)) {
						lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), YELLOW);
						System.out.println("\r\n"
								+ " ::::::::      :::     ::::    ::::  ::::::::::  ::::::::  :::     ::: :::::::::: :::::::::  \r\n"
								+ ":+:    :+:   :+: :+:   +:+:+: :+:+:+ :+:        :+:    :+: :+:     :+: :+:        :+:    :+: \r\n"
								+ "+:+         +:+   +:+  +:+ +:+:+ +:+ +:+        +:+    +:+ +:+     +:+ +:+        +:+    +:+ \r\n"
								+ ":#:        +#++:++#++: +#+  +:+  +#+ +#++:++#   +#+    +:+ +#+     +:+ +#++:++#   +#++:++#:  \r\n"
								+ "+#+   +#+# +#+     +#+ +#+       +#+ +#+        +#+    +#+  +#+   +#+  +#+        +#+    +#+ \r\n"
								+ "#+#    #+# #+#     #+# #+#       #+# #+#        #+#    #+#   #+#+#+#   #+#        #+#    #+# \r\n"
								+ " ########  ###     ### ###       ### ##########  ########      ###     ########## ###    ### ");
						lib.SetConsoleTextAttribute(lib.GetStdHandle(STD_OUTPUT_HANDLE), WHITE);
						ti.setStop(true);
						break;
					}
					
				}
				

				
				System.out.println("점수 : " + c.getGScore());
				System.out.println("틀린개수 : " + c.fail);
				c.getScore(id, choice, c.getGScore());

				System.out.println();
			} else if (choice == 3) { // 랭킹 조회
				System.out.println("=============문제 유형 선택=============");
				System.out.print("[1] 상식 [2] 넌센스 [3] 사자성어 >> ");
				choice = sc.nextInt();
				ArrayList<RankDTO> rd = c.rank(choice);
				
				int preScore = -1;
				int rankNum = 1;
				for (int i = 0; i < rd.size(); i++) {

					if ((rd.get(i).getScore()) == preScore) {
						System.out.println(rankNum + "등 ID : " + rd.get(i).getId() + " 점수 : " + rd.get(i).getScore());
						continue;
					} else {

						rankNum = i + 1;
						System.out.println(rankNum + "등 ID : " + rd.get(i).getId() + " 점수 : " + rd.get(i).getScore());
					}
					preScore = rd.get(i).getScore();

				}
				
				} else {
					System.out.println("종료");
					break;
				}
		}//while

	}// main
	
	interface Kernel32 extends Library {
		
		boolean SetConsoleTextAttribute(int h_ConsoleOutput, int u16_Attributes);

		int GetStdHandle(int u32_Device);

	}

}// class
