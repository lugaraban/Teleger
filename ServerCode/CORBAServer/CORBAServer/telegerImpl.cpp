#include "telegerImpl.h"


using namespace std;

void telegerImpl::startSQLConnector()
{
	//Initialize the sql connector
	connector = new SQLConnector;
	connector->startConnector();
}

::CORBA::Boolean telegerImpl::_cxx_register(const teleger::User& userData)
{
	if(!connector->registerNewUser(userData))
		return false;
	else
		return true;

}



userFriends * telegerImpl::logIn(const char * userId, const char * userPassword, const char * ip, ::teleger::ClientInterface_ptr client)
{
	if (client->_is_nil()) {
		cout << "Cliente vacio!!!" << endl;
	}
	else {
		cout << userId << endl;

		client->notifyConnection(* new SafeUser);
	}
	userFriends * test = new userFriends;
	test->length(2);
	SafeUser * testUser = new SafeUser;
	testUser->name = "pene";
	(*test)[0] = *(testUser);
return		test;
}

teleger::userFriends* logIn(const char* userId, const char* userPassword, const char* ip,ClientInterface_ptr client){
	cout << "Call received!" << endl;
	return new userFriends;
}

::CORBA::Boolean telegerImpl::logOut(const char* userId, const char* userPassword)
{
	cout << "Ola!" << endl;
	return true;
}

teleger::userFriends* telegerImpl::searchNewFriends(const char* name)
{
	cout << name << endl;
	return new userFriends;
}

void telegerImpl::sendRequestForFriend(const teleger::SafeUser& user, const teleger::SafeUser& _cxx_friend)
{
}

