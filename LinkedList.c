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
	Node *first = NULL;	// 首節點 
	while(true){
		// 印出串列內容 
		printf("串列內容（Content of list）=>");
		PrintList(first);
			
		// UI & 選擇 
		printf("(1)附加節點(Append new node)\n");
		printf("(2)插入節點(Insert new node)\n");
		printf("(3)刪除節點(Delete node)\n");
		printf("(0)結束(exit)=>");
        scanf("%d",&sel);
		
		// 判斷
		switch(sel){
			case 0:
				// 釋放記憶體空間 
				FreeList(first);
				return 0;
			case 1:
				printf("請輸入欲附加之資料(Input new data)=>");
				scanf("%d",&add);
				AddNode(&first, add);
				printf("\n");
				break;
			case 2:
				printf("請輸入欲插入之資料(Insert new data)=>");
				scanf("%d",&ins);
				printf("請輸入欲插入之位置(New Position)=>");
				scanf("%d",&ins_location);
				InsertNode(&first, ins_location, ins);
				printf("\n");
				break;
			case 3:
				printf("請輸入欲刪除之資料(Data to be deleted)=>");
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
// 印出串列內容 
void PrintList(Node *node){
	while(node != NULL){
		printf("%d ", node->data);
		node = node->next;
	}
	printf("\n");
}
// 釋放記憶體空間
void FreeList(Node *node){
	Node *now, *tmp;
	now = node;
	while(now != NULL){
		tmp = now;
		now = now->next;
		free(tmp);
	}
}
// 附加節點 
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
// 插入節點
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
// 刪除節點
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
