//
// Example code for implementing IDL interfaces in file teleger.idl
//

#include <iostream>
#include <teleger.hh>


class teleger_ServerInterface_i : public POA_teleger::ServerInterface {
private:
  // Make sure all instances are built on the heap by making the
  // destructor non-public
  //virtual ~teleger_ServerInterface_i();

public:
  // standard constructor
  teleger_ServerInterface_i();
  virtual ~teleger_ServerInterface_i();

  // methods corresponding to defined IDL attributes and operations
  ::CORBA::Boolean _cxx_register(const teleger::User& userData);
  teleger::userFriends* logIn(const char* userId, const char* userPassword, const char* ip);
  ::CORBA::Boolean logOut(const char* userId, const char* userPassword);
  teleger::userFriends* searchNewFriends(const char* name);
  void sendRequestForFriend(const teleger::SafeUser& user, const teleger::SafeUser& _cxx_friend);
};

//
// Example implementation code for IDL interface 'teleger::ServerInterface'
//
teleger_ServerInterface_i::teleger_ServerInterface_i(){
  // add extra constructor code here
}
teleger_ServerInterface_i::~teleger_ServerInterface_i(){
  // add extra destructor code here
}

// Methods corresponding to IDL attributes and operations
::CORBA::Boolean teleger_ServerInterface_i::_cxx_register(const teleger::User& userData)
{
  // insert code here and remove the warning
  #warning "Code missing in function <::CORBA::Boolean teleger_ServerInterface_i::_cxx_register(const teleger::User& userData)>"
}

teleger::userFriends* teleger_ServerInterface_i::logIn(const char* userId, const char* userPassword, const char* ip)
{
  // insert code here and remove the warning
  #warning "Code missing in function <teleger::userFriends* teleger_ServerInterface_i::logIn(const char* userId, const char* userPassword, const char* ip)>"
}

::CORBA::Boolean teleger_ServerInterface_i::logOut(const char* userId, const char* userPassword)
{
  // insert code here and remove the warning
  #warning "Code missing in function <::CORBA::Boolean teleger_ServerInterface_i::logOut(const char* userId, const char* userPassword)>"
}

teleger::userFriends* teleger_ServerInterface_i::searchNewFriends(const char* name)
{
  // insert code here and remove the warning
  #warning "Code missing in function <teleger::userFriends* teleger_ServerInterface_i::searchNewFriends(const char* name)>"
}

void teleger_ServerInterface_i::sendRequestForFriend(const teleger::SafeUser& user, const teleger::SafeUser& _cxx_friend)
{
  // insert code here and remove the warning
  #warning "Code missing in function <void teleger_ServerInterface_i::sendRequestForFriend(const teleger::SafeUser& user, const teleger::SafeUser& _cxx_friend)>"
}



// End of example implementation code



int main(int argc, char** argv)
{
  try {
    // Initialise the ORB.
    CORBA::ORB_var orb = CORBA::ORB_init(argc, argv);

    // Obtain a reference to the root POA.
    CORBA::Object_var obj = orb->resolve_initial_references("RootPOA");
    PortableServer::POA_var poa = PortableServer::POA::_narrow(obj);

    // We allocate the objects on the heap.  Since these are reference
    // counted objects, they will be deleted by the POA when they are no
    // longer needed.
    teleger_ServerInterface_i* myteleger_ServerInterface_i = new teleger_ServerInterface_i();


    // Activate the objects.  This tells the POA that the objects are
    // ready to accept requests.
    PortableServer::ObjectId_var myteleger_ServerInterface_iid = poa->activate_object(myteleger_ServerInterface_i);



    {
      // IDL interface: teleger::ServerInterface
      CORBA::Object_var ref = myteleger_ServerInterface_i->_this();
      CORBA::String_var sior(orb->object_to_string(ref));
      std::cout << "IDL object teleger::ServerInterface IOR = '" << (char*)sior << "'" << std::endl;
    }



    // Obtain a POAManager, and tell the POA to start accepting
    // requests on its objects.
    PortableServer::POAManager_var pman = poa->the_POAManager();
    pman->activate();

    orb->run();
    orb->destroy();
  }
  catch(CORBA::TRANSIENT&) {
    std::cerr << "Caught system exception TRANSIENT -- unable to contact the "
              << "server." << std::endl;
  }
  catch(CORBA::SystemException& ex) {
    std::cerr << "Caught a CORBA::" << ex._name() << std::endl;
  }
  catch(CORBA::Exception& ex) {
    std::cerr << "Caught CORBA::Exception: " << ex._name() << std::endl;
  }
  return 0;
}
