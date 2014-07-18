package playground.service.impl;

import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.osgi.framework.BundleContext;

import playground.service.TestService;

public class Activator extends DependencyActivatorBase {

	@Override
	public void destroy(BundleContext arg0, DependencyManager arg1)
			throws Exception {
		
		
	}

	@Override
	public void init(BundleContext context, DependencyManager manager)
			throws Exception {
		manager.add(createComponent().setInterface(TestService.class.getName(), null)
				.setImplementation(TestServiceImpl.class));
	}

}
