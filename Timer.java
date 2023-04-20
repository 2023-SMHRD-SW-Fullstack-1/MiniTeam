
public class Timer extends Thread{
	
	private int sec=20;
	boolean isStop = false;
	
	@Override
	public void run() {
		while(!isStop) {
			sec--;
//			if(sec%2==0) {
//				System.out.println(sec+"초");
//			}
			
			if(sec==0) {				
				break;
			}
			
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}//while
		
		
	}//run
	
	public int getSec() {
		return sec;
	}
	
	public void setStop(boolean isStop) {
		this.isStop = isStop;
	}

}//class
