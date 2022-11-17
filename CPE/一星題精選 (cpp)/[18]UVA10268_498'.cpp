// CPE 一星精選題 18
// UVA10268 498' (cpp)

#include<iostream>
using namespace std;
int main(){
	int x,y,i,n;
	int a[10000]={0};
	int b=5000;
	while(cin>>x){
		for(i=0;i<b;i++){	// 讀上面宣告的 i 
			cin>>a[i];
			if(getchar()=='\n')break;
		}
		// 代入公式進行運算
		n=i;				// 將 i 的值存到 n
		y=a[0]*n;
		for(int i=1;i<n;i++)y=y*x+a[i]*(n-i);
		cout<<y<<endl;
	}
	return 0;
} 
