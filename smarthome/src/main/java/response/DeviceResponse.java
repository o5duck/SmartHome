package response;

public class DeviceResponse {
	private String user_id;
	private String dev_id;
	private String sub_id;
	private String msg;
	
	public DeviceResponse(){}
	public DeviceResponse(String user_id, String dev_id, String sub_id, String msg) {
		this.user_id = user_id;
		this.dev_id = dev_id;
		this.sub_id = sub_id;
		this.msg = msg;
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
	public String getSub_id() {
		return sub_id;
	}
	public void setSub_id(String sub_id) {
		this.sub_id = sub_id;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
