#pragma once
#include "stdafx.h"

struct node {
	serverSideUser user;
	teleger::ClientInterface_ptr reference;
	struct node * next;
};

class linkedList
{
private:
	struct node * first;
public:
	linkedList();
	bool _insert(serverSideUser user, teleger::ClientInterface_ptr clientObject);
	bool _delete(const char * id);
	struct node * search(const char * id);
	~linkedList();
};

