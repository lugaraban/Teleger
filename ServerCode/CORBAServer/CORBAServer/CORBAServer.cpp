// CORBAServer.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include "telegerImpl.h"
using namespace std;

int main(int argc, char ** argv)
{
	try {
		// Initialize operations for the ORB
		CORBA::ORB_ptr orb = CORBA::ORB_init(argc, argv);

		// Resolves a specific object reference from the set of available initial service names.
		CORBA::Object_var poa_obj = orb->resolve_initial_references("RootPOA");
		//Gets a reference of the desired object
		PortableServer::POA_var poa = PortableServer::POA::_narrow(poa_obj);
		//Create a new POA manager with the empty call
		PortableServer::POAManager_var manager = poa->the_POAManager();
		// We allocate the objects on the heap.  Since these are reference
		// counted objects, they will be deleted by the POA when they are no
		// longer needed.
		telegerImpl* myteleger_ServerInterface_i = new telegerImpl();

		try {
			CORBA::Object_var ns_obj = orb->resolve_initial_references("NameService");
			if (!CORBA::is_nil(ns_obj)) {
				CosNaming::NamingContext_ptr nc = CosNaming::NamingContext::_narrow(ns_obj);
				CosNaming::Name name;
				name.length(1);
				name[0].id = CORBA::string_dup("TestServer");
				name[0].kind = CORBA::string_dup("");
				//Start the service
				nc->rebind(name, myteleger_ServerInterface_i->_this());
				cout << "Server is running ..." << endl;
			}
		}
		catch (CosNaming::NamingContext::NotFound &) {
			cerr << "not found" << endl;
		}
		catch (CosNaming::NamingContext::InvalidName &) {
			cerr << "invalid name" << endl;
		}
		catch (CosNaming::NamingContext::CannotProceed &) {
			cerr << "cannot proceed" << endl;
		}

		// run
		manager->activate();
		orb->run();

		// clean up
		delete myteleger_ServerInterface_i;

		// quit
		orb->destroy();
	}
	catch (CORBA::UNKNOWN) {
		cerr << "unknown exception" << endl;
	}
	catch (CORBA::SystemException &) {
		cerr << "system exception" << endl;
	}
}