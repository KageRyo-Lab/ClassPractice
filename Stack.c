#include<stdio.h>
#include<stdlib.h>
#define true 1 
#define false 0

typedef struct node{
	int data;
	struct node *next;
}Node;

void PushStack(Node **start, int value);
void PopStack(Node **start);
void PrintStack(Node *node);
void FreeStack(Node *node);

int main(){
	int sel,pushData,top=1;
	Node *first=NULL;
	while(true){
		// PRINT
		if(first == NULL){
			printf("堆疊為空\n");
		}else{
			printf("堆疊內容由上而下：\n");
			PrintStack(first);
		}
		printf("\n");
		if(top==1){
			top=0;
		}
		// UI & SELECT 
		printf("(1)PUSH資料 (2)POP資料 (0)結束 : ");
		scanf("%d",&sel);
		switch(sel){
			case 0:
				// EXIT 
				FreeStack(first);
				return 0;
			case 1:
				// PUSH
				printf("請輸入欲PUSH的資料 => ");
                scanf("%d",&pushData);
				PushStack(&first,pushData);
				printf("\n");
				break;
			case 2:
				// POP
				if(first == NULL){
					printf("堆疊已經是空的了！\n");
				}else{
					PopStack(&first);
				}
				printf("\n");
				break;
			default:
				// ERROR
				printf("選項不存在，請重新輸入！\n");
				printf("\n");
				break;
		}
	}
}
// PUSH
void PushStack(Node **start, int value){
	Node *newNode = (Node*)malloc(sizeof(Node));
	newNode->data = value;
	newNode->next = NULL;
	if(*start == NULL){
		*start = newNode;
		return;
	}else{
		newNode->next = *start;
		*start = newNode;
		return;
	}
}
// POP
void PopStack(Node **start){
	Node *now = *start;
	*start = now->next;
	printf("POP 出 : %d\n",now->data);
	free(now);
	return; 
}
// 印出堆疊內容 
void PrintStack(Node *node){
	while(node != NULL){
		printf("%d ", node->data);
		node = node->next;
	}
	printf("\n");
}
// 釋放記憶體空間
void FreeStack(Node *node){
	Node *now, *tmp;
	now = node;
	while(now != NULL){
		tmp = now;
		now = now->next;
		free(tmp);
	}
}
