// CPE �@�P����D 25
// UVA10093 An Easy Problem! (cpp)

#include<iostream>
#include<algorithm>
using namespace std;
int main(){
	string num;
	//0~9 A~Z a~z
	string list="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	while(cin>>num){
		//�N 0 ��J 62 �i�줤�� 61�Ʀr
		for(int i=0; i<num.size(); i++){
			num[i]=list.find(num[i]);
			num[i]=max(0,(int)num[i]);
		}
		// �̤j�r�� +1 �@���̤p�_�� n ��
		int n=*max_element(num.begin(),num.end())+1;
		n=max(n,2);
		// �p�� n �i�줤 num ���u��ƭȹ� (n-1) ���l�� (rsd)
		for(; n<=62; n++){
			int r=0;
			for(int i=0; i<num.size(); i++){
				r=r*n+num[i];
				r%=n-1;
			}
			if(r==0)break;	// �l�Ƭ� 0 ���}�j��
		}
		if(n<=62)cout<<n;
		else cout<<"such number is impossible!";
		cout<<endl;
	}
}
