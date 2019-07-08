package com.example.demo.exchange;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExchangeValue {
	
	@Id
	private Integer id;
	@Column(name = "currency_from")
	private String from;
	@Column(name = "currency_to")
	private String to;
	private int conversionMultiply;
	private int port;
	
	protected ExchangeValue() {
	}
	
	public ExchangeValue(Integer id, String from, String to, int conversionMultiply) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiply = conversionMultiply;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public int getConversionMultiply() {
		return conversionMultiply;
	}

	public void setConversionMultiply(int conversionMultiply) {
		this.conversionMultiply = conversionMultiply;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
}
