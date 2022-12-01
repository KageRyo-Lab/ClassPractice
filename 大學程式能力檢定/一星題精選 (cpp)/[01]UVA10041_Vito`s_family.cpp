// CPE 一星題精選 01
// UVA10041 Vito`s family (cpp)

#include<iostream>
#include<algorithm>				// 使用 sort() 函數用
using namespace std;
int main(){
	int test; 					// 測資數
	cin>>test;
	while(test--){
		int r;
		cin>>r;					// 親戚數
		int st[r];				// 街號數
		for(int i=0; i<r; i++){
			cin>>st[i];			// 將親戚存入街號數
		}
		sort(st,st+r);			// 排序
		int mid=r/2;			// 取中位數
		int ans=0;
		for(int i=0; i<r; i++){	// 計算 Vito 到各親戚家的總距離
			ans+=abs(st[mid]-st[i]);
		}
		cout<<ans<<endl;		// 印出結果
	}
}
