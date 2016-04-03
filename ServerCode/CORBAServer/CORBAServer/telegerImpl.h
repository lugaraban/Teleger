#pragma once
#include "stdafx.h"

using namespace teleger;

class telegerImpl : public POA_teleger::ServerInterface
{
public:
	inline telegerImpl() {};
	virtual ::CORBA::Boolean _cxx_register(const teleger::User& userData);
	virtual teleger::userFriends* logIn(const char* userId, const char* userPassword, const char* ip);
	virtual ::CORBA::Boolean logOut(const char* userId, const char* userPassword);
	virtual teleger::userFriends* searchNewFriends(const char* name);
	virtual void sendRequestForFriend(const teleger::SafeUser& user, const teleger::SafeUser& _cxx_friend);
	virtual ~telegerImpl() {};
};