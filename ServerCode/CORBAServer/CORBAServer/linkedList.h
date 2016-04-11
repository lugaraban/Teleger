#pragma once
#include "stdafx.h"
struct node {
	teleger::SafeUser user;
	struct node * next;
};
class linkedList
{
private:
	struct node * first;
public:
	linkedList();
	bool _insert(teleger::SafeUser);
	bool _delete(char * id);
	teleger::SafeUser search(char * id);
	~linkedList();
};

