// CPE �@�P����D 29
// UVA948 Fibonaccimal Base (cpp)

#include<iostream>
using namespace std;
int main(){
	int test,n;
	// �إ߶O��ƦC
	int f[40]={0,1};
	for(int i=2;i<40;i++){
		f[i]=f[i-1]+f[i-2];
	}
	cin>>test;
	while(cin>>n){
		cout<<n<<" = ";
		int flag=0;
		// �q�̤j���O��ƶ}�l���U�B�z�� f(2) ����
		for(int i=39; i>=2; i--){
			if(n>=f[i]){
				cout<<"1";
				n-=f[i];
				flag=1;
			}else if(flag){
				cout<<"0";
			}	
		}
		cout<<" (fib)"<<endl;
	}
}
