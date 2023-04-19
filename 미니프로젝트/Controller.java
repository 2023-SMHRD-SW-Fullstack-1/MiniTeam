package 미니프로젝트;

public class Controller implements I_gameRule, I_loginRule{

	Model m = new Model();
	@Override
	public void getConn() {
		m.getConn();
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean login(String id, String pw) {
		return m.login(id, pw);
	}

	@Override
	public int join(String id, String pw) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int rank() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int random() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getQuiz(int type, int difficulty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkAnswer(String ans) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getHint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getScore() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getTime() {
		// TODO Auto-generated method stub
		return 0;
	}



}
