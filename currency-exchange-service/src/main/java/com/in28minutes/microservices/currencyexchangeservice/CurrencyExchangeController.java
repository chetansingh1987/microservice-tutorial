package com.in28minutes.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	@Autowired
	Environment environment;
	@GetMapping("/exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from,@PathVariable  String to) {
		ExchangeValue ev = new  ExchangeValue(10l, from, to, BigDecimal.valueOf(65L));
		ev.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		return ev;
	}

}
