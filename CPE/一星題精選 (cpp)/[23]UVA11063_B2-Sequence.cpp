// CPE �@�P����D 23
// UVA11063 B2-Sequence (cpp)

#include<iostream>
using namespace std;
int main(){
	int test,time=1;	// ���� ����
	while(cin>>test){
		int b2=1;
		int a[test]={0},table[1001]={0};
		// ��J
		for(int i=0;i<test;i++)cin>>a[i];
		// �p��ðO��
		for(int i=0;i<test;i++){
			for(int j=0;j<test;j++){
				table[a[i]+a[j]]++;
				if(table[a[i]+a[j]]>2)b2=0;
			}
		}
		// ��X
		if(b2)cout<<"Case #"<<time<<": It is a B2-Sequence."<<endl<<endl;
		else cout<<"Case #"<<time<<": It is not a B2-Sequence."<<endl<<endl;
		time++;			// ���� +1
	}
}
