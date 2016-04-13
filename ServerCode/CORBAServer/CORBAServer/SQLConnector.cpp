#include "SQLConnector.h"
using namespace std;


void SQLConnector::startConnector() {
	if (sqlite3_open(routeToFile, &db)) {
		cout << "erro!" << endl;
		exit(0);
	}
}

bool SQLConnector::registerNewUser(teleger::User user) {
	//First It's checked if the id already exists
	char * statement = (char *)malloc(200 * (sizeof(char)));
	strcat(statement,"SELECT id FROM users WHERE id like '");
	strcat(statement, (&user)->id);
	strcat(statement, "'");
	sqlite3_prepare(db, statement, -1, &queryResult, NULL);
	mutex.trylock();
	sqlite3_step(queryResult);
	mutex.unlock();
	if (sqlite3_column_text(queryResult, 0)!=NULL) {
		cout << "O usuario existe!!" << endl;
		return false;
	}
	else {
		//If it doesn't the user is added to the database
		strcpy(statement, "INSERT INTO USERS values('");
		strcat(statement, (&user)->id);
		strcat(statement, "' , '");
		strcat(statement, (&user)->name);
		strcat(statement, "' , '");
		strcat(statement, (&user)->password);
		strcat(statement, "' , '");
		strcat(statement, (&user)->image);
		strcat(statement, "');");
		sqlite3_prepare(db, statement, -1, &queryResult, NULL);
		mutex.trylock();
		sqlite3_step(queryResult);
		mutex.unlock();
		//cout << "Usuario engadido de forma satisfactoria!!!" << endl;
		return true;
	}
}

bool SQLConnector::login(const char * id, const  char * pass)
{
	char * statement = (char *)malloc(200 * (sizeof(char)));
	strcat(statement, "SELECT id FROM users WHERE id like '");
	strcat(statement, id);
	strcat(statement, "' AND password = '");
	strcat(statement, pass);
	strcat(statement, "'");
	sqlite3_prepare(db, statement, -1, &queryResult, NULL);
	mutex.trylock();
	sqlite3_step(queryResult);
	mutex.unlock();
	if (sqlite3_column_text(queryResult, 0) != NULL) {
		return true;
	}
	else {
		return false;
	}
}
