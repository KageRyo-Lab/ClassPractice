// CPE �@�P����D 22
// UVA11461 Square Numbers

#include<iostream>
#include<cmath>
using namespace std;
int main(){
	int n1,n2,ans,temp;
	while(cin>>n1>>n2){				// ��J�Ʀr1 �μƦr2
		if(n1==0)break;				// �Y�Ʀr 1 �� 0 break
		ans=0;						// ans �k�s
		for(int i=n1;i<=n2;i++){
			temp=(int)sqrt(i);		// ���l��
			if(temp*temp==i)ans++;	// ����
		}
		cout<<ans<<endl;			//�L�X���G
	}
}
