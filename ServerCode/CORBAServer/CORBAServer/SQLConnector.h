#pragma once
#include "stdafx.h"
#include "../SQLite/sqlite3.h"
#include "CORBAServer.h"

class SQLConnector
{
private:
	sqlite3 *db;
	char *zErrMsg = 0;
	char * routeToFile = "../SQLite/teleger.db";
	sqlite3_stmt * queryResult;
	char **results;
	int nRow = 4, nColumn = 2;
	int rc;

public:
	SQLConnector() {};
	void startConnector();
	bool registerNewUser(teleger::User user);
	void getUserData(const char * id, const char * pass, serverSideUser ** user);
	bool login(const char * id, const  char * pass);
	void getFriendsId(const char * userName, int *friendNumber, int *arraySize, char*** friendsArray);
	void searchNewFriends(const char * userName, int *friendNumber, teleger::userFriends ** friendsArray);
	void getFriendRequests(const char * userName, int *friendNumber, serverSideUser  ** friendsArray);
	void insertFriendRequest(const char *solicitor,const char *requested);
	void updatePass(const char *user,const char * _cxx_new);
	void removePetition(const char* _cxx_friend,const char* connectedUser);
	void addFriend(const char* connectedUser,const char * _cxx_friend);
	~SQLConnector() {};
};

