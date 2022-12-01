// CPE 一星精選題 46
// UVA10415 Eb Alto Saxophone Player (cpp)

#include<iostream>
#include<map>
using namespace std;
int main(){
	map<char,string>a;
	a[' ']="0000000000";
	a['c']="0111001111";	// c: finger 2~4, 7~10
	a['d']="0111001110";	// d: finger 2~4, 7~9
	a['e']="0111001100";	// e: finger 2~4, 7, 8
	a['f']="0111001000";	// f: finger 2~4, 7
	a['g']="0111000000";	// g: finger 2~4
	a['a']="0110000000";	// a: finger 2, 3
	a['b']="0100000000";	// b: finger 2
	a['C']="0010000000";	// C: finger 3
	a['D']="1111001110";	// D: finger 1~4, 7~9
	a['E']="1111001100";	// E: finger 1~4, 7, 8
	a['F']="1111001000";	// F: finger 1~4, 7
	a['G']="1111000000";	// G: finger 1~4
	a['A']="1110000000";	// A: finger 1~3
	a['B']="1100000000";	// B: finger 1~2
	int test;				// 測資組數
	cin>>test;
	cin.ignore();			// 需使用getlin()讀取 , 故先清楚t後的換列字元
	while(test--){
		char c,l=' ';
		int ans[10]={};
		while(cin.get(c),c!=10){
			for(int i=0;i<10;i++){
				if(a[l][i]=='0' && a[c][i]=='1')ans[i]++;
			}
			l=c;
		}
		// 印出結果
		for(int i=0;i<10;i++){
			if(i)cout<<" ";
			cout<<ans[i];
		}
		cout<<endl;
	}
}
