package controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import request.OutsideRequest;
import response.OutsideResponse;
import service.OutsideModeService;

@RequestMapping("/outsideMode")
@RestController
public class OutsideModeController {
	private OutsideModeService outsideModeService;

	public void setOutsideModeService(OutsideModeService outsideModeService) {
		this.outsideModeService = outsideModeService;
	}
	
	@RequestMapping(value="/addInfo", method=RequestMethod.POST)
	public OutsideResponse addInfo(@RequestBody OutsideRequest req){
		return outsideModeService.addInfo(req);
	}
	
	@RequestMapping(value="/searchInfo", method=RequestMethod.POST)
	public OutsideResponse searchInfo(@RequestBody OutsideRequest req){
		return outsideModeService.searchInfo(req.getMaster_dev_id());
	}
}
