package service;

import org.springframework.transaction.annotation.Transactional;

import dao.OutsideModeDao;
import request.OutsideRequest;
import response.OutsideResponse;

public class OutsideModeService {
private OutsideModeDao outsideModeDao;
	
	public OutsideModeService(OutsideModeDao outsideModeDao){
		this.outsideModeDao = outsideModeDao;
	}
	
	@Transactional
	public OutsideResponse addInfo(OutsideRequest req){
		if(outsideModeDao.searchInfo(req.getMaster_dev_id()) == null){
			outsideModeDao.addInfo(req.getMaster_dev_id(), req.getAuto_lighting_time(), req.getAuto_lighting_room_id());
		}else{
			outsideModeDao.updateInfo(req.getMaster_dev_id(), req.getAuto_lighting_time(), req.getAuto_lighting_room_id());
		}
		return new OutsideResponse("success");
	}
	
	@Transactional
	public OutsideResponse searchInfo(String master_dev_id){
		return outsideModeDao.searchInfo(master_dev_id);
	}
}
