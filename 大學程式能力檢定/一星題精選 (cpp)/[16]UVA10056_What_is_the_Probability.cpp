// CPE �@�P����D 16
// UVA10056 What is the Probability? (cpp)

#include<iostream>
#include<cmath>					// �ϥΨ禡 pow()
using namespace std;
int main(){
	int test,player,win;	//����� ���a�� �H
	float p,s;				// ���v
	cin>>test;
	while(cin>>player>>p>>win){
		// �Y p ���� 0 , �̤�����X���v
		if(p>0.00001){
			s=p*pow(1-p,win-1)/(1-pow(1-p,player));
			printf("%.4f\n",s);	// ����p���I��|��
		}else cout<<"0.0000"<<endl;
	}
}
