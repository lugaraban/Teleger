#pragma once
#include "SQLConnector.h"
#include "stdafx.h"
#include "linkedList.h"

using namespace teleger;
using namespace std;
//The omni_thread allow to use threads
class telegerImpl : public POA_teleger::ServerInterface
{
private:
	linkedList * lList;
	SQLConnector * connector;
	omni_mutex * mutex;
public:
	inline telegerImpl(){};
	virtual void telegerImplInit(linkedList * onlineClient, omni_mutex * mutex, sqlite3 *db);
	virtual ::CORBA::Boolean _cxx_register(const teleger::User& userData);
	virtual userFriends* logIn(const char* userId, const char* userPassword, const char* ip, ::teleger::ClientInterface_ptr client);
	virtual ::CORBA::Boolean logOut(const char* userId, const char* userPassword);
	virtual teleger::userFriends* searchNewFriends(const char* name);
	virtual void sendRequestForFriend(const teleger::SafeUser& user, const teleger::SafeUser& _cxx_friend);
	virtual ~telegerImpl() {};
};