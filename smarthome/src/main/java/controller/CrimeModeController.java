package controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import request.CrimeRequest;
import request.CrimeSearchRequest;
import response.CrimeResponse;
import response.CrimeSearchResponse;
import service.CrimeModeService;

@RequestMapping("/crimeMode")
@RestController
public class CrimeModeController {
	private CrimeModeService crimeModeService;
	
	public void setCrimeModeService(CrimeModeService crimeModeService){
		this.crimeModeService = crimeModeService;
	}
	
	@RequestMapping(value="/addImage", method=RequestMethod.POST)
	public CrimeResponse addImage(@RequestBody CrimeRequest request){
		return crimeModeService.addImage(request);
	}
	
	@RequestMapping(value="/search", method=RequestMethod.POST)
	public CrimeSearchResponse searchImage(@RequestBody CrimeSearchRequest request){
		return crimeModeService.searchImage(request);
	}
}
