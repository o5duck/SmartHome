package model;

public class SubDevice {
	private String sub_dev_id;
	private String sub_dev_bd_addr;
	
	public SubDevice(){}
	public SubDevice(String sub_dev_id, String sub_dev_bd_addr) {
		super();
		this.sub_dev_id = sub_dev_id;
		this.sub_dev_bd_addr = sub_dev_bd_addr;
	}
	
	public String getSub_dev_id() {
		return sub_dev_id;
	}
	public void setSub_dev_id(String sub_dev_id) {
		this.sub_dev_id = sub_dev_id;
	}
	public String getSub_dev_bd_addr() {
		return sub_dev_bd_addr;
	}
	public void setSub_dev_bd_addr(String sub_dev_bd_addr) {
		this.sub_dev_bd_addr = sub_dev_bd_addr;
	}
	
}
