// CPE 一星精選題 41
// UVA10062 Tell me the frequencies! (cpp)

#include <iostream>
#include <cstring>
using namespace std;
int main()
{
	char list[10001];
	int flag=0;
	while(gets(list))
	{
		if(flag)cout<<endl;					// 如果 flag 有值yk6j0c4c;6
		flag=1;
		int n[256]={0},i,j;
		int length=strlen(list);			// 讀取 list 長度
		for(i=0;i<length;i++){
			n[list[i]]++;
		}
		for(i=1;i<=length;i++)
			for(j=255;j>=0;j--)				// 迴圈跑製 j 為0
				if(n[j]==i)
					cout<<j<<" "<<i<<endl;	// 印出結果
	}
}
