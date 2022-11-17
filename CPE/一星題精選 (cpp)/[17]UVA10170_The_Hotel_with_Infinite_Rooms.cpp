// CPE 一星精選題 17
// UVA10170 The Hotel with Infinite Rooms (cpp)

#include<iostream>
using namespace std;
int main(){
	long long n1,n2;		// 兩測資
	while(cin>>n1>>n2){
		long long num=0;	// num 歸零
		while(n2>num){		// 若 n2>num 則進行運算
			num+=n1;		// 若不使用 num 會超時
			n1+=1;
		}
		cout<<n1-1<<endl;	// 印出結果
	}
}
