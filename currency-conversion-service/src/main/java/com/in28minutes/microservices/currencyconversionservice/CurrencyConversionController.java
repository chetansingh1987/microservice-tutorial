package com.in28minutes.microservices.currencyconversionservice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CurrencyConversionController {
	
	@Autowired
	Environment environment;
	
	@Autowired
	CurrencyExchangeServiceProxy currencyExchangeService;
	
	Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	
	@GetMapping("/currency-converter-feign/from/{from}/to/{to}/{quantity}")
	public CurrencyConversionBean retrieveConvertedValueFeign(@PathVariable String from,@PathVariable  String to,@PathVariable  BigDecimal quantity) {
		CurrencyConversionBean response = currencyExchangeService.retrieveExchangeValue(from, to);
		BigDecimal value = quantity.multiply(response.getConversionMultiple());
		CurrencyConversionBean ev = new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity, value);
		ev.setPort(response.getPort());
		logger.info("{}",response);
		return ev;
	}	
	
	@GetMapping("/currency-converter/from/{from}/to/{to}/{quantity}")
	public CurrencyConversionBean retrieveConvertedValue(@PathVariable String from,@PathVariable  String to,@PathVariable  BigDecimal quantity) {
		String url = "http://localhost:8000/currency-exchange/from/{from}/to/{to}" ;
		Map<String,String> uriVariables = new HashMap<>();
		uriVariables.put("from", from);
		uriVariables.put("to"  , to);
		
		ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(url,CurrencyConversionBean.class,uriVariables);
		CurrencyConversionBean response = responseEntity.getBody();
		BigDecimal value = quantity.multiply(response.getConversionMultiple());
		CurrencyConversionBean ev = new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity, value);
		ev.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		return ev;
	}

}
