// CPE �@�P����D 19
// UVA10783 Odd Sum (cpp)

#include<iostream>
using namespace std;
int main(){
	int test,start,end,ans;			// ���� �_�l ���� ���G
	while(cin>>test){
		for(int i=1;i<=test;i++){	// �q 1 �}�l���W +
			ans=0;					// �k�s
			cin>>start;				// ��J�_�l
			cin>>end;				// ��J����
			int j=start;			// �N start �s�J j
			if(j%2==0)j++;			// j �㰣 -> j++
			for(; j<=end; j+=2)ans+=j;
			cout<<"Case "<<i<<": "<<ans<<endl;
		}
	}
}
