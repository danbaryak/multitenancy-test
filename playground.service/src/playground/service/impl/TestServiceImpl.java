package playground.service.impl;

import playground.service.TestService;

public class TestServiceImpl implements TestService {
	private String name;
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
}
