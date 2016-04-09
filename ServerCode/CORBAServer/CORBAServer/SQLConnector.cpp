#include "SQLConnector.h"
using namespace std;

SQLConnector::SQLConnector()
{
	rc = sqlite3_open(routeToFile, &db);
	if (rc) {
		exit(0);
	}
}

void SQLConnector::startConection() {
}

SQLConnector::~SQLConnector()
{
	sqlite3_close(db);
}
