#include "stdafx.h"
#include "telegerImpl.h"

using namespace std;



::CORBA::Boolean telegerImpl::_cxx_register(const teleger::User& userData)
{
	cout << "Function called successfully!" << endl;
	return true;
}

teleger::userFriends* telegerImpl::logIn(const char* userId, const char* userPassword, const char* ip)
{
	return new userFriends;
}

::CORBA::Boolean telegerImpl::logOut(const char* userId, const char* userPassword)
{
	return true;
}

teleger::userFriends* telegerImpl::searchNewFriends(const char* name)
{
	return new userFriends;
}

void telegerImpl::sendRequestForFriend(const teleger::SafeUser& user, const teleger::SafeUser& _cxx_friend)
{
	
}