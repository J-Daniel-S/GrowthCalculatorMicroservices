package the.best.stockprovider.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import the.best.stockprovider.asset.Stock;
import the.best.stockprovider.templates.ReturnValues;
import the.best.stockprovider.utils.RequestHandler;

@RestController//8200
@RequestMapping("/stock")
public class StockController {
	
	@Autowired
	public Environment env;
	
	@PostMapping
	public ResponseEntity<Stock> getStock(
			@RequestBody Stock stockRequest	
			) {
		
		StringBuilder request = RequestHandler.buildRequestUrl(stockRequest, env);
		ReturnValues response = new RestTemplate().getForObject(request.toString(), ReturnValues.class);
		return RequestHandler.handleReturn(stockRequest, response);
		
	}

	//the contents of the stockRequest object are different in this method, they're not identical
	@PostMapping("/fcf")
	public ResponseEntity<Stock> getStockFcf(
			@RequestBody Stock stockRequest	
			) {
		
		StringBuilder request = RequestHandler.buildRequestUrlFcf(stockRequest, env);
		ReturnValues response = new RestTemplate().getForObject(request.toString(), ReturnValues.class);
		return RequestHandler.handleReturn(stockRequest, response);
		
	}
	
	@PostMapping("/{growthRate}")
	public ResponseEntity<Stock> getStockGrowthRate(
			@RequestBody Stock stockRequest,
			@PathVariable long growthRate
			) {
		
		StringBuilder request = RequestHandler.buildRequestUrl(stockRequest, growthRate, env);
		ReturnValues response = new RestTemplate().getForObject(request.toString(), ReturnValues.class);
		return RequestHandler.handleReturn(stockRequest, growthRate, response);
		
	}

	//the contents of the stockRequest object are different in this method, they're not identical
	@PostMapping("/fcf/{growthRate}")
	public ResponseEntity<Stock> getStockFcfGrowthRate(
			@RequestBody Stock stockRequest,
			@PathVariable long growthRate
			) {
		
		StringBuilder request = RequestHandler.buildRequestUrlFcf(stockRequest, growthRate, env);
		ReturnValues response = new RestTemplate().getForObject(request.toString(), ReturnValues.class);
		return RequestHandler.handleReturn(stockRequest, growthRate, response);
		
	}

}
