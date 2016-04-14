#pragma once
#include "stdafx.h"
#include "../SQLite/sqlite3.h"

class SQLConnector
{
private:
	sqlite3 *db;
	char *zErrMsg = 0;
	char * routeToFile = "../SQLite/teleger.db";
	sqlite3_stmt * queryResult;
	omni_mutex * mutex;

public:
	SQLConnector() {};
	void startConnector(omni_mutex * mutex, sqlite3 *db);
	bool registerNewUser(teleger::User user);
	void getUserData(const char * id, const char * pass, teleger::SafeUser * user);
	bool login(const char * id, const  char * pass);
	~SQLConnector() {};
};

