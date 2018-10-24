package request;

public class CrimeSearchRequest {
	private String master_dev_id;
	private String _year;
	private String _month;
	private String _date;
	private String _hour1;
	private String _hour2;
	
	public CrimeSearchRequest(){}
	public CrimeSearchRequest(String master_dev_id, String _year, String _month, String _date, String _hour1,
			String _hour2) {
		super();
		this.master_dev_id = master_dev_id;
		this._year = _year;
		this._month = _month;
		this._date = _date;
		this._hour1 = _hour1;
		this._hour2 = _hour2;
	}
	
	public String getMaster_dev_id() {
		return master_dev_id;
	}
	public void setMaster_dev_id(String master_dev_id) {
		this.master_dev_id = master_dev_id;
	}
	public String get_year() {
		return _year;
	}
	public void set_year(String _year) {
		this._year = _year;
	}
	public String get_month() {
		return _month;
	}
	public void set_month(String _month) {
		this._month = _month;
	}
	public String get_date() {
		return _date;
	}
	public void set_date(String _date) {
		this._date = _date;
	}
	public String get_hour1() {
		return _hour1;
	}
	public void set_hour1(String _hour1) {
		this._hour1 = _hour1;
	}
	public String get_hour2() {
		return _hour2;
	}
	public void set_hour2(String _hour2) {
		this._hour2 = _hour2;
	}
	
}
