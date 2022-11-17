// CPE 一星精選題 19
// UVA10783 Odd Sum (cpp)

#include<iostream>
using namespace std;
int main(){
	int test,start,end,ans;			// 測資 起始 結束 結果
	while(cin>>test){
		for(int i=1;i<=test;i++){	// 從 1 開始往上 +
			ans=0;					// 歸零
			cin>>start;				// 輸入起始
			cin>>end;				// 輸入結束
			int j=start;			// 將 start 存入 j
			if(j%2==0)j++;			// j 整除 -> j++
			for(; j<=end; j+=2)ans+=j;
			cout<<"Case "<<i<<": "<<ans<<endl;
		}
	}
}
