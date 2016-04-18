#include "stdafx.h"
#include "linkedList.h"


linkedList::linkedList()
{
	first = (struct node *)malloc(sizeof(struct node));
	first->user.id= "23184093217598021393214432";
	first->next = NULL;
}

bool linkedList::_insert(serverSideUser user, teleger::ClientInterface_ptr clientObject) {
	if (first->next == NULL) {
		struct node * addedNode;
		addedNode = (struct node *)malloc(sizeof(struct node));
		addedNode->user = user;
		addedNode->reference = teleger::ClientInterface::_duplicate(clientObject);
		addedNode->next = NULL;
		first->next = addedNode;
		return true;
	}else{
		struct node * iterator;
		struct node * addedNode;
		addedNode = (struct node *)malloc(sizeof(struct node));
		addedNode->user = user;
		addedNode->reference = teleger::ClientInterface::_duplicate(clientObject);
		addedNode->next = NULL;
		iterator = first;
		while (iterator->next != NULL) {
			if (strcmp(iterator->next->user.id, user.id) == 0) {
				iterator->next->reference= teleger::ClientInterface::_duplicate(clientObject);
				return false;
			}
			else {
				iterator = iterator->next;
			}
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
			std::cout << "borro " << iterator->next->user.id << std::endl;
			tmp = iterator->next->next;
			iterator->next->user.id = "23184093217598021393214432";
			free(iterator->next);
			iterator->next = tmp;
			return true;
		}else{
			iterator = iterator->next;
		}
			
	}
return false;
}
struct node * linkedList::search(const char * id) {
	struct node * iterator=nullptr;
	iterator = first;
	while (iterator->next != NULL && id!=nullptr) {
		if (strcmp(iterator->next->user.id, id) == 0) {
			return iterator->next;
		}
		else {
			if (iterator->next != NULL)
				iterator = iterator->next;
		}
	}
		return  first;
}
linkedList::~linkedList()
{
}
