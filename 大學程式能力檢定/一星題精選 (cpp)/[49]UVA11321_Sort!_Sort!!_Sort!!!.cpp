// CPE �@�P����D (49)
// UVA11321 Sort! Sort!! and Sort!!! (cpp)

#include<iostream>
#include<algorithm>		// �ϥ� sort()
using namespace std;
int m;					// �ŧi m �����ܼ�
bool cp(int x,int y){	// �i�����B��
	if(x%m==y%m){
		if(x%2==0&&y%2==0)return x<y;
		else if(x%2!=0&&y%2!=0)return x>y;
		else return y%2==0;
	}else return x%m<y%m;
}
int main(){
	int n;
	while(cin>>n>>m){	// ��Jn�Mm
		cout<<n<<" "<<m<<endl;
		if(n==0)break;	// �Yn��0 break
		int a[n];		// �ŧi�}�Ca
		for(int i=0; i<n; i++)cin>>a[i];
		sort(a,a+n,cp);	// �ƦC
		for(int i=0; i<n; i++)cout<<a[i]<<endl;
	}
}
