package the.best.fixedassetprovider.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import the.best.fixedassetprovider.assets.Fixed;
import the.best.fixedassetprovider.templates.FixedAsset;

@RequestMapping("/fixed-asset")
@RestController
public class FixedController {
	
	@GetMapping
	public FixedAsset getFixedAsset(@RequestBody Fixed fixed) {
		return fixed;
	}

}
