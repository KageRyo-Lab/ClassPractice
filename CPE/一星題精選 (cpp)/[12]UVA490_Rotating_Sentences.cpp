// CPE �@�P����D 12
// UVA490 Rotating Sentences (cpp)

#include<iostream>
#include<cstring>				// �ϥ� strlen() �禡
using namespace std;
int main(){
	char str[100][101];			// �����y�l
	int length[100],max,n;		// length �����y�l���� , max �����̤j����
	// ��l�ư}�C
	for(int i=0; i<100; i++){		
		for(int j=0; j<101; j++){
			str[i][j];				
		}
		length[i]=0;
	}
	// �g�J�}�C
	while(cin.getline(str[n],101)){
		length[n]=strlen(str[n]);
		if(length[n]>max)max=length[n];
		//�ɻ��Ů�
		for(int i=length[n]; i<max; i++){
			str[n][i]=' ';
			length[n]++;
		}
		n++;					// �`�C��
	}
	// �����X
	for(int i=0; i<max; i++){
		for(int j=n-1; j>=0; j--){
			if(i<length[j])cout<<str[j][i];
			// ������X�|���Ů� , �G�g�P�_���קK�h�l�Ů�
		}
		cout<<endl;
	}
}
