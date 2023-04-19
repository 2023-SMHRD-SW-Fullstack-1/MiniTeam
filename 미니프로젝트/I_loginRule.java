package 미니프로젝트;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface I_loginRule {

	
	Connection conn = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	
	//db 연결
	public abstract void getConn();
	
	
	//db연결 해제
	public abstract void close();
	
	//로그인 기능(회원정보 대조)
	public abstract boolean login(String id, String pw);
	
	//회원가입 기능
	public abstract int join(String id,String pw);
	
	//전체 랭킹 조회 기능
	public abstract int rank();

}
