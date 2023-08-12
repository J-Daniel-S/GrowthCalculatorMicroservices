package the.best.growthvaluecalculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import the.best.growthvaluecalculator.templates.StockAsset;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/calculator")
public class CalculatorController {

	@GetMapping
	public <T> StockAsset calculateStockValue(@RequestBody StockAsset stock) {
		return stock;
	}
	
}
