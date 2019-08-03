package com.example.demo.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RestController
public class CurrenyExchangeController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeRepository exchangeRepository;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * @param from
	 * @param to
	 * @return
	 */
	/*
	 * @HystrixCommand(fallbackMethod = "getExchangeValue_Fallback",
	 * commandProperties = {
	 * 
	 * @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
	 * value = "100") })
	 */
	  @HystrixCommand(fallbackMethod = "getExchangeValue_Fallback")
	 
	@GetMapping("/curreny-exchange/from/{from}/to/{to}")
	public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		logger.info("Inside CurrenyExchangeController >> getExchangeValue");
		
		
		  String name = null; System.out.println(name.toString());
		 
		
		/*
		 * try { Thread.sleep(20000); } catch (Exception e) {
		 * System.out.println("#### Exception occured ###"); }
		 */
		
		
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
	
	private ExchangeValue getExchangeValue_Fallback(@PathVariable String from, @PathVariable String to) {
		ExchangeValue exchangeValue = new ExchangeValue();
		exchangeValue.setFrom(from);
		exchangeValue.setTo(to);
		exchangeValue.setPort(1000);
		exchangeValue.setConversionMultiply(2);
        return exchangeValue;
    }
	
}
