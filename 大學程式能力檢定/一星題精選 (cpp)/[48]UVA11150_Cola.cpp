// CPE �@�P����D 48
// UVA11150 Cola (cpp)

#include<iostream>
using namespace std;
int main(){
	int n,sum,other;	// ����� �`�� �h�l�~��
	while(cin>>n){
		sum=n;			// �ܤFn�~ , �s�J�`��
		while(n>=3){	// �ܶW�L3�~�p��I��
			other=n%3;
			n=n/3;
			sum+=n;
			n+=other;
		}
		if(n==2)sum++;	// �Y��2�~�i��B�ͭ�1�~�I�� , �Gsum+1
		cout<<sum<<endl;
	}
}
