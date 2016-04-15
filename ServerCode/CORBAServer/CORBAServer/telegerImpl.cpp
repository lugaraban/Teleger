#include "telegerImpl.h"


using namespace std;

void telegerImpl::telegerImplInit()
{
	//Initialize the sql connector
	connector = new SQLConnector();
	connector->startConnector();
//	mutex->trylock();
	//lList = onlineClient;
	//telegerImpl::mtx = mtx;
//	mutex->unlock();
}

::CORBA::Boolean telegerImpl::_cxx_register(const teleger::User& userData)
{
	bool Registered = connector->registerNewUser(userData);
	if (!Registered) {
		delete(connector);
		return false;
	}
	else {
		delete(connector);
		return true;
	}

}



userFriends * telegerImpl::logIn(const char * userId, const char * userPassword, const char * ip, ::teleger::ClientInterface_ptr client)
{

	userFriends * userFriendsArray=new userFriends();

	if (client->_is_nil()) {
		cout << "Cliente vacio!!!" << endl;
	}
	else {
		//cout << userId << endl;
		bool loginBoolean = connector->login(userId, userPassword);

		if (loginBoolean) {
			cout << "Existe!!!" << endl;
			//I create it's representation and add it to the linked list
			teleger::SafeUser  * loggedUser = new teleger::SafeUser;

			connector->getUserData(userId, userPassword, loggedUser);
			//lList->_insert(*loggedUser,client);
			//I it exist, then I return a list with their connected friends
			//cout << lList->search("Lucia").id << endl;
		}
		else{
			cout << "non existe!!" << endl;
			userFriendsArray->length(1);
			(*userFriendsArray)[0] = *(new SafeUser);
		}
	
	//client			->notifyConnection(*new SafeUser);
	}
	//test->length(2);
	//testUser->name = "pene";
	//(*test)[0] = *(testUser);*/
	//return		test;
	cout << "ola" << endl;
	return userFriendsArray;
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

