// CPE 琍弘匡肈 08
// UVA10008 What`s Cryptanalysis?

#include<iostream>
using namespace std;
int count[256],length;	//璸衡ダ瞷Ω计の参璸计秖ノ
int main(){
	char c;
	while(cin>>c)length++,count[toupper(c)]++;
	while(--length){	// 弄 c –Ω length+1 Τ癸莱Ω计 +1
		for(c='A'; c<='Z'; c++){
			if(count[c]==length)cout<<c<<" "<<count[c]<<endl;
			// 璝ダ瞷Ω计单 length 玥
		}
	}
}
