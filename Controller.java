import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javazoom.jl.player.MP3Player;

public class Controller implements I_gameRule, I_loginRule{
	int gScore=0;
	int fail=0;
	ArrayList<MusicDTO> musicList = new ArrayList<MusicDTO>();
	MP3Player mp3 = new MP3Player();
	
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	
	int index = 0;
	public Controller() {
		musicList.add(new MusicDTO("C:\\Users\\smhrd\\Desktop\\JAVA\\miniP\\music\\yes.mp3"));
		musicList.add(new MusicDTO("C:\\Users\\smhrd\\Desktop\\JAVA\\miniP\\music\\no.mp3"));
	}
	
	public void play(int num) {

		// 현재 재생중인 음악이 있는지
		if (mp3.isPlaying()) {
			// 현재 재생중인 음악 정지
			mp3.stop();
		}

		// index가 가리키고 있는 위치의 음악을 재생
		mp3.play(musicList.get(num-1).getFilePath());
		
	}

	// 정지
		public void stop() {
			if(mp3.isPlaying()) {
				mp3.stop(); // 현재 음악 재생 정지
			}
			
		}
	
	@Override
	public void getConn() {

		try {
			// DB연결할 수 있는 외부 클래스 가져오기!
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 연결시 필요한 값 (url, id, pw)
			String url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String id1 = "campus_k_0417_5";
			String pw1 = "smhrd5";

			// DriverManager --> DB랑 연결 (실질적 연결)
			conn = DriverManager.getConnection(url, id1, pw1);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		// 3. DB 연결 해체( = 연결의 역순으로)
		try {

			if (rs != null) {
				rs.close();
			}
			if (pstm != null) {
				pstm.close();
			}
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean login(String id, String pw) {
		getConn();

		try {
			// ********** 쿼리문 작성 코드 **************
			String sql = "select * from user_info where ID = ? and PW = ?";
			pstm = conn.prepareStatement(sql); // 연결하겠다
			pstm.setString(1, id);
			pstm.setString(2, pw);

			// ********** 쿼리문 "실행!!!!" 해서 데이터 가져 오는 경우 **************
			rs = pstm.executeQuery();// 쿼리를 실행 시키겠다는 내용

			if (rs.next()) {// 처음엔 맨 위의 데이터 값을 가르킴(컬럼명)
				// rs.next를 써서 다음 데이터 값을 보겠다는 뜻
				// if만약 데이터가 있다면 출력문 실행하겠다
				id = rs.getString("id");
				System.out.println(id + "님 환영합니다.");
				return true;

			}
		} catch (SQLException e) {
			System.out.println("쿼리문 오류");
			e.printStackTrace();
		}
		close();
		return false;
	}

	@Override
	public int join(String id, String pw) {
		getConn();
		int result = 0;
		// (3)이름, 비밀번호, 닉네임 입력받아서 데이터를 추가
		try {
			String sql = "insert into user_info (id, pw) values(?,?)";

			// insert into member values
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, pw);

			// 데이터 넣는 쿼리문을 실행하겠다
			result = pstm.executeUpdate();
			// executeQuery(); << 데이터를 가져올 때 씀

		} catch (SQLException e) {
			System.out.println("쿼리문 오류");
			e.printStackTrace();
		}
		close();
		return result;
	}

	// 전체 랭킹 출력 기능
	@Override
	public ArrayList<RankDTO> rank(int choice) {

		ResultSet rs = null;
		String rid = null;
		int rscore = 0;
		String result = null;

		getConn();
		ArrayList<RankDTO> rd = new ArrayList<RankDTO>();
		if (choice == 1) {
			try {
				String sql = "select * from USER_INFO WHERE CSCORE IS NOT NULL order by CSCORE desc";
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();
				while (rs.next()) {
					rid = rs.getString(1);
					rscore = rs.getInt(3);
					rd.add(new RankDTO(rid, rscore));

				}
				return rd;

			} catch (SQLException e) {
				System.out.println("쿼리문 오류");
				e.printStackTrace();
			}
		} else if (choice == 2) {
			try {
				String sql = "select * from USER_INFO WHERE NSCORE IS NOT NULL order by NSCORE desc";
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();
				while (rs.next()) {
					rid = rs.getString(1);
					rscore = rs.getInt(4);
					rd.add(new RankDTO(rid, rscore));

				}
				return rd;

			} catch (SQLException e) {
				System.out.println("쿼리문 오류");
				e.printStackTrace();
			}
		} else {
			try {
				String sql = "select * from USER_INFO WHERE FSCORE IS NOT NULL order by FSCORE desc";
				pstm = conn.prepareStatement(sql);
				rs = pstm.executeQuery();
				while (rs.next()) {
					rid = rs.getString(1);
					rscore = rs.getInt(5);
					rd.add(new RankDTO(rid, rscore));

				}
				return rd;

			} catch (SQLException e) {
				System.out.println("쿼리문 오류");
				e.printStackTrace();
			}
		}
		close();

		return null;
	}

	@Override
	public int[] random() {
		Random rd = new Random();

		// 유형별 난이도별 숫자
		int tydi = 0;

		// 랜덤 숫자가 담길 변수
		ArrayList<Integer> rdArr = new ArrayList<>();


		// 2. 접속 후 실행하고 싶은 쿼리문 실행!

		// 유형별 난이도별 count로 총 개수 구하기 -> 랜덤() 안에 넣어야함

		try {
			String sql = "select count(*) from question_info where q_type = ? and difficulty = ?";

			pstm = conn.prepareStatement(sql);
			pstm.setString(1, "사자성어");
			pstm.setString(2, "상");
			rs = pstm.executeQuery();

			if (rs.next()) {
				tydi = rs.getInt(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


		int[] be = new int[tydi];

		for (int j = 0; j < tydi; j++) {
			be[j] = rd.nextInt(tydi) + 1;

			for (int i = 0; i < j; i++) {

				if (be[i] == be[j]) {

					j--;

					break;
				}

			}

		}

		// 배열 보내기
		return be;
	}

	@Override
public String getQuiz(int type, int difficulty, int Qnum) {
		
		String selectquiz = "";
		String sql="";
		try {
			sql = "SELECT QUESTION FROM QUESTION_INFO WHERE Q_TYPE = ? AND DIFFICULTY = ? AND QNUM = ?";
			pstm=conn.prepareStatement(sql);
			pstm.setString(1, conType(type));
			pstm.setString(2, conDiffi(difficulty));
			pstm.setInt(3, Qnum);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				selectquiz =  rs.getString("Question");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return selectquiz;
		
	}

	@Override
	public boolean checkAnswer(String ans) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String hint(int type, int difficulty, int Qnum) {

		String hint = "";
		String sql = "";
		try {
			sql = "SELECT HINT FROM QUESTION_INFO WHERE Q_TYPE = ? AND DIFFICULTY = ? AND QNUM = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, conType(type));
			pstm.setString(2, conDiffi(difficulty));
			pstm.setInt(3, Qnum);
			rs = pstm.executeQuery();
			if(rs.next()) {
				hint = rs.getString(1);
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return hint;

	}
	
	public String conType(int type) {
		String inputType = "";
		if (type == 1) {
			inputType = "상식";
		} else if (type == 2) {
			inputType = "넌센스";
		} else {
			inputType = "사자성어";
		}
		return inputType;
	}

	public String conDiffi(int difficulty) {
		String inputDifficulty = "";
		if (difficulty == 1) {
			inputDifficulty = "상";
		} else if (difficulty == 2) {
			inputDifficulty = "중";
		} else {
			inputDifficulty = "하";
		}
		return inputDifficulty;

	}
	

	@Override
	public int getScore(String id, int type, int score) {
		getConn();
		try {
			String sql = null;
			String temp = null;
			if (type == 1) { // 상식
				temp = "cScore";
				sql = "select cScore from user_info where id = ?";
			} else if (type == 2) { // 넌센스
				temp = "nScore";
				sql = "select nScore from user_info where id = ?";
			} else  { // 사자성어
				temp = "fScore";
				sql = "select fScore from user_info where id = ?";
			}

			// insert into member values
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);

			rs = pstm.executeQuery();

			int num = 0;
			if (rs.next()) {
				num = rs.getInt(temp);
			}

			if (num < gScore) { // 내 점수가 db점수보다 높을 경우
				sql = "update user_info set " + temp + "= ? where id = ?";
				pstm = conn.prepareStatement(sql);
				pstm.setInt(1, gScore);
				pstm.setString(2, id);
				pstm.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println("쿼리문 오류");
			e.printStackTrace();
		}
		close();

		return score;
	}

	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean checkAnswer(String ans, int type, int difficulty, int Qnum) {

		try {
			String correct = "SELECT ANSWER FROM QUESTION_INFO WHERE Q_TYPE = ? AND DIFFICULTY = ? AND QNUM = ?";
			pstm = conn.prepareStatement(correct);
			pstm.setString(1, conType(type));
			pstm.setString(2, conDiffi(difficulty));
			pstm.setInt(3, Qnum);
			rs = pstm.executeQuery();
			// ?에 들어갈 값이 문제에 나온 값이랑 똑같아야 함
			if (rs.next()) {
				if (ans.equals(rs.getString("Answer"))) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int addScore(int choice) {
		if(choice == 1) {
			gScore += 30;
		} else if(choice == 2) {
			gScore += 20;
		} else {
			gScore += 10;
		}
		return gScore;
	}
	
	public int minusScore(int choice) {
		if(choice == 1) {
			gScore -= 30;
		} else if(choice == 2) {
			gScore -= 20;
		} else {
			gScore -= 10;
		}
		fail++;
		return gScore;
	}

	public void setGScore(int gScore) {
		this.gScore = gScore;
	}
	
	public int getGScore() {
		return gScore;
	}



}
