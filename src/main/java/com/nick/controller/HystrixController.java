package com.nick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nick.hystrix.HystrixManager;

@RestController
public class HystrixController {

	@Autowired
	HystrixManager hystrixManager;

	@RequestMapping("/price")
	public String getPrice() {
		return "The price is " + hystrixManager.getPrice(23);
	}
}
