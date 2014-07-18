multitenancy-test
=================

Hey!

What I tried to do here is a simple test service with an implementation and a console. The service has a setName() and getName() methods.
I then tried to make the service multitenant aware and then changed the console bundle activator to first configure the tenant
and then to inject the service using the tenant filter.
But it doesn't work, dm notavail says that the service isn't available
