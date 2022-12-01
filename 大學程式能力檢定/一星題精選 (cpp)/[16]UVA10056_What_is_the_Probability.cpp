// CPE 一星精選題 16
// UVA10056 What is the Probability? (cpp)

#include<iostream>
#include<cmath>					// 使用函式 pow()
using namespace std;
int main(){
	int test,player,win;	//測資數 玩家數 人
	float p,s;				// 機率
	cin>>test;
	while(cin>>player>>p>>win){
		// 若 p 不為 0 , 依公式算出機率
		if(p>0.00001){
			s=p*pow(1-p,win-1)/(1-pow(1-p,player));
			printf("%.4f\n",s);	// 取到小數點後四位
		}else cout<<"0.0000"<<endl;
	}
}
