package response;

public class SleepingResponse {
	private String awake_time;
	private int temperature;
	private String msg;
	
	public SleepingResponse(){}
	public SleepingResponse(String msg){this.msg = msg;}
	public SleepingResponse(String awake_time, int temperature) {
		super();
		this.awake_time = awake_time;
		this.temperature = temperature;
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
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
