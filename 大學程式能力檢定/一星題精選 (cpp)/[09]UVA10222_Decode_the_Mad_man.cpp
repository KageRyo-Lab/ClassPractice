// CPE �@�P����D 09
// UVA10222 Decode the Mad man

#include<iostream>
#include<cstring>				// �ϥ� strchr() �禡��
using namespace std;
int main(){
	char c,list[]="`1234567890-=qwertyuiop[]\\asdfghjkl;'zxcvbnm,./";
	// �إ���L��
	while(cin.get(c)){
		c=tolower(c);			// �j�g��p�g
		char *p=strchr(list,c);	// ����
		if(p)cout<<*(p-2);		// ���w��m -2 ��
		else cout<<c;
	}
}
