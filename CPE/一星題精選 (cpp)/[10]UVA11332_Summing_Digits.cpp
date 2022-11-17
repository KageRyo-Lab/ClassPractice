// CPE 一星題精選 10
// UVA11332 Summing Digits (cpp)

#include<iostream>
using namespace std;
int calu(long long ans){
	int sum=0;
	while(ans){
		sum=sum+ans%10;	
		ans=ans/10;				
	}
	if(sum>=10)sum=calu(sum);	// 若還是 >= 10 代回處理
	return sum;
}
int main(){
	long long n;				// 宣告變數 n 為長整數
	while(cin>>n){
		if(n==0)break;			// 若 n 為 0 結束程式
		cout<<calu(n)<<endl;	// 印出結果
	}
}
