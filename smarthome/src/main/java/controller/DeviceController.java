package controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import request.DeviceRequest;
import response.DeviceResponse;
import service.DeviceService;

@RequestMapping("/device")
@RestController
public class DeviceController {
	private DeviceService deviceService;

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public DeviceResponse addDevice(@RequestBody DeviceRequest request){
		return deviceService.addDevice(request);
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public DeviceResponse removeDevice(@RequestBody DeviceRequest request){
		return deviceService.removeDevice(request);
	}

}
