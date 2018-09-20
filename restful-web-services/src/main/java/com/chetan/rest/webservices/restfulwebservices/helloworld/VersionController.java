package com.chetan.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class VersionController {
	
	@GetMapping(value="/person/param",params="version=1")
	public PersonV1 getPersonV1() {
		return new PersonV1("Chetan Singh");
	}
	
	@GetMapping(value="/person/param",params="version=2")
	public PersonV2 getPersonV2() {
		return new PersonV2(new Name("Chetan","Singh"));
	}
	
	@GetMapping(value="/person/header",headers="version=1")
	public PersonV1 getHeaderV1() {
		return new PersonV1("Chetan Singh");
	}
	
	@GetMapping(value="/person/header",headers="version=2")
	public PersonV2 getHeaderV2() {
		return new PersonV2(new Name("Chetan","Singh"));
	}
	
	@GetMapping(value="/person/produces",produces="application/v1+json")
	public PersonV1 getProducesV1() {
		return new PersonV1("Chetan Singh");
	}
	
	@GetMapping(value="/person/produces",produces="application/v2+json")
	public PersonV2 getProducesV2() {
		return new PersonV2(new Name("Chetan","Singh"));
	}
}
class PersonV1{
	String name;

	public PersonV1(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
class PersonV2{
	Name name;

	public PersonV2(Name name) {
		super();
		this.name = name;
	}

	public Name getName() {
		return name;
	}
}

class Name{
	String firstName;
	String lastName;
	
	public Name(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
}