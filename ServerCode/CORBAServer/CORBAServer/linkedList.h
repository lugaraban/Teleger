#pragma once
#include "stdafx.h"
struct node {
	teleger::SafeUser user;
	teleger::ClientInterface_ptr clientObject;
	struct node * next;
};
class linkedList
{
private:
	struct node * first;
public:
	linkedList();
	bool _insert(teleger::SafeUser, teleger::ClientInterface_ptr clientObject);
	bool _delete(const char * id);
	struct node * search(const char * id);
	~linkedList();
};

