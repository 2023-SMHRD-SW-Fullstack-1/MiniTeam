package 미니프로젝트;

public interface I_gameRule {

	// 난수를 생성하는 기능
	public abstract int random();
	


	// 문제를 제출할 수 있는 기능
	public abstract String getQuiz(int type, int difficulty);

	// 정답을 체크할 수 있는 기능
	public abstract boolean checkAnswer(String ans);
	
	//힌트를 제출할 수 있는 기능
	public abstract String getHint();
	
	//게임 점수 출력 기능
	public abstract int getScore();
	
	//제한 시간 출력 기능
	public abstract int getTime();
	

}
