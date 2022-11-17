// CPE 一星題精選 05 
// UVA10929 You can say 11 (cpp)

#include<iostream>
using namespace std;
int main(){
	string s;
	while(cin>>s && s!="0"){		// 若字串 s 為 0 時結束
		long long sum[2]={0,0};
		for(int i=0; i<s.length(); i++){
			sum[i%2]+=s[i]-'0';		// 將字串 s 減去字元 '0'
		}
		cout<<s<<" is"<<((sum[0]-sum[1])%11?" not ":" ")<<"a multiple of 11."<<endl;
		// 奇數和 - 偶數和 , 檢查差是否為 11 之倍數
	}
}
