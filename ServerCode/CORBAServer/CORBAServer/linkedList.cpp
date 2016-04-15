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
		addedNode->clientObject = teleger::ClientInterface::_duplicate(clientObject);
		addedNode->next = NULL;
		first->next = addedNode;
		return true;
	}else{
		struct node * iterator;
		struct node * addedNode;
		addedNode = (struct node *)malloc(sizeof(struct node));
		addedNode->user = user;
		addedNode->clientObject = teleger::ClientInterface::_duplicate(clientObject);
		addedNode->next = NULL;
		iterator = first;
		while (iterator->next != NULL) {
			if (strcmp(iterator->next->user.id, user.id) == 0)
				return true;
			else
				iterator = iterator->next;
		}
		iterator->next = addedNode;
		return true;
	}
	return false;
}
bool linkedList::_delete(const char * id) {
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
struct node * linkedList::search(const char * id) {
	struct node * iterator=nullptr;
	/*struct node * dummy = nullptr;
	dummy = (struct node *)malloc(sizeof(struct node));
	dummy->user = * new teleger::SafeUser();
	dummy->user.id = "23184093217598021393214432";
	dummy->next = NULL;*/

	iterator = first;
	std::cout << "id do iterator " << id << std::endl;
	while (iterator->next != NULL && id!=nullptr) {
		if (strcmp(iterator->next->user.id, id) == 0) {
			return iterator->next;
		}
		else {
			if (iterator->next != NULL)
				iterator = iterator->next;
		}
	}
	//if (strcmp(iterator->user.id, id) == 0)
	//	return iterator;
	//else
		return  first;
}
linkedList::~linkedList()
{
}
