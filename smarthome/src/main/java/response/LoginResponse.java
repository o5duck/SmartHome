package response;

public class LoginResponse {
	private String user_name;
	private String user_id;
	private String master_dev_name;
	private String sub_dev_name;
	//private boolean isSuccess;

	public LoginResponse(){}
	//public LoginResponse(boolean isSuccess){ this.isSuccess = isSuccess; }
	public LoginResponse(String user_name, String user_id, String master_dev_name, String sub_dev_name){
		this.user_name = user_name;
		this.user_id = user_id;
		this.master_dev_name = master_dev_name;
		this.sub_dev_name = sub_dev_name;
		//this.isSuccess = true;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getMaster_dev_name() {
		return master_dev_name;
	}
	public void setMaster_dev_name(String master_dev_name) {
		this.master_dev_name = master_dev_name;
	}
	public String getSub_dev_name() {
		return sub_dev_name;
	}
	public void setSub_dev_name(String sub_dev_name) {
		this.sub_dev_name = sub_dev_name;
	}	
	/*public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}*/
	
	
}
