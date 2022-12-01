// CPE 一星精選題 25
// UVA10093 An Easy Problem! (cpp)

#include<iostream>
#include<algorithm>
using namespace std;
int main(){
	string num;
	//0~9 A~Z a~z
	string list="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	while(cin>>num){
		//將 0 轉入 62 進位中的 61數字
		for(int i=0; i<num.size(); i++){
			num[i]=list.find(num[i]);
			num[i]=max(0,(int)num[i]);
		}
		// 最大字元 +1 作為最小起測 n 值
		int n=*max_element(num.begin(),num.end())+1;
		n=max(n,2);
		// 計算 n 進位中 num 的真實數值對 (n-1) 的餘數 (rsd)
		for(; n<=62; n++){
			int r=0;
			for(int i=0; i<num.size(); i++){
				r=r*n+num[i];
				r%=n-1;
			}
			if(r==0)break;	// 餘數為 0 離開迴圈
		}
		if(n<=62)cout<<n;
		else cout<<"such number is impossible!";
		cout<<endl;
	}
}
