// CPE �@�P�D��� 07
// UVA10420 List of Conquests (cpp)

#include<iostream>
#include<map>
using namespace std;
int main(){
	map<string,int>country;	// ��a
	int test;				// �����
	string s;
	cin>>test;
	while(test--){
		cin>>s;
		country[s]++;
		getline(cin,s);		// ���X�r�� s
	}
	map<string,int>::iterator i;
	for(i=country.begin(); i!=country.end(); i++){
		cout<<i->first<<" "<<i->second<<endl;	// �L�X����
	}
	country.clear();
}
