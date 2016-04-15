#pragma once
#include "stdafx.h"
//#include "telegerImpl.h"
#include "linkedList.h"
#include "../SQLite/sqlite3.h"
#include "CORBAServer.h"
#include "SQLConnector.h"
#include <unordered_map>
#include <string.h>
using namespace std;
static CORBA::ORB_ptr orb;
static sqlite3 *db;	
static char * routeToFile = "../SQLite/teleger.db";
static omni_mutex * mtx= new omni_mutex;
static linkedList * lList= new linkedList();


using namespace teleger;
//The omni_thread allow to use threads
class telegerImpl : public POA_teleger::ServerInterface
{
private:
	SQLConnector * connector;
public:
	inline telegerImpl() {};
	void telegerImplInit();
	::CORBA::Boolean _cxx_register(const teleger::User& userData);
	userFriends* logIn(const char* userId, const char* userPassword, const char* ip, ::teleger::ClientInterface_ptr client);
	::CORBA::Boolean logOut(const char* userId, const char* userPassword);
	teleger::userFriends* searchNewFriends(const char* name);
	void sendRequestForFriend(const teleger::SafeUser& user, const teleger::SafeUser& _cxx_friend);
	virtual ~telegerImpl() {};
};

void telegerImpl::telegerImplInit()
{
	connector = new SQLConnector();
	connector->startConnector();
}

::CORBA::Boolean telegerImpl::_cxx_register(const teleger::User& userData)
{
	//mtx.try_lock();
	mtx->lock();
	bool Registered = connector->registerNewUser(userData);
	mtx->unlock();
	if (!Registered) {
		return false;
	}
	else {
		return true;
	}

}



userFriends * telegerImpl::logIn(const char * userId, const char * userPassword, const char * ip, ::teleger::ClientInterface_ptr client)
{
	userFriends * userFriendsArray = new userFriends();

	if (client->_is_nil()) {
		cout << "Cliente vacio!!!" << endl;
	}
	else {
		//mtx->lock();
		if (connector->login(userId, userPassword)) {
			//mtx->unlock();
			//I create it's representation and add it to the linked list
			teleger::SafeUser  * loggedUser = new teleger::SafeUser;
			//mtx->lock();
			connector->getUserData(userId, userPassword,&loggedUser);
			loggedUser->ip = ip;
			lList->_insert(*loggedUser,client);
			//mtx->unlock();
			//I it exist, then I return a list with their connected friends  
			char ** friends=nullptr;
			int arraySize, friendsNumber;
			//mtx->lock();
			connector->getFriendsId(userId,&friendsNumber,&arraySize,&friends);
			//mtx->unlock();
			int i = 1;
			userFriendsArray->length(friendsNumber+1);
			(*userFriendsArray)[0] = *loggedUser;
			for (i = 1; i < friendsNumber+1; i++) {
				if (strcmp(lList->search(friends[i-1])->user.id, friends[i - 1]) == 0) {
					(*userFriendsArray)[i]=(lList->search(friends[i - 1])->user);
					lList->search(friends[i - 1])->clientObject->notifyConnection(*loggedUser);
				}
			}
		}
		else {
			//mtx->unlock();
			userFriendsArray->length(1);
			SafeUser * dummyUser = new SafeUser();
			dummyUser->id = "NULL";
			(*userFriendsArray)[0] = *dummyUser;
		}
	}
	return userFriendsArray;
}


::CORBA::Boolean telegerImpl::logOut(const char* userId, const char* userPassword)
{
	mtx->lock();
	if (connector->login(userId,userPassword)) {
		lList->_delete(userId);
		mtx->unlock();
		return true;
	}
	mtx->unlock();
	return false;
}

teleger::userFriends* telegerImpl::searchNewFriends(const char* name)
{
	int searchFriends;
	teleger::userFriends * searchArray = new userFriends();
	mtx->lock();
	connector->searchNewFriends(name,&searchFriends,&searchArray);
	mtx->unlock();
	return searchArray;
}

void telegerImpl::sendRequestForFriend(const teleger::SafeUser& user, const teleger::SafeUser& _cxx_friend)
{
	//Revisar este if que non furrula ben
	if (strcmp(lList->search(_cxx_friend.id)->user.id,_cxx_friend.id)) {
		connector->insertFriendRequest(user.id, _cxx_friend.id);
		lList->search(_cxx_friend.id)->clientObject->receiveFriendRequest(user);
	}
	else {
		connector->insertFriendRequest(user.id, _cxx_friend.id);
	}
}


int main(int argc, char** argv)
{
	try {
		orb = CORBA::ORB_init(argc, argv);

		 {
			CORBA::Object_var obj = orb->resolve_initial_references("RootPOA");
			PortableServer::POA_var poa = PortableServer::POA::_narrow(obj);
			PortableServer::POAManager_var pman = poa->the_POAManager();
			telegerImpl * myserver = new telegerImpl;
			try {
				CORBA::Object_var ns_obj = orb->resolve_initial_references("NameService");
				if (!CORBA::is_nil(ns_obj)) {
					CosNaming::NamingContext_ptr nc = CosNaming::NamingContext::_narrow(ns_obj);
					CosNaming::Name name;
					name.length(1);
					name[0].id = CORBA::string_dup("TestServer");
					name[0].kind = CORBA::string_dup("");
					nc->rebind(name, myserver->_this());
					//Start the service
					myserver->telegerImplInit();
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
			pman->activate();
			orb->run();
		}

		 orb->destroy();
	}
	catch (CORBA::SystemException& ex) {
		cerr << "Caught CORBA::" << ex._name() << endl;
	}
	catch (CORBA::Exception& ex) {
		cerr << "Caught CORBA::Exception: " << ex._name() << endl;
	}
	//sqlite3_close(db);
	return 0;
}

