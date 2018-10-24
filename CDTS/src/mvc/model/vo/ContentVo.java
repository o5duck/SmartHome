package mvc.model.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContentVo {
	private int docId;
	private String title;
	private String content;
	private Date c_time;
	private String attached;
	private String[] gen_user;
	private String[] des1;
	private String[] des2;
	private String[] des3;
	private String doc_type;
	private String state;
	private String listId;

	public ContentVo(String title, String content, Date c_time, String[] gen_user, String[] des1, String[] des2, String[] des3, String doc_type, String state, String listId){
		this.title = title;
		this.content = content;
		this.c_time = c_time;
		this.gen_user = gen_user;
		this.des1 = des1;
		this.des2 = des2;
		this.des3 = des3;
		this.doc_type = doc_type;
		this.state = state;
		this.listId = listId;
		this.attached = "";
		this.docId = 0;
	}
	
	public void setDocId(int docId){
		this.docId = docId;
	}
	
	public void setAttached(String attached){
		this.attached = attached;
	}

	public int getDocId(){
		return docId;
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

	public String[] getGen_user() {
		return gen_user;
	}

	public String[] getDes1() {
		return des1;
	}

	public String[] getDes2() {
		return des2;
	}

	public String[] getDes3() {
		return des3;
	}

	public String getDoc_type() {
		return doc_type;
	}
	
	public String getState(){
		return state;
	}
	
	public String getListId(){
		return listId;
	}
	
	static public String convertDateFormat(Date date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}
}
