// CPE �@�P�D��� 01
// UVA10041 Vito`s family (cpp)

#include<iostream>
#include<algorithm>				// �ϥ� sort() ��ƥ�
using namespace std;
int main(){
	int test; 					// �����
	cin>>test;
	while(test--){
		int r;
		cin>>r;					// �˱���
		int st[r];				// �󸹼�
		for(int i=0; i<r; i++){
			cin>>st[i];			// �N�˱��s�J�󸹼�
		}
		sort(st,st+r);			// �Ƨ�
		int mid=r/2;			// �������
		int ans=0;
		for(int i=0; i<r; i++){	// �p�� Vito ��U�˱��a���`�Z��
			ans+=abs(st[mid]-st[i]);
		}
		cout<<ans<<endl;		// �L�X���G
	}
}
