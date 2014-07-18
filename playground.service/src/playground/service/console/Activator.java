package playground.service.console;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.amdatu.multitenant.Constants;
import org.amdatu.multitenant.TenantFactoryConfiguration;
import org.apache.felix.dm.DependencyActivatorBase;
import org.apache.felix.dm.DependencyManager;
import org.apache.felix.service.command.CommandProcessor;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

import playground.service.TestService;

public class Activator extends DependencyActivatorBase {

	@Override
	public void destroy(BundleContext arg0, DependencyManager arg1)
			throws Exception {

	}

	@Override
	public void init(BundleContext context, DependencyManager manager)
			throws Exception {

		String tenantPid = configureTenant(context);

		System.out.println("Tenant PID is " + tenantPid);
//		ServiceReference ref = context.getServiceReference(TestService.class.getName(), String.format("(%1$s=%2$s)", Constants.PID_KEY, tenantPid));
//		System.out.println("service reference is " + ref);
		
		Properties props = new Properties();
		props.put(CommandProcessor.COMMAND_SCOPE, "test");
		props.put(CommandProcessor.COMMAND_FUNCTION, new String[] { "getName",
				"setName" });
		manager.add(createComponent()
				.setInterface(Object.class.getName(), props)
				.setImplementation(TestConsole.class)
				.add(createServiceDependency().setService(
						TestService.class,
						String.format("(%1$s=%2$s)", Constants.PID_KEY, tenantPid)).setRequired(false)));

	}

	private String configureTenant(BundleContext context) {
		Map<String, Map<String, Object>> tenants = new HashMap<String, Map<String, Object>>();

		String name = "Tentant1";
		String pid = "12345";
		Map<String, Object> props = new HashMap<String, Object>();
		props.put(org.amdatu.multitenant.Constants.PID_KEY, pid);
		props.put(org.amdatu.multitenant.Constants.NAME_KEY, name);
		tenants.put(pid, props);

		try {
			ServiceReference ref = context
					.getServiceReference(TenantFactoryConfiguration.class
							.getName());
			TenantFactoryConfiguration tfc = (TenantFactoryConfiguration) context
					.getService(ref);

			if (tfc != null) {
				tfc.update(tenants);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return pid;
	}

}
