#include<stdio.h>
#include<stdlib.h>
#define true 1 

typedef struct node{
	int data;
	struct node *next;
}Node;

void AddNode(Node **start, int value);
void InsertNode(Node **start, int afterInsert, int value);
void PrintList(Node *node);
void FreeList(Node *node);
void DeleteNode(Node **start, int value);

int main(){
	int sel, add, ins, ins_location, del;
	Node *first = NULL;	// ���`�I 
	while(true){
		// �L�X��C���e 
		printf("��C���e�]Content of list�^=>");
		PrintList(first);
			
		// UI & ��� 
		printf("(1)���[�`�I(Append new node)\n");
		printf("(2)���J�`�I(Insert new node)\n");
		printf("(3)�R���`�I(Delete node)\n");
		printf("(0)����(exit)=>");
        scanf("%d",&sel);
		
		// �P�_
		switch(sel){
			case 0:
				// ����O����Ŷ� 
				FreeList(first);
				return 0;
			case 1:
				printf("�п�J�����[�����(Input new data)=>");
				scanf("%d",&add);
				AddNode(&first, add);
				printf("\n");
				break;
			case 2:
				printf("�п�J�����J�����(Insert new data)=>");
				scanf("%d",&ins);
				printf("�п�J�����J����m(New Position)=>");
				scanf("%d",&ins_location);
				InsertNode(&first, ins_location, ins);
				printf("\n");
				break;
			case 3:
				printf("�п�J���R�������(Data to be deleted)=>");
				scanf("%d",&del);
				DeleteNode(&first, del);
				printf("\n");
				break;
			default:
				printf("\n");
				break;
		}
	}
}
// �L�X��C���e 
void PrintList(Node *node){
	while(node != NULL){
		printf("%d ", node->data);
		node = node->next;
	}
	printf("\n");
}
// ����O����Ŷ�
void FreeList(Node *node){
	Node *now, *tmp;
	now = node;
	while(now != NULL){
		tmp = now;
		now = now->next;
		free(tmp);
	}
}
// ���[�`�I 
void AddNode(Node **start, int value){
	Node *newNode = (Node*)malloc(sizeof(Node));
	newNode->data = value;
	newNode->next = NULL;
	if(*start == NULL){
		*start = newNode;
		return;
	}else{
		Node *now;
		now = *start;
		while(now->next != NULL){
			now = now->next;
		}
		now->next = newNode;
		return;
	}
}
// ���J�`�I
void InsertNode(Node **start, int afterInsert, int value){
	Node *now = *start;
	while(now != NULL){
		if(afterInsert == now->data){
			Node *newNode = (Node*)malloc(sizeof(Node));
			newNode->data = value;
			newNode->next = NULL;
			if(now->next == NULL){
				now->next = newNode;
				break;
			}else{
				newNode->next = now->next;
				now->next = newNode;
				break;
			}
		}
		now = now->next;
	}
}
// �R���`�I
void DeleteNode(Node **start, int value){
	Node *now = *start;
	Node *tmp;
	if(value == now->data){
		*start = now->next;
		free(now);
		return;
	}
	while(now != NULL){
		if(now->next->data == value){
			tmp = now->next;
			now->next = now->next->next;
			free(tmp);
			return;
		}
		now = now->next;
	}
}
