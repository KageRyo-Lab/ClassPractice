// CPE �@�P����D 20
// UVA10812 Beat the Spread!

#include<iostream>
using namespace std;
int main(){
	long long n,s,d,min,max;
	while(cin>>n){
		for(long long i=0; i<n; i++){
			cin>>s;
			cin>>d;
			// �ˬds-d�O�_�����Ʃέt��
			if((s-d)<0 || (s-d)%2!=0){
				cout<<"impossible"<<endl;
				continue;
			}
			min=(s-d)/2;					// �o�䤤�@���`��
			max=s-min;		
			cout<<max<<" "<<min<<endl;		// �L�X����
		}
	}
}
