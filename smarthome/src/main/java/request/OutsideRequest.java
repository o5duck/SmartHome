package request;

public class OutsideRequest {
	private String master_dev_id;
	private String auto_lighting_time;
	private String auto_lighting_room_id;
	
	public OutsideRequest(String master_dev_id, String auto_lighting_time, String auto_lighting_room_id) {
		super();
		this.master_dev_id = master_dev_id;
		this.auto_lighting_time = auto_lighting_time;
		this.auto_lighting_room_id = auto_lighting_room_id;
	}
	public OutsideRequest() {}
	
	public String getMaster_dev_id() {
		return master_dev_id;
	}
	public void setMaster_dev_id(String master_dev_id) {
		this.master_dev_id = master_dev_id;
	}
	public String getAuto_lighting_time() {
		return auto_lighting_time;
	}
	public void setAuto_lighting_time(String auto_lighting_time) {
		this.auto_lighting_time = auto_lighting_time;
	}
	public String getAuto_lighting_room_id() {
		return auto_lighting_room_id;
	}
	public void setAuto_lighting_room_id(String auto_lighting_room_id) {
		this.auto_lighting_room_id = auto_lighting_room_id;
	}
}
