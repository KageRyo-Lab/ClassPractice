// CPE 一星題精選 04
// UVA100 The 3n+1 problem (cpp)

#include<iostream>
using namespace std;
int main(){
	int n1,n2;
	while(cin>>n1>>n2){
		cout<<n1<<" "<<n2<<" ";
		if(n1>n2){					// 若 n1>n2 兩數交換
			int temp=n1;
			n1=n2;
			n2=temp;
		}
		int maxLength=0;
		for(int i=n1; i<=n2; i++){
			int n=i,length=1;
			while(true){
				if(n==1)break;		// 若 n=1 離開迴圈
				if(n%2)n=3*n+1;		// 帶入題目公式
				else n=n/2;
				length++;			// 拉茲長度 +1
			}
			maxLength=max(length,maxLength);	// 比較目前最大者與紀錄最大者並存入
		}
		cout<<maxLength<<endl;		// 印出結果
	}
}
