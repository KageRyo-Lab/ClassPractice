// CPE �@�P����D 18
// UVA10268 498' (cpp)

#include<iostream>
using namespace std;
int main(){
	int x,y,i,n;
	int a[10000]={0};
	int b=5000;
	while(cin>>x){
		for(i=0;i<b;i++){	// Ū�W���ŧi�� i 
			cin>>a[i];
			if(getchar()=='\n')break;
		}
		// �N�J�����i��B��
		n=i;				// �N i ���Ȧs�� n
		y=a[0]*n;
		for(int i=1;i<n;i++)y=y*x+a[i]*(n-i);
		cout<<y<<endl;
	}
	return 0;
} 
