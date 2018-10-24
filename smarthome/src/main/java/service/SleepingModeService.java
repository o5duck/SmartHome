package service;

import org.springframework.transaction.annotation.Transactional;

import dao.SleepingModeDao;
import request.SleepingRequest;
import response.SleepingResponse;

public class SleepingModeService {
	private SleepingModeDao sleepingModeDao;
	
	public SleepingModeService(SleepingModeDao sleepingModeDao){
		this.sleepingModeDao = sleepingModeDao;
	}
	
	@Transactional
	public SleepingResponse addInfo(SleepingRequest req){
		if(sleepingModeDao.searchInfo(req.getMaster_dev_id()) == null){
			sleepingModeDao.addInfo(req.getMaster_dev_id(), req.getAwake_time(), req.getTemperature());
		}else{
			sleepingModeDao.updateInfo(req.getMaster_dev_id(), req.getAwake_time(), req.getTemperature());
		}
		return new SleepingResponse("success");
	}
	
	@Transactional
	public SleepingResponse searchInfo(String master_dev_id){
		return sleepingModeDao.searchInfo(master_dev_id);
	}
}
