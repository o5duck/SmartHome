package request;

public class SleepingRequest {
	private String master_dev_id;
	private String awake_time;
	private int temperature;
	
	public SleepingRequest(){}
	public SleepingRequest(String master_dev_id){this.master_dev_id = master_dev_id;}
	public SleepingRequest(String master_dev_id, String awake_time, int temperature) {
		super();
		this.master_dev_id = master_dev_id;
		this.awake_time = awake_time;
		this.temperature = temperature;
	}
	
	public String getMaster_dev_id() {
		return master_dev_id;
	}
	public void setMaster_dev_id(String master_dev_id) {
		this.master_dev_id = master_dev_id;
	}
	public String getAwake_time() {
		return awake_time;
	}
	public void setAwake_time(String awake_time) {
		this.awake_time = awake_time;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	
}
