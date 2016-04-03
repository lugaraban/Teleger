#include "stdafx.h"
#include "telegerImpl.h"

using namespace std;

static CORBA::ORB_ptr orb;
static int            dying = 0;
static int            num_active_servers = 0;
static omni_mutex     mu;
static omni_condition sigobj(&mu);

//////////////////////////////////////////////////////////////////////

int main(int argc, char** argv)
{
	try {
		orb = CORBA::ORB_init(argc, argv);

		{
			CORBA::Object_var obj = orb->resolve_initial_references("RootPOA");
			PortableServer::POA_var poa = PortableServer::POA::_narrow(obj);
			PortableServer::POAManager_var pman = poa->the_POAManager();
			//server_i* myserver = new server_i;
			telegerImpl * myserver = new telegerImpl;
			////////////////////////////////////////////////////////
			try {
				CORBA::Object_var ns_obj = orb->resolve_initial_references("NameService");
				if (!CORBA::is_nil(ns_obj)) {
					CosNaming::NamingContext_ptr nc = CosNaming::NamingContext::_narrow(ns_obj);
					CosNaming::Name name;
					name.length(1);
					name[0].id = CORBA::string_dup("TestServer");
					name[0].kind = CORBA::string_dup("");
					//Start the service
					nc->rebind(name, myserver->_this());
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

			//////////////////////////////////////////////////
			pman->activate();
			orb->run();

		}
		cout << "cb_server: Returned from orb->run()." << endl;
		orb->destroy();
	}
	catch (CORBA::SystemException& ex) {
		cerr << "Caught CORBA::" << ex._name() << endl;
	}
	catch (CORBA::Exception& ex) {
		cerr << "Caught CORBA::Exception: " << ex._name() << endl;
	}
	return 0;
}