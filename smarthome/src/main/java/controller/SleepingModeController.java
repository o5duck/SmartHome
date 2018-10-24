package controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import request.SleepingRequest;
import response.SleepingResponse;
import service.SleepingModeService;

@RequestMapping("/sleepingMode")
@RestController
public class SleepingModeController {
	private SleepingModeService sleepingModeService;

	public void setSleepingModeService(SleepingModeService sleepingModeService) {
		this.sleepingModeService = sleepingModeService;
	}
	
	@RequestMapping(value="/addInfo", method=RequestMethod.POST)
	public SleepingResponse addInfo(@RequestBody SleepingRequest req){
		return sleepingModeService.addInfo(req);
	}
	
	@RequestMapping(value="/searchInfo", method=RequestMethod.POST)
	public SleepingResponse searchInfo(@RequestBody SleepingRequest req){
		return sleepingModeService.searchInfo(req.getMaster_dev_id());
	}
	
}
