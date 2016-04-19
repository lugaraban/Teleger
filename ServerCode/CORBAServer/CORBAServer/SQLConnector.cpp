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
	char * statement;
	statement = (char *)malloc(200 * (sizeof(char)));
	strcpy(statement, "SELECT * FROM users WHERE id = '");
	strcat(statement,(&user)->id);
	strcat(statement, "'");
	sqlite3_open("../SQLite/teleger.db", &db);
	rc=sqlite3_get_table(db, reinterpret_cast<const char *>(statement), &results, &nRow, &nColumn, &zErrMsg);
	sqlite3_close(db);
	free(&zErrMsg);
	free(statement);
	if(nRow>0){
	//	cout << "O usuario existe!!" << endl;
		return false;
	}
	else {
		//If it doesn't the user is added to the database
		strcpy(statement, "INSERT INTO USERS values('");
		strcat(statement, (&user)->id);
		strcat(statement, "' , '");
		strcat(statement, (&user)->password);
		strcat(statement, "' , '");
		strcat(statement, (&user)->name);
		strcat(statement, "' , '");
		strcat(statement, (&user)->image);
		strcat(statement, "');");
		//sqlite3_prepare(db, reinterpret_cast<const char *>(statement), -1, &queryResult, NULL);
		sqlite3_open(routeToFile, &db);
		sqlite3_exec(db, reinterpret_cast<const char *>(statement), NULL, NULL, &zErrMsg);
		sqlite3_close(db);
		//cout << "Usuario engadido de forma satisfactoria!!!" << endl;
		free(&zErrMsg);
		free(statement);
		return true;
	}
}

void SQLConnector::getUserData(const char * id, const char * pass, serverSideUser ** user)
{
	char * statement;
	statement = (char *)malloc(200 * (sizeof(char)));
	strcpy(statement, "SELECT * FROM users WHERE id = '");
	strcat(statement, id);
	strcat(statement, "' AND password = '");
	strcat(statement, pass);
	strcat(statement, "'");
	sqlite3_open(routeToFile, &db);
	rc = sqlite3_get_table(db, reinterpret_cast<const char *>(statement), &results, &nRow, &nColumn, &zErrMsg);
	sqlite3_close(db);
	free(&zErrMsg);
	free(statement);
	(*user)->id = results[4];
	(*user)->name = results[6];
	(*user)->image = results[7];
}

bool SQLConnector::login(const char * id, const  char * pass)
{
	char * statement;
	statement = (char *)malloc(200 * (sizeof(char)));
	strcpy(statement, "SELECT * FROM users WHERE id = '");
	strcat(statement, id);
	strcat(statement, "' AND password = '");
	strcat(statement, pass);
	strcat(statement,"'");
	cout << statement << endl;
	sqlite3_open(routeToFile, &db);
	rc = sqlite3_get_table(db, reinterpret_cast<const char *>(statement), &results, &nRow, &nColumn, &zErrMsg);
	sqlite3_close(db);
	free(&zErrMsg);
	free(statement);
	if (nRow>0) 
		return true;
	else
		return false;
}

void SQLConnector::getFriendsId(const char * userName, int *friendNumber, int *arraySize, serverSideUser ** friendsArray)
{
	char * statement;
	statement = (char *)malloc(200 * (sizeof(char)));
	strcpy(statement, "SELECT idFriend0,idFriend1 FROM friends WHERE idFriend0 = '");
	strcat(statement, userName);
	strcat(statement, "' OR idFriend1 = '");
	strcat(statement, userName);
	strcat(statement, "'");
	sqlite3_open(routeToFile, &db);
	rc = sqlite3_get_table(db, reinterpret_cast<const char *>(statement), &results, &nRow, &nColumn, &zErrMsg);
	//*friendsArray = (char**)malloc(nRow*nColumn*sizeof(struct serverSideUser));
	*friendsArray = (struct serverSideUser *)malloc(nRow*nColumn * sizeof(struct serverSideUser)+sizeof(struct serverSideUser));
	int i,z=0;
	cout << "numero de filas" << nRow << endl;
	if(nRow>0)
	for (i = 2; i < (nRow*nColumn)+2; i += 1) {
		if (strcmp(userName, results[i]) != 0) {
			cout << "amigo " << results[i] << endl;
			(*friendsArray)[z].id = results[i];
			z++;
		}
		
	}
	sqlite3_close(db);
	if (z > 0)
		*friendNumber = z;
	else
		*friendNumber = 0;
	free(&zErrMsg);
	free(statement);
}

void SQLConnector::searchNewFriends(const char * userName, int * friendNumber, teleger::userFriends ** friendsArray)
{
	char * statement;
	statement = (char *)malloc(200 * (sizeof(char)));
	//statement[0] = '\0';
	strcpy(statement, "SELECT * FROM users WHERE id like '%");
	strcat(statement, userName);
	strcat(statement, "%' OR name like '%");
	strcat(statement, userName);
	strcat(statement, "%'");
	sqlite3_open(routeToFile, &db);
	rc = sqlite3_get_table(db, reinterpret_cast<const char *>(statement), &results, &nRow, &nColumn, &zErrMsg);
	sqlite3_close(db);
	free(&zErrMsg);
	free(statement);
	std::cout << "erro " << rc << std::endl;
	int i, z = 0;
	(*friendsArray)->length(nRow);
	cout << "length " << nRow + 1 << endl;
	int size = (nColumn*nRow + 1);
	for (i = 4; i < size; i+=4) {
		std::cout << "i " << i << std::endl;
		(*friendsArray)->get_buffer()[z].id = results[i];
		(*friendsArray)->get_buffer()[z].name = results[i+2];
		(*friendsArray)->get_buffer()[z].image = results[i+3];
		z++;
	}

	*friendNumber = z;
}

