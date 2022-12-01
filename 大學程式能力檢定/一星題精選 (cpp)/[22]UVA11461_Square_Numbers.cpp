// CPE 琍弘匡肈 22
// UVA11461 Square Numbers

#include<iostream>
#include<cmath>
using namespace std;
int main(){
	int n1,n2,ans,temp;
	while(cin>>n1>>n2){				// 块计1 の计2
		if(n1==0)break;				// 璝计 1  0 break
		ans=0;						// ans 耴箂
		for(int i=n1;i<=n2;i++){
			temp=(int)sqrt(i);		// 緇计
			if(temp*temp==i)ans++;	// キよ
		}
		cout<<ans<<endl;			//挡狦
	}
}
