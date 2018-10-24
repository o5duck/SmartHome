package request;

public class CrimeRequest {
	private String master_dev_id;
	private String photo_path;
	public CrimeRequest(){}
	public CrimeRequest(String master_dev_id, String photo_path){
		this.master_dev_id = master_dev_id;
		this.photo_path = photo_path;
	}
	public String getMaster_dev_id() {
		return master_dev_id;
	}
	public void setMaster_dev_id(String master_dev_id) {
		this.master_dev_id = master_dev_id;
	}
	public String getPhoto_path() {
		return photo_path;
	}
	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}
	
}
