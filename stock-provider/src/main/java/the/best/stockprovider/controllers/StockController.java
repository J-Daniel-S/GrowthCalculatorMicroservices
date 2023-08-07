package the.best.stockprovider.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import the.best.stockprovider.asset.Stock;
import the.best.stockprovider.templates.StockAsset;

@RestController
@RequestMapping("/stock")
public class StockController {

	@GetMapping
	public StockAsset getStock(@RequestBody Stock stock) {
		return stock;
	}
	
}
