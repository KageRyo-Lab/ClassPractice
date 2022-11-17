// CPE 一星精選題 (49)
// UVA11321 Sort! Sort!! and Sort!!! (cpp)

#include<iostream>
#include<algorithm>		// 使用 sort()
using namespace std;
int m;					// 宣告 m 全域變數
bool cp(int x,int y){	// 進行比較運算
	if(x%m==y%m){
		if(x%2==0&&y%2==0)return x<y;
		else if(x%2!=0&&y%2!=0)return x>y;
		else return y%2==0;
	}else return x%m<y%m;
}
int main(){
	int n;
	while(cin>>n>>m){	// 輸入n和m
		cout<<n<<" "<<m<<endl;
		if(n==0)break;	// 若n為0 break
		int a[n];		// 宣告陣列a
		for(int i=0; i<n; i++)cin>>a[i];
		sort(a,a+n,cp);	// 排列
		for(int i=0; i<n; i++)cout<<a[i]<<endl;
	}
}
