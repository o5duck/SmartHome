package model;

public class UserDev {
	private String user_id;
	private String master_dev_id;
	private String sub_dev_id;
	
	public UserDev(){}
	public UserDev(String user_id, String master_dev_id, String sub_dev_id) {
		super();
		this.user_id = user_id;
		this.master_dev_id = master_dev_id;
		this.sub_dev_id = sub_dev_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getMaster_dev_id() {
		return master_dev_id;
	}
	public void setMaster_dev_id(String master_dev_id) {
		this.master_dev_id = master_dev_id;
	}
	public String getSub_dev_id() {
		return sub_dev_id;
	}
	public void setSub_dev_id(String sub_dev_id) {
		this.sub_dev_id = sub_dev_id;
	}
	
	
}
