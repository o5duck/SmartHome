package mvc.model.service.CRUD;

import java.sql.SQLException;

import mvc.model.service.ServiceModel;
import mvc.model.vo.DocumentVo;
import mvc.model.vo.WriteResultVo;

public interface WriteDocService extends ServiceModel {
	public WriteResultVo service(DocumentVo docVo) throws SQLException;
}
