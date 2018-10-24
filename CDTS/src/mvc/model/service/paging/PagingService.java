package mvc.model.service.paging;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import mvc.model.dao.ApprovalDesDao;
import mvc.model.dao.BoardDao;
import mvc.model.dao.PersonalDao;
import mvc.model.service.ServiceModel;
import mvc.model.vo.ContentVo;
import mvc.model.vo.DocPagingVo;

public class PagingService implements ServiceModel {
	private String doc_type;
	private int size = 13;
	
	public PagingService(String doc_type){
		this.doc_type = doc_type;
	}
	
	//검색 조건이 없는 개인 문서함 페이징 서비스
	public DocPagingVo service(int pageNum, String userId, String tableId) throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		try {
			PersonalDao personalDao = new PersonalDao();
			int total = personalDao.selectCount(conn, userId, doc_type, tableId);
			System.out.println(tableId);
			List<ContentVo> content = personalDao.select(
					conn, (pageNum - 1) * size, size, userId, tableId, doc_type);
			return new DocPagingVo(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
	}
	
	//검색 조건이 있는 개인 문서함 페이징 서비스
	public DocPagingVo service(int pageNum, String userId, String tableId, String searchCondition, String searchWord) throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		try  {
			PersonalDao personalDao = new PersonalDao();
			int total = personalDao.selectCount(conn, userId, doc_type, tableId, searchCondition, searchWord);
			List<ContentVo> content = personalDao.select(
					conn, (pageNum - 1) * size, size, userId, tableId, doc_type, searchCondition, searchWord);
			return new DocPagingVo(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
	}
	
	//검색 조건이 없는 게시판 페이징 서비스
	public DocPagingVo service(int pageNum, String userId, String tableId, boolean flag) throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		try  {
			BoardDao boardDao = new BoardDao();
			int total = boardDao.selectCount(conn, tableId);
			List<ContentVo> content = boardDao.select(
					conn, (pageNum - 1) * size, size, userId, tableId);
			return new DocPagingVo(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
	}
	
	//검색 조건이 있는 게시판 페이징 서비스
	public DocPagingVo service(int pageNum, String userId, String tableId, String searchCondition, String searchWord, boolean flag) throws SQLException {
		Connection conn = ConnectionProvider.getConnection();
		try {
			BoardDao boardDao = new BoardDao();
			int total = boardDao.selectCount(conn, tableId, searchCondition, searchWord);
			List<ContentVo> content = boardDao.select(
					conn, (pageNum - 1) * size, size, userId, tableId, searchCondition, searchWord);
			return new DocPagingVo(total, pageNum, size, content);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
	}
	
	public HashMap<String, String> getApprovalDes() throws SQLException{
		Connection conn = ConnectionProvider.getConnection();
		try{
			ApprovalDesDao approvalDesDao = new ApprovalDesDao();
			HashMap<String, String> map = approvalDesDao.getApprovalDes(conn);
			return map;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
	}
	
	public int getCntByState(String userId, String state) throws SQLException{
		Connection conn = ConnectionProvider.getConnection();
		try{
			PersonalDao personalDao = new PersonalDao();
			return personalDao.selectCntByState(conn, userId, state);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.close(conn);
		}
	}
}
