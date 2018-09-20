package com.chetan.rest.webservices.restfulwebservices.helloworld;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	@GetMapping("/filter-some-bean")
	public MappingJacksonValue getSomeBean() {
		SomeBean someBean = new SomeBean("value1","value2","value3");
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");  //Step 1
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);    //Step 2
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);                                   //Step 3
		mapping.setFilters(filterProvider);                                 							   //Step 4
		return mapping;
	}

	@GetMapping("/filter-some-bean-list")
	public List<SomeBean> getSomeBeanList() {
		return Arrays.asList(new SomeBean("value1","value2","value3"),
							 new SomeBean("value1","value2","value3"));
	}
}
