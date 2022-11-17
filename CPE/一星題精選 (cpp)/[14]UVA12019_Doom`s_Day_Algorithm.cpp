// CPE 一星精選題 14
// UVA12019 Doom`s Day Algorithm (cpp)

#include<iostream>
using namespace std;
int main(){
	// 分別建立週日至週一及每月天數之陣列
	char wday[7][10]={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	int mday[]={31,28,31,30,31,30,31,31,30,31,30,31};
	// 輸入測資
	int test;
	cin>>test;
	while(test--){
		// 輸入月日
		int m,d;
		cin>>m>>d;
		int w=5;				// 2010/12/31 為週五 , 在 wday 陣列中 index = 5
		// 將 index w 加上從 2010/12/31 到輸入日期的天數除以 7 即可得到答案
		for(int i=1;i<m;i++){
			w+=mday[i-1];
		}
		w=(w+d)%7;
		cout<<wday[w]<<endl;	// 輸出結果
	}
}
