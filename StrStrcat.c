#include<stdio.h>
#include<string.h>

void strcat(char S1[], char S2[]);

// �D�{��
int main(){
	char s1[]="Data";
	char s2[]="Structure";
	strcat(s1,s2);
	return 0;
}

// strcat
void strcat(char S1[], char S2[]){
	// �������ҼҶ�
	int S1_len=strlen(S1);
	// �ƻs�Ҷ�
	int i;
	for(i=S1_len;S2[i-S1_len]!='\0';i++){
		S1[i]=S2[i-S1_len];
	}
	S1[i]='\0';
	// ��X
	printf(S1);
}
