package com.example.demo.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrenyExchangeController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeRepository exchangeRepository;
	
	/**
	 * @param from
	 * @param to
	 * @return
	 */
	@GetMapping("/curreny-exchange/from/{from}/to/{to}")
	public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		//We can write custom method to retrieve the record based on From and To like the below. Refer ExchangeRepository 
		ExchangeValue exchangeValue = exchangeRepository.findByFromAndTo(from, to);
		if(exchangeValue == null) {
			return exchangeValue;
		}
		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		
		//Below is the usual way of getting the particular record by iterating over the list and find the correct record.
		/*
		List<ExchangeValue> values = exchangeRepository.findAll();
		for(ExchangeValue value : values) {
			if(value!=null 
					&& value.getFrom().equals(from) 
					&& value.getTo().equals(to)) {
				exchangeValue.setConversionMultiply(value.getConversionMultiply());
				exchangeValue.setFrom(value.getFrom());
				exchangeValue.setTo(value.getTo());
			}
		}*/
		return exchangeValue;
	}
}