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
	omni_mutex mutex;

public:
	SQLConnector() {};
	void startConnector();
	bool registerNewUser(teleger::User user);
	bool login(const char * id, const  char * pass);
	~SQLConnector() {};
};

