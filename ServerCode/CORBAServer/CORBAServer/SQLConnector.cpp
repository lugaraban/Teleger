#include "SQLConnector.h"
using namespace std;

static int callback(void *NotUsed, int argc, char **argv, char **azColName) {
	int i;
	for (i = 0; i<argc; i++) {
		printf("%s = %s\n", azColName[i], argv[i] ? argv[i] : "NULL");
	}
	printf("\n");
	return 0;
}


void SQLConnector::startConnector() {
	rc = sqlite3_open(routeToFile, &db);
	if (rc) {
		exit(0);
	}
}

bool SQLConnector::registerNewUser(teleger::User user) {
	char * sql = (char *)malloc(200 * sizeof(char));
	strcpy(sql,"INSERT INTO USERS values('");
	strcat(sql, (&user)->id);
	strcat(sql, "' , '");
	strcat(sql, (&user)->name);
	strcat(sql, "' , '");
	strcat(sql, (&user)->password);
	strcat(sql, "' , '");
	strcat(sql, (&user)->image);
	strcat(sql, "');");
	rc = sqlite3_exec(
		db,
		sql,
		callback,
		0,
		&zErrMsg
	);
	return true;
}
