// CPE 一星題精選 11
// UVA10252 Common Permutation (cpp)

#include<iostream>
using namespace std;
int main(){
	string s1,s2;
	while(getline(cin,s1)){				// 先輸入字串 s1
		getline(cin,s2);				// 再輸入字串 s2
		int c1[26]={0};
		int c2[26]={0};
		//26個字母
		for(int i=0;i<s1.length();i++){
			s1[i]=tolower(s1[i]);		// 大寫轉小寫
			c1[s1[i]-'a']++;			// 減去字串內的字元 'a'
		}
		for(int i=0;i<s2.length();i++){
			s2[i]=tolower(s2[i]);		// 大寫轉小寫
			c2[s2[i]-'a']++;			// 減去字串內的字元 'a'
		}
		for(int i=0;i<26;i++){							// 26個字母
			int n=c1[i]>c2[i]?c2[i]:c1[i];				// 比較 c1[i] 和 c2[i]
			for(int j=0;j<n;j++)cout<<(char)(i+'a');	// 將字元 'a' 加回並印出結果
		}
		cout<<endl;
	}
}
