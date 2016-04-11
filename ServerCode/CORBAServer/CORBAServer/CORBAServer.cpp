#include "stdafx.h"
#include "telegerImpl.h"
#include "linkedList.h"
using namespace std;

static CORBA::ORB_ptr orb;
//static int            dying = 0;
//static int            num_active_servers = 0;
//static omni_mutex     mu;
//static omni_condition sigobj(&mu);
//////////////////////////////////////////////////////////////////////

int main(int argc, char** argv)
{
	linkedList * connectedUsers = new linkedList();
	teleger::SafeUser * testSafeUser = new SafeUser;
	testSafeUser->id = "eu";
	testSafeUser->image = "img";
	testSafeUser->name = "name";
	testSafeUser->ip = "ip";
	connectedUsers->_insert(*testSafeUser);
	cout << (connectedUsers->search("eu")).name << endl;
	connectedUsers->_delete("eu");
	cout << (connectedUsers->search("eu")).name << endl;
	/*try {
		orb = CORBA::ORB_init(argc, argv);

		{
			CORBA::Object_var obj = orb->resolve_initial_references("RootPOA");
			PortableServer::POA_var poa = PortableServer::POA::_narrow(obj);
			PortableServer::POAManager_var pman = poa->the_POAManager();
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
					myserver->startSQLConnector();
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
	}*/
	///connection test
	telegerImpl * tmp = new telegerImpl;
	User *testUser = new User;
	testUser->id = "eu";
	testUser->image = "img";
	testUser->name = "name";
	testUser->password = "pass";
	tmp->startSQLConnector();
	tmp->_cxx_register(*testUser);
	cout << "Ola!" << endl;

	getchar();
	return 0;
}