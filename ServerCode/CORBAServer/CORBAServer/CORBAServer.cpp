#pragma once
#include "stdafx.h"
//#include "telegerImpl.h"
#include "linkedList.h"
#include "../SQLite/sqlite3.h"
#include "CORBAServer.h"
#include "SQLConnector.h"
#include <list>
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
	teleger::userFriends* logIn(const char* userId, const char* userPassword, teleger::ClientInterface_ptr client);
	::CORBA::Boolean logOut(const char* userId, const char* userPassword);
	teleger::userFriends* searchNewFriends(const char* name);
	void sendRequestForFriend(const teleger::SafeUser& user, const char* _cxx_friend);
	void notifyAnswerRequest(const char* connectedUser, const char* pass, const char* _cxx_friend, ::CORBA::Boolean acceptance);
	::CORBA::Boolean changePassword(const char* old, const char* _cxx_new, const char* user);
	virtual ~telegerImpl() {};
};

void telegerImpl::telegerImplInit()
{
	mtx->lock();
	connector = new SQLConnector();
	connector->startConnector();
	mtx->unlock();
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



userFriends * telegerImpl::logIn(const char * userId, const char * userPassword, ::teleger::ClientInterface_ptr client)
{
	mtx->lock();
	userFriends * userFriendsArray = new userFriends();
	serverSideUser * userFriendsSol = new serverSideUser();
	if (client->_is_nil()) {
		cout << "Cliente vacio!!!" << endl;
	}
	else {
		if (connector->login(userId, userPassword)) {
			//I create it's representation and add it to the linked list
			//teleger::SafeUser  * loggedUser = new teleger::SafeUser;
			struct serverSideUser * loggedUser;
			loggedUser = (struct serverSideUser *)malloc(sizeof(struct serverSideUser));
			connector->getUserData(userId, userPassword, &loggedUser);
			lList->_insert(*loggedUser, client);
			//I it exist, then I return a list with their connected friends  
			char ** friends = nullptr;
			int arraySize, friendsNumber;
			connector->getFriendsId(userId, &friendsNumber, &arraySize, &friends);
			int i = 1;
			userFriendsArray->length(friendsNumber+1);
			(*userFriendsArray)[0].id = loggedUser->id;
			(*userFriendsArray)[0].image = loggedUser->image;
			(*userFriendsArray)[0].name = loggedUser->name;
			(*userFriendsArray)[0].reference = teleger::ClientInterface::_duplicate(client);
			for (i = 1; i < userFriendsArray->length(); i++) {
				if (lList->search(friends[i - 1])->user.id != NULL)
					if (strcmp(lList->search(friends[i - 1])->user.id, friends[i - 1]) == 0) {
						(*userFriendsArray)[i].id = (lList->search(friends[i - 1])->user).id;
						(*userFriendsArray)[i].image = (lList->search(friends[i - 1])->user).image;
						(*userFriendsArray)[i].name = (lList->search(friends[i - 1])->user).name;
						(*userFriendsArray)[i].reference = (lList->search(friends[i - 1])->reference);
						(*userFriendsArray)[i].reference->notifyConnection((*userFriendsArray)[0]);
					}
			}
			////Friends solitudes
			connector->getFriendRequests(userId, &friendsNumber, &userFriendsSol);
			cout << "friends number " << friendsNumber << endl;
			for (i = 0; i < friendsNumber; i++) {
				cout << userFriendsSol[i].id << endl;
				client->receiveFriendRequest((userFriendsSol[i]).id);
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
	mtx->release();
	return userFriendsArray;
}


::CORBA::Boolean telegerImpl::logOut(const char* userId, const char* userPassword)
{
	mtx->lock();
	if (connector->login(userId, userPassword)) {
		lList->_delete(userId);
		mtx->unlock();
		return true;
	}
	mtx->unlock();
	return false;
}

teleger::userFriends* telegerImpl::searchNewFriends(const char* name)
{
	cout << "nome -> " << name << endl;
	int searchFriends;
	mtx->lock();
	teleger::userFriends * searchArray = new userFriends();
	connector->searchNewFriends(name, &searchFriends, &searchArray);
	cout << "numero de busca " << searchArray->length() << endl;
	mtx->unlock();
	return searchArray;
}

void telegerImpl::sendRequestForFriend(const teleger::SafeUser& user, const char * _cxx_friend)
{
	mtx->lock();
	if (strcmp(lList->search(_cxx_friend)->user.id, _cxx_friend) == 0) {
		connector->insertFriendRequest(user.id, _cxx_friend);
		lList->search(_cxx_friend)->reference->receiveFriendRequest(user.id);
	}
	else {
		connector->insertFriendRequest(user.id, _cxx_friend);
	}
	mtx->unlock();
}

void telegerImpl::notifyAnswerRequest(const char * connectedUser, const char * pass, const char * _cxx_friend, ::CORBA::Boolean acceptance)
{
	mtx->lock();
	cout << connectedUser << endl;
	cout << pass << endl;
	cout << _cxx_friend << endl;
	cout << acceptance << endl;
	if (connector->login(connectedUser, pass)) {
			connector->removePetition(_cxx_friend, connectedUser);
			if (acceptance) {
				connector->addFriend(connectedUser, _cxx_friend);
			if (strcmp(lList->search(_cxx_friend)->user.id, _cxx_friend) == 0){
				SafeUser selfUser;
				SafeUser friendUser;
				selfUser.id = lList->search(connectedUser)->user.id;
				selfUser.image = lList->search(connectedUser)->user.image;
				selfUser.name = lList->search(connectedUser)->user.name;
				selfUser.reference = lList->search(connectedUser)->reference;
				friendUser.id = lList->search(_cxx_friend)->user.id;
				friendUser.image = lList->search(_cxx_friend)->user.image;
				friendUser.name = lList->search(_cxx_friend)->user.name;
				friendUser.reference = lList->search(_cxx_friend)->reference;
				selfUser.reference->notifyConnection(friendUser);
				friendUser.reference->notifyConnection(selfUser);
			}

		}
	}
	mtx->unlock();
}

::CORBA::Boolean telegerImpl::changePassword(const char * old, const char * _cxx_new, const char * user)
{	
	mtx->lock();
	if (connector->login(user,old)) {
		connector->updatePass(user, _cxx_new);
		mtx->unlock();
		return true;
	}
	mtx->unlock();
	return false;
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
					CosNaming::Name name = * new CosNaming::Name;
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
	return 0;
}

