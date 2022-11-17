// CPE 一星精選題 28
// UVA10931 Parity (cpp)

#include<iostream>
using namespace std;
int main(){
	int n;
	int f[32]={1};
	for(int i=1;i<32;i++)f[i]=f[i-1]*2;
	while(cin>>n && n){
		cout<<"The parity of ";
		int flag=0;
		int count=0;
		for(int i=31;i>=0;i--){
			if(n>=f[i]&&f[i]>0){
				cout<<"1";
				n-=f[i];
				flag=1;
				count++;
			}else if(flag)cout<<"0";
		}
		cout<<" is "<<count<<" (mod 2)."<<endl;
	}
}
