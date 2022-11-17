// CPE 一星精選題 20
// UVA10812 Beat the Spread!

#include<iostream>
using namespace std;
int main(){
	long long n,s,d,min,max;
	while(cin>>n){
		for(long long i=0; i<n; i++){
			cin>>s;
			cin>>d;
			// 檢查s-d是否為偶數或負值
			if((s-d)<0 || (s-d)%2!=0){
				cout<<"impossible"<<endl;
				continue;
			}
			min=(s-d)/2;					// 得其中一方總分
			max=s-min;		
			cout<<max<<" "<<min<<endl;		// 印出答案
		}
	}
}
