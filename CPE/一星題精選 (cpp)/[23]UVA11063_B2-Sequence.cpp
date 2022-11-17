// CPE 一星精選題 23
// UVA11063 B2-Sequence (cpp)

#include<iostream>
using namespace std;
int main(){
	int test,time=1;	// 測資 次數
	while(cin>>test){
		int b2=1;
		int a[test]={0},table[1001]={0};
		// 輸入
		for(int i=0;i<test;i++)cin>>a[i];
		// 計算並記錄
		for(int i=0;i<test;i++){
			for(int j=0;j<test;j++){
				table[a[i]+a[j]]++;
				if(table[a[i]+a[j]]>2)b2=0;
			}
		}
		// 輸出
		if(b2)cout<<"Case #"<<time<<": It is a B2-Sequence."<<endl<<endl;
		else cout<<"Case #"<<time<<": It is not a B2-Sequence."<<endl<<endl;
		time++;			// 次數 +1
	}
}
