// CPE �@�P����D 14
// UVA12019 Doom`s Day Algorithm (cpp)

#include<iostream>
using namespace std;
int main(){
	// ���O�إ߶g��ܶg�@�ΨC��ѼƤ��}�C
	char wday[7][10]={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	int mday[]={31,28,31,30,31,30,31,31,30,31,30,31};
	// ��J����
	int test;
	cin>>test;
	while(test--){
		// ��J���
		int m,d;
		cin>>m>>d;
		int w=5;				// 2010/12/31 ���g�� , �b wday �}�C�� index = 5
		// �N index w �[�W�q 2010/12/31 ���J������Ѽư��H 7 �Y�i�o�쵪��
		for(int i=1;i<m;i++){
			w+=mday[i-1];
		}
		w=(w+d)%7;
		cout<<wday[w]<<endl;	// ��X���G
	}
}
