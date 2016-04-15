#pragma once
#include "SQLConnector.h"
#include <string.h>
#include "CORBAServer.h"
using namespace std;


void SQLConnector::startConnector() {
	//mutex->trylock();
	//SQLConnector::mtx->
	//SQLConnector::db = db;
	//mutex->unlock();
}

bool SQLConnector::registerNewUser(teleger::User user) {
	//First It's checked if the id already exists
	char * statement = (char *)malloc(200 * (sizeof(char)));
	//statement[0]='\0';
	strcpy_s(statement,sizeof("SELECT * FROM users WHERE id = '"), "SELECT * FROM users WHERE id = '");
	strcat_s(statement,sizeof((&user)->id),(&user)->id);
	strcat(statement, "'");
	sqlite3_open("../SQLite/teleger.db", &db);
	rc=sqlite3_get_table(db, statement, &results, &nRow, &nColumn, &zErrMsg);
	sqlite3_close(db);
	free(&zErrMsg);
	if(nRow>0){
	//	cout << "O usuario existe!!" << endl;
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
		sqlite3_open(routeToFile, &db);
		sqlite3_exec(db, statement, NULL, NULL, &zErrMsg);
		sqlite3_close(db);
		//cout << "Usuario engadido de forma satisfactoria!!!" << endl;
		free(&zErrMsg);
		return true;
	}
}

void SQLConnector::getUserData(const char * id, const char * pass, teleger::SafeUser ** user)
{
	char * statement = (char *)malloc(200 * (sizeof(char)));
	//statement[0] = '\0';
	strcpy(statement, "SELECT * FROM users WHERE id = '");
	strcat(statement, id);
	strcat(statement, "' AND password = '");
	strcat(statement, pass);
	strcat(statement, "'");
	sqlite3_open(routeToFile, &db);
	rc = sqlite3_get_table(db, statement, &results, &nRow, &nColumn, &zErrMsg);
	sqlite3_close(db);
	(*user)->id = results[4];
	(*user)->name = results[6];
	(*user)->image = results[7];
}

bool SQLConnector::login(const char * id, const  char * pass)
{
	char * statement = (char *)malloc(200 * (sizeof(char)));
	//statement[0] = '\0';
	strcpy(statement, "SELECT * FROM users WHERE id = '");
	strcat(statement, id);
	strcat(statement, "' AND password = '");
	strcat(statement, pass);
	strcat(statement,"'");
	cout << statement << endl;
	sqlite3_open(routeToFile, &db);
	rc = sqlite3_get_table(db, statement, &results, &nRow, &nColumn, &zErrMsg);
	sqlite3_close(db);
	if (nRow>0) 
		return true;
	else
		return false;
}

void SQLConnector::getFriendsId(const char * userName,int *friendNumber,int *arraySize,char*** friendsArray)
{
	char * statement = (char *)malloc(200 * (sizeof(char)));
	//statement[0] = '\0';
	strcpy(statement, "SELECT idFriend0,idFriend1 FROM friends WHERE idFriend0 = '");
	strcat(statement, userName);
	strcat(statement, "' OR idFriend1 = '");
	strcat(statement, userName);
	strcat(statement, "'");
	sqlite3_open(routeToFile, &db);
	rc = sqlite3_get_table(db, statement, &results, &nRow, &nColumn, &zErrMsg);
	*friendsArray = (char**)malloc(strlen(*results)*sizeof(char));
	*friendsArray[0] = '\0';
	int i,z=0;
	for (i = 2; i < (nRow+1)*nColumn; i += 2) {
		if (strcmp(userName, results[i]) == 0)
			*friendsArray[z] = results[i + 1];
		else
			*friendsArray[z] = results[i];
		//cout << *(friendsArray)[z] << endl;
		z++;
	}
	sqlite3_close(db);
	*friendNumber = z;
}

void SQLConnector::searchNewFriends(const char * userName, int * friendNumber, teleger::userFriends ** friendsArray)
{
	char * statement = (char *)malloc(200 * (sizeof(char)));
	statement[0] = '\0';
	strcpy(statement, "SELECT * FROM users WHERE id like '");
	strcat(statement, userName);
	strcat(statement, "' OR name like '");
	strcat(statement, userName);
	strcat(statement, "'");
	sqlite3_open(routeToFile, &db);
	rc = sqlite3_get_table(db, statement, &results, &nRow, &nColumn, &zErrMsg);
	std::cout << "erro " << rc << std::endl;
	int i, z = 0;
	(*friendsArray)->length(nRow+1);
	for (i = 4; i < (nRow + 1)*nColumn; i+=4) {
		(*friendsArray)->get_buffer()[i - 4].id = results[i];
		(*friendsArray)->get_buffer()[i - 4].id = results[i+2];
		(*friendsArray)->get_buffer()[i - 4].id = results[i+3];
		z++;
	}
	sqlite3_close(db);
	*friendNumber = z;
}

void SQLConnector::getFriendRequests(const char * userName, int * friendNumber, teleger::userFriends ** friendsArray)
{
	char * statement = (char *)malloc(200 * (sizeof(char)));
	//statement[0] = '\0';
	strcpy(statement, "select users.id, users.name, users.image from users left join pendSol where users.id = '");
	strcat(statement, userName);
	strcat(statement, "'");
	sqlite3_open(routeToFile, &db);
	rc = sqlite3_get_table(db, statement, &results, &nRow, &nColumn, &zErrMsg);
	std::cout << "erro " << rc << std::endl;
	int i, z = 0;
	(*friendsArray)->length(nRow + 1);
	for (i = 3; i < (nRow + 1)*nColumn; i += 3) {
		(*friendsArray)->get_buffer()[i - 3].id = results[i];
		(*friendsArray)->get_buffer()[i - 3].id = results[i + 1];
		(*friendsArray)->get_buffer()[i - 3].id = results[i + 2];
		z++;
	}
	sqlite3_close(db);
	*friendNumber = z;
}

void SQLConnector::insertFriendRequest(const char * solicitor, const char * requested)

{
	char * statement = (char *)malloc(200 * (sizeof(char)));
	statement[0] = '\0';
	//If it doesn't the user is added to the database
	strcpy(statement, "INSERT INTO pendSol values('");
	strcat(statement, solicitor);
	strcat(statement, "' , '");
	strcat(statement, requested);
	strcat(statement, "');");
	sqlite3_prepare(db, statement, -1, &queryResult, NULL);
	sqlite3_open(routeToFile, &db);
	sqlite3_exec(db, statement, NULL, NULL, &zErrMsg);
	sqlite3_close(db);
	free(&zErrMsg);
}
