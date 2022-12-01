// CPE 一星精選題 12
// UVA490 Rotating Sentences (cpp)

#include<iostream>
#include<cstring>				// 使用 strlen() 函式
using namespace std;
int main(){
	char str[100][101];			// 紀錄句子
	int length[100],max,n;		// length 紀錄句子長度 , max 紀錄最大長度
	// 初始化陣列
	for(int i=0; i<100; i++){		
		for(int j=0; j<101; j++){
			str[i][j];				
		}
		length[i]=0;
	}
	// 寫入陣列
	while(cin.getline(str[n],101)){
		length[n]=strlen(str[n]);
		if(length[n]>max)max=length[n];
		//補齊空格
		for(int i=length[n]; i<max; i++){
			str[n][i]=' ';
			length[n]++;
		}
		n++;					// 總列數
	}
	// 旋轉輸出
	for(int i=0; i<max; i++){
		for(int j=n-1; j>=0; j--){
			if(i<length[j])cout<<str[j][i];
			// 直接輸出會有空格 , 故寫判斷式避免多餘空格
		}
		cout<<endl;
	}
}
