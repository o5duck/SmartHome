package mvc.model.vo;

public class ApprovalDesVo {
	private int docId;
	private String[] nowDes;
	private String[] nextDes;
	private int step;
	
	public ApprovalDesVo(int docId, String[] nowDes, String[] nextDes, int step) {
		this.docId = docId;
		this.nowDes = nowDes;
		this.nextDes = nextDes;
		this.step = step;
	}
	public int getDocId() {
		return docId;
	}
	public String[] getNowDes() {
		return nowDes;
	}
	public String[] getNextDes() {
		return nextDes;
	}
	public int getStep() {
		return step;
	}

}
