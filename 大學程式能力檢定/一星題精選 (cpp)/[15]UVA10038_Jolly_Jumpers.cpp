// CPE �@�P����D 15
// UVA10038 Jolly Jumpers (cpp)

#include<iostream>
#include<cstdlib>		// �ϥ� abs()
#include<set>			// �ϥ� set<>
using namespace std;
int main(){
	int test;					// ����(�ƦC)����
	while(cin>>test){
		int n1;
		cin>>n1;
		set<int>t;				// ���X t
		for(int i=1;i<test;i++){
			int n2;
			cin>>n2;
			int ti=abs(n1-n2);	// �������
			// �Y ti �b 1~n-1 �� , �[�J���X t
			if(ti && ti<test)t.insert(ti);
			n1=n2;
		}
		// �Y t �������ӼƬ� n-1 �h�O Jolly Jumpers
		if(t.size()==test-1)cout<<"Jolly";
		else cout<<"Not jolly";
		cout<<endl;
	}
}
