// CPE �@�P����D 08
// UVA10008 What`s Cryptanalysis?

#include<iostream>
using namespace std;
int count[256],length;	//�p��r���X�{���Ƥβέp�ƶq��
int main(){
	char c;
	while(cin>>c)length++,count[toupper(c)]++;
	while(--length){	// Ū�� c �C�� length+1 �B���������Ƥ] +1
		for(c='A'; c<='Z'; c++){
			if(count[c]==length)cout<<c<<" "<<count[c]<<endl;
			// �Y�r���X�{���Ƶ��� length �h�L�X
		}
	}
}
