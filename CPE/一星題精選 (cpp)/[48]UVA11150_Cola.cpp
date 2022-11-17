// CPE 琍弘匡肈 48
// UVA11150 Cola (cpp)

#include<iostream>
using namespace std;
int main(){
	int n,sum,other;	// 代戈计 羆计 緇瞺计
	while(cin>>n){
		sum=n;			// 耻n瞺 , 羆计
		while(n>=3){	// 耻禬筁3瞺璸衡传
			other=n%3;
			n=n/3;
			sum+=n;
			n+=other;
		}
		if(n==2)sum++;	// 璝2瞺蛤狟ね1瞺传 , 珿sum+1
		cout<<sum<<endl;
	}
}
