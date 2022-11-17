// CPE �@�P�D��� 06
// UVA10101 Bangla Numbers (cpp)

#include<iostream>
#include<iomanip>			// �ϥ� setw() ���
using namespace std;
void sp(long long n){		// �i�H�Q�����s���������A�B�z���@�h�N�ᵹ�U�@�h
	if(n>=10000000){
		sp(n/10000000);
		cout<<" kuti";
		n=n%10000000;
	}
	if(n>=100000){
		sp(n/100000);
		cout<<" lakh";
		n=n%100000;
	}
	if(n>=1000){
		sp(n/1000);
		cout<<" hajar";
		n=n%1000;
	}
	if(n>=100){
		sp(n/100);
		cout<<" shata";
		n=n%100;
	}
	if(n){
		cout<<" "<<n;
	}
}
int main(){
	long long n,num=1;
	while(cin>>n){
		cout<<setw(4)<<num++<<".";	// �L�X�C������e�����N��
		if(n)sp(n);					// n ���ȫh�N�J sp() �i��B��
		else cout<<" 0";
		cout<<endl;					//����
	}
}
