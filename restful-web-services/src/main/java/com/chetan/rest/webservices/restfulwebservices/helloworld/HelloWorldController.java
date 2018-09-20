package com.chetan.rest.webservices.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource;
	
	//@RequestMapping(method=RequestMethod.GET,path="/hello-world")
	@GetMapping(path="/hello-world")
	public String helloWorld() {
		return "Hello-World";
	}
	
	//@RequestMapping(method=RequestMethod.GET,path="/hello-world")
	@GetMapping(path="/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello-World");
	}
	
	//@RequestMapping(method=RequestMethod.GET,path="/hello-world")
	@GetMapping(path="/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello-World , %s",name));
	}
	
	
	@GetMapping(path="/hello-world-internationalized1")
	public HelloWorldBean helloWorldInternationalized1(@RequestHeader(name="Accept-Language",required=false)Locale locale) {
		String greet = messageSource.getMessage("good.morning.message",null, locale);
		return new HelloWorldBean(greet);
	}
	
	
	
	@GetMapping(path="/hello-world-internationalized2")
	public HelloWorldBean helloWorldInternationalized2(@RequestHeader(name="Accept-Language",required=false)Locale locale) {
		String greet = messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
		return new HelloWorldBean(greet);
	}


}
