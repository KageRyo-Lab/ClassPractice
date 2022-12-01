// CPE 一星精選題 15
// UVA10038 Jolly Jumpers (cpp)

#include<iostream>
#include<cstdlib>		// 使用 abs()
#include<set>			// 使用 set<>
using namespace std;
int main(){
	int test;					// 測資(數列)長度
	while(cin>>test){
		int n1;
		cin>>n1;
		set<int>t;				// 集合 t
		for(int i=1;i<test;i++){
			int n2;
			cin>>n2;
			int ti=abs(n1-n2);	// 取絕對值
			// 若 ti 在 1~n-1 間 , 加入集合 t
			if(ti && ti<test)t.insert(ti);
			n1=n2;
		}
		// 若 t 的元素個數為 n-1 則是 Jolly Jumpers
		if(t.size()==test-1)cout<<"Jolly";
		else cout<<"Not jolly";
		cout<<endl;
	}
}
