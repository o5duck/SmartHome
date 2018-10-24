package service;

import org.springframework.transaction.annotation.Transactional;

import dao.MasterDevDao;
import dao.SubDevDao;
import dao.UserDevDao;
import request.DeviceRequest;
import response.DeviceResponse;

public class DeviceService {
	private MasterDevDao masterDevDao;
	private SubDevDao subDevDao;
	private UserDevDao userDevDao;
	
	public DeviceService(MasterDevDao masterDevDao, SubDevDao subDevDao, UserDevDao userDevDao){
		this.masterDevDao = masterDevDao;
		this.subDevDao = subDevDao;
		this.userDevDao = userDevDao;
	}
	
	@Transactional
	public DeviceResponse addDevice(DeviceRequest req){
		DeviceResponse devResponse = new DeviceResponse();
		if(masterDevDao.isExist(req.getDev_id(), req.getDev_password())){
			if(subDevDao.isExist(req.getSub_id(), req.getSub_bd())){
				userDevDao.addInfo(req.getUser_id(), req.getDev_id(), req.getSub_id());
				return new DeviceResponse(req.getUser_id(), req.getDev_id(), req.getSub_id(), null);
			}else{
				devResponse.setMsg("Sub Device Id와 bluetooth address를 다시 확인해주세요.");
				return devResponse;
			}
		}else{
			devResponse.setMsg("AI SPEAKER ID와 PASSWORD CODE를 다시 확인해주세요.");
			return devResponse;
		}
	}
	
	@Transactional
	public DeviceResponse removeDevice(DeviceRequest req){
		DeviceResponse devResponse = new DeviceResponse();
		if(userDevDao.selectById(req.getUser_id()) != null){
			userDevDao.removeInfo(req.getUser_id());
			devResponse.setMsg("success");
			return devResponse;
		}else{
			devResponse.setMsg("fail");
			return devResponse;
		}
	}
}
