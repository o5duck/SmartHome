package service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import dao.CrimeModeDao;
import dao.MasterDevDao;
import request.CrimeRequest;
import request.CrimeSearchRequest;
import response.CrimeResponse;
import response.CrimeSearchResponse;

public class CrimeModeService {
	private CrimeModeDao crimeModeDao;
	private MasterDevDao masterDevDao;
	
	public CrimeModeService(CrimeModeDao crimeModeDao, MasterDevDao masterDevDao){
		this.crimeModeDao = crimeModeDao;
		this.masterDevDao = masterDevDao;
	}
	
	@Transactional
	public CrimeResponse addImage(CrimeRequest req){
		if(masterDevDao.isExist(req.getMaster_dev_id())){
			crimeModeDao.addInfo(req.getMaster_dev_id(), req.getPhoto_path());
			return new CrimeResponse("success");
		}else{
			return new CrimeResponse("fail");
		}
		
	}
	
	@Transactional
	public CrimeSearchResponse searchImage(CrimeSearchRequest req){
		List<String> image_list = crimeModeDao.searchImage(req);
		return new CrimeSearchResponse(image_list, "success");
	}
}
