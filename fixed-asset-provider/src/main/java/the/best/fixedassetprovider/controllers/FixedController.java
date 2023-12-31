package the.best.fixedassetprovider.controllers;

import org.apache.hc.core5.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import the.best.fixedassetprovider.assets.Fixed;

@RestController//8100
@RequestMapping("/fixed")
public class FixedController {
	
	@Autowired
	public Environment env;
	
	@GetMapping("/")
	public ResponseEntity<Fixed> getFixedAsset(
			@RequestParam long principle, 
			@RequestParam double interest,
			@RequestParam double length,
			@RequestParam int compoundFrequency
			) {
		StringBuilder request = new StringBuilder()
				.append(env.getProperty("calculator.path"))
				.append(env.getProperty("calculator.port"))
				.append("/fixed?principle=").append(principle)
				.append("&interest=").append(interest)
				.append("&length=").append(length)
				.append("&compoundFrequency=").append(compoundFrequency);
		
		long endValue = 0;
		
		try {
			endValue = new RestTemplate().getForObject(
					request.toString(), Long.class
					);
		} catch (NullPointerException e) {
			return ResponseEntity.status(HttpStatus.SC_SERVICE_UNAVAILABLE).body(new Fixed(principle, interest, length, compoundFrequency));
		}
		
		return ResponseEntity.ok(new Fixed(principle, interest, length, compoundFrequency, endValue));
	}

}
