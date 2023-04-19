package 미니프로젝트;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model {

	// db연결 변수
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;

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
			System.out.println("연결 성공");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

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

	public boolean login(String id, String pw) {
		getConn();

		try {
			// ********** 쿼리문 작성 코드 **************
			String sql = "select nick from member where ID = ? and PW = ?";
			pstm = conn.prepareStatement(sql); // 연결하겠다
			pstm.setString(1, id);
			pstm.setString(2, pw);

			// ********** 쿼리문 "실행!!!!" 해서 데이터 가져 오는 경우 **************
			rs = pstm.executeQuery();// 쿼리를 실행 시키겠다는 내용

			if (rs.next()) {// 처음엔 맨 위의 데이터 값을 가르킴(컬럼명)
				// rs.next를 써서 다음 데이터 값을 보겠다는 뜻
				// if만약 데이터가 있다면 출력문 실행하겠다
				id = rs.getString("id");
				System.out.print(id + "님 환영합니다.");
				return true;

			}
		} catch (SQLException e) {
			System.out.println("쿼리문 오류");
			e.printStackTrace();
		}
		close();
		return false;
	}

	public int join(String id, String pw, String nick) {
		getConn();
		int result = 0;
		// (3)이름, 비밀번호, 닉네임 입력받아서 데이터를 추가
		try {
			String sql = "insert into member values(?,?,?)";

			// insert into member values
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, pw);
			pstm.setString(3, nick);

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

	public ArrayList<UserDTO> userList() {
		getConn();
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		try {
			// ********** 쿼리문 작성 코드 **************
			String sql = "select * from USER_INFO "; // 쿼리문 작성 하고
			pstm = conn.prepareStatement(sql); // 그것을 연결하겠다

			// ********** 쿼리문 "실행!!!!" 해서 데이터 가져 오는 경우 **************
			rs = pstm.executeQuery();// 쿼리를 실행 시키겠다는 내용

			while (rs.next()) {
				String id = rs.getString(1);
				String pw = rs.getString(2);

				UserDTO dto = new UserDTO(id, pw);
				userList.add(dto);

			}
		} catch (SQLException e) {
			System.out.println("쿼리문 오류");
			e.printStackTrace();
		}
		close();
		return userList;
	}

	// 전체 랭킹 출력 기능
	public int rank(int choice) {
		String sql = "select * from USER_INFO "; // 쿼리문 작성 하고
		try {
			pstm = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (choice == 1) {

		} else if (choice == 2) {

		} else {

		}

		return 0;
	}

	// 최고 점수를 DB에 입력 하는 기능

}
