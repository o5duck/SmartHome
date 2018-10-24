package response;

import java.util.List;

public class CrimeSearchResponse {
	private List<String> images;
	private String msg;
	
	public CrimeSearchResponse(){}
	public CrimeSearchResponse(List<String> images, String msg) {
		super();
		this.images = images;
		this.msg = msg;
	}
	
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
