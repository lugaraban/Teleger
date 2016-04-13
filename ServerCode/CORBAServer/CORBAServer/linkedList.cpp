#include "stdafx.h"
#include "linkedList.h"


linkedList::linkedList()
{
	first = (struct node *)malloc(sizeof(struct node));
	first->next = NULL;
}
bool linkedList::_insert(teleger::SafeUser user, teleger::ClientInterface_ptr clientObject) {
	if (first->next == NULL) {
		struct node * addedNode;
		addedNode = (struct node *)malloc(sizeof(struct node));
		addedNode->user = user;
		addedNode->clientObject = clientObject;
		first->next = addedNode;
		return true;
	}else{
		struct node * iterator;
		struct node * addedNode;
		addedNode = (struct node *)malloc(sizeof(struct node));
		iterator = first;
		while (iterator->next != NULL) {
			iterator = iterator->next;
		}
		addedNode->user = user;
		iterator->next = addedNode;
		return true;
	}
	return false;
}
bool linkedList::_delete(char * id) {
	struct node * iterator;
	struct node * tmp;
	iterator = first;
	while (iterator->next != NULL) {
		if (strcmp(iterator->next->user.id, id) == 0) {
			tmp = iterator->next->next;
			free(iterator->next);
			iterator->next = tmp;
			return 1;
		}
			
	}
	return 0;
}
teleger::SafeUser linkedList::search(char * id) {
	struct node * iterator;
	iterator = first;
	while (iterator->next != NULL) {
		if (strcmp(iterator->next->user.id, id) == 0) {
			return iterator->next->user;
		}
	}
	return * new teleger::SafeUser;
}
linkedList::~linkedList()
{
}
