// CPE 一星題精選 07
// UVA10420 List of Conquests (cpp)

#include<iostream>
#include<map>
using namespace std;
int main(){
	map<string,int>country;	// 國家
	int test;				// 測資數
	string s;
	cin>>test;
	while(test--){
		cin>>s;
		country[s]++;
		getline(cin,s);		// 提出字串 s
	}
	map<string,int>::iterator i;
	for(i=country.begin(); i!=country.end(); i++){
		cout<<i->first<<" "<<i->second<<endl;	// 印出答案
	}
	country.clear();
}
