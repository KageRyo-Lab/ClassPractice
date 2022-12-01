// CPE �@�P�D��� 11
// UVA10252 Common Permutation (cpp)

#include<iostream>
using namespace std;
int main(){
	string s1,s2;
	while(getline(cin,s1)){				// ����J�r�� s1
		getline(cin,s2);				// �A��J�r�� s2
		int c1[26]={0};
		int c2[26]={0};
		//26�Ӧr��
		for(int i=0;i<s1.length();i++){
			s1[i]=tolower(s1[i]);		// �j�g��p�g
			c1[s1[i]-'a']++;			// ��h�r�ꤺ���r�� 'a'
		}
		for(int i=0;i<s2.length();i++){
			s2[i]=tolower(s2[i]);		// �j�g��p�g
			c2[s2[i]-'a']++;			// ��h�r�ꤺ���r�� 'a'
		}
		for(int i=0;i<26;i++){							// 26�Ӧr��
			int n=c1[i]>c2[i]?c2[i]:c1[i];				// ��� c1[i] �M c2[i]
			for(int j=0;j<n;j++)cout<<(char)(i+'a');	// �N�r�� 'a' �[�^�æL�X���G
		}
		cout<<endl;
	}
}
