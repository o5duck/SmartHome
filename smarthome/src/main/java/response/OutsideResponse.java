package response;

public class OutsideResponse {
	private String auto_lighting_room_id;
	private String auto_lighting_time;
	private String msg;
	
	public OutsideResponse(String auto_lighting_room_id, String auto_lighting_time) {
		this.auto_lighting_room_id = auto_lighting_room_id;
		this.auto_lighting_time = auto_lighting_time;
	}
	public OutsideResponse(String msg) {
		this.msg = msg;
	}
	public OutsideResponse() {}
	public String getAuto_lighting_room_id() {
		return auto_lighting_room_id;
	}
	public void setAuto_lighting_room_id(String auto_lighting_room_id) {
		this.auto_lighting_room_id = auto_lighting_room_id;
	}
	public String getAuto_lighting_time() {
		return auto_lighting_time;
	}
	public void setAuto_lighting_time(String auto_lighting_time) {
		this.auto_lighting_time = auto_lighting_time;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
