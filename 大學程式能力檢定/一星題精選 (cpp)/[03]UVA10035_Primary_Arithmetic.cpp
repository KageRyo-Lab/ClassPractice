// CPE �@�P�D��� 03
// UVA10035 Primary Arithmetic (cpp)

#include<iostream>
using namespace std;
int main(){
	int n1,n2;
	while(cin>>n1>>n2){
		if(n1==0 && n2==0)break;	// �� n1 �� n2 �Ҭ� 0 �ɵ���
		int carry=0,ans=0;
		while(n1!=0 || n2!=0){
			if(n1%10+n2%10+carry>=10){ // �P�_�O�_�i��A�Y�i�� ans+1
				carry=1;
				ans++;
			}else{					// �w�L�i��
				carry=0;
			}
			n1=n1/10;
			n2=n2/10;
		}
		if(ans==0)cout<<"No carry operation."<<endl;
		else if(ans==1)cout<<"1 carry operation."<<endl;
		else cout<<ans<<" carry operations."<<endl;
	}
}
