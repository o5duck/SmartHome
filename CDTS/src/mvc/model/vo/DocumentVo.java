package mvc.model.vo;

import java.util.Date;

public class DocumentVo {
	private String title;
	private String content;
	private Date c_time;
	private String attached;
	private String gen_user;
	private String des1;
	private String des2;
	private String des3;
	private String doc_type;
	private String boardId;
	
	public DocumentVo(String title, String content, Date c_time, String attached, String gen_user, String des1,
			String des2, String des3, String doc_type) {
		this.title = title;
		this.content = content;
		this.c_time = c_time;
		this.attached = attached;
		this.gen_user = gen_user;
		this.des1 = des1;
		this.des2 = des2;
		this.des3 = des3;
		this.doc_type = doc_type;
		this.boardId = "";
	}
	public void setBoardId(String boardId){
		this.boardId = boardId;
	}
	
	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public Date getC_time() {
		return c_time;
	}

	public String getAttached() {
		return attached;
	}

	public String getGen_user() {
		return gen_user;
	}

	public String getDes1() {
		return des1;
	}

	public String getDes2() {
		return des2;
	}

	public String getDes3() {
		return des3;
	}

	public String getDoc_type() {
		return doc_type;
	}
	
	public String getBoardId(){
		return boardId;
	}
}
