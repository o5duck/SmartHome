package request;

public class DeviceRequest {
	private String user_id;
	private String dev_id;
	private String dev_password;
	private String sub_id;
	private String sub_bd;
	
	public DeviceRequest(){}
	public DeviceRequest(String user_id, String dev_id, String dev_password, String sub_id, String sub_bd) {
		super();
		this.user_id = user_id;
		this.dev_id = dev_id;
		this.dev_password = dev_password;
		this.sub_id = sub_id;
		this.sub_bd = sub_bd;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getDev_id() {
		return dev_id;
	}
	public void setDev_id(String dev_id) {
		this.dev_id = dev_id;
	}
	public String getDev_password() {
		return dev_password;
	}
	public void setDev_password(String dev_password) {
		this.dev_password = dev_password;
	}
	public String getSub_id() {
		return sub_id;
	}
	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}
	public String getSub_bd() {
		return sub_bd;
	}
	public void setSub_bd(String sub_bd) {
		this.sub_bd = sub_bd;
	}
	
	
}