void SQLConnector::getFriendRequests(const char * userName, int * friendNumber, serverSideUser ** friendsArray)
{
	char * statement;
	statement = (char *)malloc(200 * (sizeof(char)));
	//statement[0] = '\0';
	strcpy(statement, "select users.id, users.name, users.image from users,pendSol where users.id = pendSol.applicant AND pendSol.requested='");
	strcat(statement, userName);
	strcat(statement, "'");
	sqlite3_open(routeToFile, &db);
	rc = sqlite3_get_table(db, reinterpret_cast<const char *>(statement), &results, &nRow, &nColumn, &zErrMsg);
	std::cout << "erro " << rc << std::endl;
	int i, z = 0;
	//(*friendsArray)->length(nColumn*nRow + 1);
	(*friendsArray) = (struct serverSideUser *)malloc(nRow*nColumn*sizeof(struct serverSideUser));
	for (i = 3; i < (nRow + 3); i += 3) {
		(*friendsArray)[i - 3].id = results[i];
		(*friendsArray)[i - 3].name = results[i + 1];
		(*friendsArray)[i - 3].image = results[i + 2];
		z++;
	}
	sqlite3_close(db);
	*friendNumber = z;
	free(&zErrMsg);
	free(statement);
}

void SQLConnector::insertFriendRequest(const char * solicitor, const char * requested)

{
	char * statement;
	statement = (char *)malloc(200 * (sizeof(char)));
	//If it doesn't the user is added to the database
	strcpy(statement, "INSERT INTO pendSol values('");
	strcat(statement, solicitor);
	strcat(statement, "' , '");
	strcat(statement, requested);
	strcat(statement, "');");
	//sqlite3_prepare(db, statement, -1, &queryResult, NULL);
	sqlite3_open(routeToFile, &db);
	sqlite3_exec(db, reinterpret_cast<const char *>(statement), NULL, NULL, &zErrMsg);
	sqlite3_close(db);
	free(&zErrMsg);
	free(statement);
}

void SQLConnector::updatePass(const char * user, const char * _cxx_new)
{
	char * statement;
	statement = (char *)malloc(200 * (sizeof(char)));
	//If it doesn't the user is added to the database
	strcpy(statement, "UPDATE users SET password='");
	strcat(statement, _cxx_new);
	strcat(statement, "' where id='");
	strcat(statement, user);
	strcat(statement, "'");
	sqlite3_open(routeToFile, &db);
	sqlite3_exec(db, reinterpret_cast<const char *>(statement), NULL, NULL, &zErrMsg);
	sqlite3_close(db);
	free(&zErrMsg);
	free(statement);
}

void SQLConnector::removePetition(const char * _cxx_friend, const char * connectedUser)
{
	char * statement;
	statement = (char *)malloc(200 * (sizeof(char)));
	//If it doesn't the user is added to the database
	strcpy(statement, "DELETE FROM pendSol where applicant='");
	strcat(statement, _cxx_friend);
	strcat(statement, "' and requested='");
	strcat(statement, connectedUser);
	strcat(statement, "'");
	sqlite3_open(routeToFile, &db);
	sqlite3_exec(db, reinterpret_cast<const char *>(statement), NULL, NULL, &zErrMsg);
	sqlite3_close(db);
	free(&zErrMsg);
	free(statement);
}

void SQLConnector::addFriend(const char * connectedUser, const char * _cxx_friend)
{
	char * statement;
	statement = (char *)malloc(200 * (sizeof(char)));
	//If it doesn't the user is added to the database
	strcpy(statement, "INSERT INTO Friends values('");
	strcat(statement, _cxx_friend);
	strcat(statement, "','");
	strcat(statement, connectedUser);
	strcat(statement, "')");
	sqlite3_open(routeToFile, &db);
	sqlite3_exec(db, reinterpret_cast<const char *>(statement), NULL, NULL, &zErrMsg);
	sqlite3_close(db);
	free(&zErrMsg);
	free(statement);
}

void SQLConnector::deleteUser(const char * userId)
{
	char * statement;
	statement = (char *)malloc(200 * (sizeof(char)));
	strcpy(statement, "DELETE FROM friends WHERE idFriend0='");
	strcat(statement, userId);
	strcat(statement, "' OR idFriend1='");
	strcat(statement, userId);
	strcat(statement, "'");
	sqlite3_open(routeToFile, &db);
	sqlite3_exec(db, reinterpret_cast<const char *>(statement), NULL, NULL, &zErrMsg);
	strcpy(statement, "DELETE FROM pendSol WHERE applicant='");
	strcat(statement, userId);
	strcat(statement, "' OR requested='");
	strcat(statement, userId);
	strcat(statement, "'");
	sqlite3_exec(db, reinterpret_cast<const char *>(statement), NULL, NULL, &zErrMsg);
	statement = (char *)malloc(200 * (sizeof(char)));
	strcpy(statement, "DELETE FROM users WHERE id='");
	strcat(statement, userId);
	strcat(statement, "'");
	sqlite3_exec(db, reinterpret_cast<const char *>(statement), NULL, NULL, &zErrMsg);
	sqlite3_close(db);
	free(&zErrMsg);
	free(statement);
}
