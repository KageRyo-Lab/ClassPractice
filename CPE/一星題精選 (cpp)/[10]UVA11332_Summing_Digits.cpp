// CPE �@�P�D��� 10
// UVA11332 Summing Digits (cpp)

#include<iostream>
using namespace std;
int calu(long long ans){
	int sum=0;
	while(ans){
		sum=sum+ans%10;	
		ans=ans/10;				
	}
	if(sum>=10)sum=calu(sum);	// �Y�٬O >= 10 �N�^�B�z
	return sum;
}
int main(){
	long long n;				// �ŧi�ܼ� n �������
	while(cin>>n){
		if(n==0)break;			// �Y n �� 0 �����{��
		cout<<calu(n)<<endl;	// �L�X���G
	}
}
