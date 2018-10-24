package model;

public class MasterDevice {
	private String master_dev_id;
	private String master_dev_ip;
	private String master_dev_password;
	
	public MasterDevice(){}
	public MasterDevice(String master_dev_id, String master_dev_ip, String master_dev_password) {
		super();
		this.master_dev_id = master_dev_id;
		this.master_dev_ip = master_dev_ip;
		this.master_dev_password = master_dev_password;
	}
	
	public String getMaster_dev_id() {
		return master_dev_id;
	}
	public void setMaster_dev_id(String master_dev_id) {
		this.master_dev_id = master_dev_id;
	}
	public String getMaster_dev_ip() {
		return master_dev_ip;
	}
	public void setMaster_dev_ip(String master_dev_ip) {
		this.master_dev_ip = master_dev_ip;
	}
	public String getMaster_dev_password() {
		return master_dev_password;
	}
	public void setMaster_dev_password(String master_dev_password) {
		this.master_dev_password = master_dev_password;
	}
	
}
