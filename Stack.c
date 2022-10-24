#include<stdio.h>
#include<stdlib.h>
// 結構 
typedef struct tagStackList{
	int data;
	struct tagStackList *next;
}StackList;
// 程式
int push(StackList *,int X);
int pop(StackList *,int *);
// 主程式 
int main(){
	// 判斷堆疊資料，有資料就印出來
	 
	// UI 
	printf("(1)PUSH資料 (2)POP資料 (0)結束 : ");
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
