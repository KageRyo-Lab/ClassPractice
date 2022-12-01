// CPE 一星精選題 09
// UVA10222 Decode the Mad man

#include<iostream>
#include<cstring>				// 使用 strchr() 函式用
using namespace std;
int main(){
	char c,list[]="`1234567890-=qwertyuiop[]\\asdfghjkl;'zxcvbnm,./";
	// 建立鍵盤表
	while(cin.get(c)){
		c=tolower(c);			// 大寫轉小寫
		char *p=strchr(list,c);	// 指標
		if(p)cout<<*(p-2);		// 指定位置 -2 格
		else cout<<c;
	}
}
