// CPE �@�P����D 17
// UVA10170 The Hotel with Infinite Rooms (cpp)

#include<iostream>
using namespace std;
int main(){
	long long n1,n2;		// �����
	while(cin>>n1>>n2){
		long long num=0;	// num �k�s
		while(n2>num){		// �Y n2>num �h�i��B��
			num+=n1;		// �Y���ϥ� num �|�W��
			n1+=1;
		}
		cout<<n1-1<<endl;	// �L�X���G
	}
}
