// CPE �@�P�D��� 04
// UVA100 The 3n+1 problem (cpp)

#include<iostream>
using namespace std;
int main(){
	int n1,n2;
	while(cin>>n1>>n2){
		cout<<n1<<" "<<n2<<" ";
		if(n1>n2){					// �Y n1>n2 ��ƥ洫
			int temp=n1;
			n1=n2;
			n2=temp;
		}
		int maxLength=0;
		for(int i=n1; i<=n2; i++){
			int n=i,length=1;
			while(true){
				if(n==1)break;		// �Y n=1 ���}�j��
				if(n%2)n=3*n+1;		// �a�J�D�ؤ���
				else n=n/2;
				length++;			// �ԯ����� +1
			}
			maxLength=max(length,maxLength);	// ����ثe�̤j�̻P�����̤j�̨æs�J
		}
		cout<<maxLength<<endl;		// �L�X���G
	}
}
