// CPE �@�P����D 21
// UVA11349 Symmetric Matrix

#include<iostream>
using namespace std;
int main(){
	int test,n;
	int time=0;				// ��l�Ʀ��Ƭ� 0
	string s;				// �r�� s
	cin>>test;
	while(cin>>s>>s>>n){
		time++;
		int m[n*n];			// �x�}
		for(int i=0;i<n*n;i++)cin>>m[i];
		int flag=0;
		int b=n*n-1;
		for(int i=0;i<n;i++){
			if(m[i]!=m[b]||m[i]<0){
				flag=1;
				break;
			}
			b--;
		}
		if(flag==1)cout<<"Test #"<<time<<": Non-symmetric."<<endl;
		if(flag==0)cout<<"Test #"<<time<<": Symmetric."<<endl;
	}
}
