#include "SQLConnector.h"
#include <string.h>
using namespace std;


void SQLConnector::startConnector(omni_mutex * mutex, sqlite3 *db) {
	mutex->trylock();
	SQLConnector::mutex = mutex;
	SQLConnector::db = db;
	mutex->unlock();
}

bool SQLConnector::registerNewUser(teleger::User user) {
	//First It's checked if the id already exists
	char * statement = (char *)malloc(200 * (sizeof(char)));
	int rc;
	strcat(statement, "SELECT id FROM users WHERE id like '");
	strcat(statement, (&user)->id);
	strcat(statement, "'");
	mutex->trylock();
	//sqlite3_prepare(db, statement, -1, &queryResult, NULL);
	//sqlite3_step(queryResult);
	//rc = sqlite3_exec(db, statement, NULL, NULL, &zErrMsg);
	char **results;
	int nRow=4,  nColumn=2;
	rc=sqlite3_get_table(db, statement, &results, &nRow, &nColumn, &zErrMsg);
	cout << "filas " << results[0] << endl;
	mutex->unlock();
	//if (sqlite3_column_text(queryResult, 0)!=NULL) {
	if(rc==SQLITE_OK){
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
		mutex->trylock();
		sqlite3_step(queryResult);
		mutex->unlock();
		cout << "Usuario engadido de forma satisfactoria!!!" << endl;
		return true;
	}
}

void SQLConnector::getUserData(const char * id, const char * pass, teleger::SafeUser * user)
{
	char * statement = (char *)malloc(200 * (sizeof(char)));
	strcat(statement, "SELECT * FROM users WHERE id = '");
	strcat(statement, id);
	strcat(statement, "' AND password = '");
	strcat(statement, pass);
	strcat(statement, "'");
	mutex->trylock();
	sqlite3_prepare(db, statement, -1, &queryResult, NULL);
	sqlite3_step(queryResult);
	mutex->unlock();
	cout << sqlite3_column_text(queryResult, 0) << endl;
//	if (sqlite3_column_text(queryResult, 0)) {
	//	user->id._ptr = const_cast<char *>(reinterpret_cast<const char*>(sqlite3_column_text(queryResult, 0)));
//		const char * tmp = reinterpret_cast<const char*>(sqlite3_column_text(queryResult, 0));
//		cout << "tmp " << tmp << endl;
//		getchar();
	//}

}

bool SQLConnector::login(const char * id, const  char * pass)
{
	//queryResult = NULL;
	char * statement = (char *)malloc(200 * (sizeof(char)));
	strcat(statement, "SELECT * FROM users WHERE id = '");
	strcat(statement, id);
	strcat(statement, "' AND password = '");
	strcat(statement, pass);
	strcat(statement, "'");
	mutex->trylock();
	sqlite3_prepare(db, statement, -1, &queryResult, NULL);
	sqlite3_step(queryResult);
	mutex->unlock();
	if (sqlite3_column_text(queryResult, 0) != NULL) 
		return true;
	else
		return false;
}
