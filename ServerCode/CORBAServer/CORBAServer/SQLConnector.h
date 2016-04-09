#pragma once
#include "stdafx.h"
#include <stdlib.h>
#include <sstream>
#include <stdexcept>
#include <odbcinst.h>
#include "../SQLite/sqlite3.h"
class SQLConnector
{
private:
	sqlite3 *db;
	char *zErrMsg = 0;
	int rc;
	char * routeToFile = "../SQLite/teleger.db";
	char * queryResult;
public:
	SQLConnector() {};
	void startConnector();
	bool registerNewUser(teleger::User user);
	~SQLConnector() {};
};

