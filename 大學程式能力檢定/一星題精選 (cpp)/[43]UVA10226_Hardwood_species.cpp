// CPE �@�P����D 43
// UVA10226 Hardwood species

#include<iostream>
#include<iomanip>		// �ϥ� setprecision()
#include<map>			// �ϥ� map
using namespace std;
int main(){
	int test;
	cin>>test;
	getchar();
	getchar();
	while(test--){
		int n=0;
		map<string,int>r;
		string tr;
		while(getline(cin,tr)){
			if(tr.compare("")==0)break;
			r[tr]++;
			n++;
		}
		map<string,int>::iterator i;
		for(i=r.begin(); i!=r.end(); i++){
			cout<<fixed<<setprecision(4)<<i->first<<" "<<i->second*100.0/n<<endl;
			// ��X���G
		}
		if(test)cout<<endl;
	}
}
