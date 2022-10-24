#include<stdio.h>
#include<stdlib.h>
// ���c 
typedef struct tagStackList{
	int data;
	struct tagStackList *next;
}StackList;
// �{��
int push(StackList *,int X);
int pop(StackList *,int *);
// �D�{�� 
int main(){
	// �P�_���|��ơA����ƴN�L�X��
	 
	// UI 
	printf("(1)PUSH��� (2)POP��� (0)���� : ");
}
// PUSH
int push(StackList *S,int X){
	StackList *p;
	if((p=(StackList *)malloc(sizeof(StackList))) == NULL){
		return false;
	} 
	p->data =X;
	p->next =S->next;
	S->next =p;
	return true;
}
// POP
int pop(StackList *S,int *X){
	StackList *p= S->next;
	if(p==NULL){
		return false;
	}
	*X =p->data;
	S->next =p->next;
	free(p);
	return true;
}
