// CPE �@�P����D 13
// UVA272 TeX Quotes (cpp)

#include<iostream>
using namespace std;
int main(){
	char c,k=0;
	while(cin.get(c)){				// �B�z�� EOF ���� 
		if(c!='"')cout<<c; 
		else if(++k%2)cout<<"``";	// K���_�ƫh��X `` 
		else cout<<"''";
	}
}
