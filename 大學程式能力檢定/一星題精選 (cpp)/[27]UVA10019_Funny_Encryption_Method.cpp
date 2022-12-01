// CPE 一星精選題 27
// UVA10019 Funny Encryption Method (cpp)

#include<iostream>
#include<bitset>			// 使用 btitset<>
using namespace std;
int main(){
	int t,n;
	cin>>t;
	while(cin>>n){
		bitset<64>b(n);
		int tp,c=0;
		while(n){
			tp=n%10;
			while(tp){
				if(tp%2)c++;
				tp=tp/2;
			}
			n=n/10;
		}
		cout<<b.count()<<" "<<c<<endl;
	}
}
