
public class MusicDTO {
	
	private String title;
	private String filePath;
	
	public MusicDTO(String title, String filePath) {
		this.title = title;
		this.filePath = filePath;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
}
