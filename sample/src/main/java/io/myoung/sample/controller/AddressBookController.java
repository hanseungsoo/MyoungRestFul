package io.myoung.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.myoung.sample.service.AddressBookService;

@RestController
@RequestMapping(value="/addressBook")
public class AddressBookController {
	
	@Autowired
	private AddressBookService addressBookService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getHome() {
		return "HelloWorld";
	}
	
	
}
