package playground.service.console;

import org.amdatu.multitenant.TenantFactoryConfiguration;

import playground.service.TestService;

public class TestConsole {

	private volatile TestService service;
	public void start() {
		System.out.println("Console is starting");
		
	}
	
	public void getName() {
		System.out.println(service.getName());
	}
	public void setName(String name) {
		service.setName(name);
	}
}
