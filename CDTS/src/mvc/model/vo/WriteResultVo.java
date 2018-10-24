package mvc.model.vo;

public class WriteResultVo {
	private int docId;
	private String msg;
	public WriteResultVo(int docId, String msg) {
		super();
		this.docId = docId;
		this.msg = msg;
	}
	public int getDocId() {
		return docId;
	}
	public String getMsg() {
		return msg;
	}
}
