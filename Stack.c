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
			printf("���|����\n");
		}else{
			printf("���|���e�ѤW�ӤU�G\n");
			PrintStack(first);
		}
		printf("\n");
		if(top==1){
			top=0;
		}
		// UI & SELECT 
		printf("(1)PUSH��� (2)POP��� (0)���� : ");
		scanf("%d",&sel);
		switch(sel){
			case 0:
				// EXIT 
				FreeStack(first);
				return 0;
			case 1:
				// PUSH
				printf("�п�J��PUSH����� => ");
                scanf("%d",&pushData);
				PushStack(&first,pushData);
				printf("\n");
				break;
			case 2:
				// POP
				if(first == NULL){
					printf("���|�w�g�O�Ū��F�I\n");
				}else{
					PopStack(&first);
				}
				printf("\n");
				break;
			default:
				// ERROR
				printf("�ﶵ���s�b�A�Э��s��J�I\n");
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
	printf("POP �X : %d\n",now->data);
	free(now);
	return; 
}
// �L�X���|���e 
void PrintStack(Node *node){
	while(node != NULL){
		printf("%d ", node->data);
		node = node->next;
	}
	printf("\n");
}
// ����O����Ŷ�
void FreeStack(Node *node){
	Node *now, *tmp;
	now = node;
	while(now != NULL){
		tmp = now;
		now = now->next;
		free(tmp);
	}
}
