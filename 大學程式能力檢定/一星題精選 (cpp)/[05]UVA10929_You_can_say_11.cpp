// CPE �@�P�D��� 05 
// UVA10929 You can say 11 (cpp)

#include<iostream>
using namespace std;
int main(){
	string s;
	while(cin>>s && s!="0"){		// �Y�r�� s �� 0 �ɵ���
		long long sum[2]={0,0};
		for(int i=0; i<s.length(); i++){
			sum[i%2]+=s[i]-'0';		// �N�r�� s ��h�r�� '0'
		}
		cout<<s<<" is"<<((sum[0]-sum[1])%11?" not ":" ")<<"a multiple of 11."<<endl;
		// �_�ƩM - ���ƩM , �ˬd�t�O�_�� 11 ������
	}
}
