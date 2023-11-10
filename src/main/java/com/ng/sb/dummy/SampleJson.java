package com.ng.sb.dummy;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping("/test/testClient")
@Component
public class SampleJson {
	
	 private JsonNode jsonNode;
	 
	private final JsonDataService jsonDataService;

	@Autowired
    public SampleJson(JsonDataService jsonDataService) throws IOException {
		System.out.println("SampleJson() constructor started for MerchantDummy Application........."); 
        this.jsonDataService = jsonDataService;
        this.jsonNode =   this.jsonDataService.loadJsonData();
    }

	 @Scheduled(cron = "0 */15 * * * ?")
	  public void sampleJsonTask() { 
		 try {
			 System.out.println("Cron job started for MerchantDummy Application........."); 
			 jsonNode = jsonDataService.loadJsonData(); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		 } 
	  }
	
    private ResponseEntity<String> handleEndpoint(String endpointKey) {
    	System.out.println("handleEndpoint() started for MerchantDummy Application........."); 
        JsonNode endpointNode = jsonNode.get(endpointKey);
        if (endpointNode != null) {
            String endpointJson = endpointNode.toString();
            return ResponseEntity.ok(endpointJson);
        } else {
            return ResponseEntity.ok(null);
        }
    }
	
	@PostMapping("/login")
	public ResponseEntity<String> getJson(@RequestParam String userName,@RequestParam String password) throws IOException {
		return handleEndpoint("login");
	}
	
	@PostMapping("/changePassword")
	public ResponseEntity<String> changePassword(
			@RequestParam String userId,
			@RequestParam String password,
			@RequestParam String newPassword
			) throws IOException{
		return handleEndpoint("password");
	}
	
	@PostMapping("/resetPassword")
	public ResponseEntity<String> resetPassword(){
		return handleEndpoint("resetPassword");
	}
	
	@PostMapping("/userDashboard")
	public ResponseEntity<String> dashboard(@RequestParam String authorization){
		return handleEndpoint("dashBoardResponse");
	}
	
	@PostMapping("/raisePO")
	public ResponseEntity<String> raisePo(@RequestParam String authorization){
		return handleEndpoint("raisePoResponse");
	}
	
	@PostMapping("/validateCustomer")
	public ResponseEntity<String> validateCustomer(@RequestParam String authorization){
		return handleEndpoint("validateCustomerResponse");
	}
	
	@PostMapping("/generateOTP")
	public ResponseEntity<String> generateOTP(@RequestParam String authorization){
	return handleEndpoint("generateOtpResponse");
	}
	
	@PostMapping("/verifyOTP")
	public ResponseEntity<String> verifyOTP(@RequestParam String authorization){
		return handleEndpoint("verifyOtpResponse");
	}
	
	@PostMapping("/fetchOverlayProduct")
	public ResponseEntity<String> fetchOverlayProduct(@RequestParam String authorization){
		return handleEndpoint("fetchProductResponse");
	}
	
	@PostMapping("/fetchOverlayMasterVersion")
	public ResponseEntity<String> fetchOverlayMasterVersion(@RequestParam String authorization){
		return handleEndpoint("masterVersionResponse");
	}
	
	@PostMapping("/fetchOverlaySubVersion")
	public ResponseEntity<String> fetchOverlaySubVersion(@RequestParam String authorization){
		return handleEndpoint("subVersionResponse");
	}
	
	@PostMapping("/fetchOverlay")
	public ResponseEntity<String> listmapOverlay(@RequestParam String authorization){
		return handleEndpoint("listOverlaysResponse");
	}
	
	@PostMapping("/mapOverlay")
	public ResponseEntity<String> mapOverlay(@RequestParam String authorization){
		return handleEndpoint("mapOverlaysResponse");
	}
	
    @PostMapping("/fetchNotification")	
    public ResponseEntity<String> fetchNotification(@RequestParam String rrn){
	    return handleEndpoint("fetchNotification");
    }
    
    @PostMapping("/userServiceRequest")
    @ResponseBody
    public ResponseEntity<String> userServiceRequest(@RequestBody JsonNode users){
    	return handleEndpoint("userServiceRequest");
    }
    
    @PostMapping("/common")
    @ResponseBody
    public ResponseEntity<String> commonEndPoint(@RequestBody JsonNode users,@RequestParam("endPoint") String endPoint) {
    	return handleEndpoint(endPoint);
    }

}


