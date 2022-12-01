// CPE 一星精選題 13
// UVA272 TeX Quotes (cpp)

#include<iostream>
using namespace std;
int main(){
	char c,k=0;
	while(cin.get(c)){				// 處理至 EOF 為止 
		if(c!='"')cout<<c; 
		else if(++k%2)cout<<"``";	// K為奇數則輸出 `` 
		else cout<<"''";
	}
}
