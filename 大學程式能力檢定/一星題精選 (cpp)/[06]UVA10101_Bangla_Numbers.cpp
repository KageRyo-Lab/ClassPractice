// CPE 一星題精選 06
// UVA10101 Bangla Numbers (cpp)

#include<iostream>
#include<iomanip>			// 使用 setw() 函數
using namespace std;
void sp(long long n){		// 可以想成換零錢的概念，處理完一層就丟給下一層
	if(n>=10000000){
		sp(n/10000000);
		cout<<" kuti";
		n=n%10000000;
	}
	if(n>=100000){
		sp(n/100000);
		cout<<" lakh";
		n=n%100000;
	}
	if(n>=1000){
		sp(n/1000);
		cout<<" hajar";
		n=n%1000;
	}
	if(n>=100){
		sp(n/100);
		cout<<" shata";
		n=n%100;
	}
	if(n){
		cout<<" "<<n;
	}
}
int main(){
	long long n,num=1;
	while(cin>>n){
		cout<<setw(4)<<num++<<".";	// 印出每項測資前面的代號
		if(n)sp(n);					// n 有值則代入 sp() 進行運算
		else cout<<" 0";
		cout<<endl;					//換行
	}
}
