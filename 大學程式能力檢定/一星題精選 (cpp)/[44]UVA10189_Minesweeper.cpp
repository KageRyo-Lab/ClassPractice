// CPE �@�P����D  44 
// UVA10189 Minesweeper (cpp) 
 
#include <iostream>
using namespace std;
int n,m;
char field[105][105];
void Fill (int x,int y){
    for (int i=-1; i<=1; i++)
        for (int j=-1; j<=1; j++)
            if (field[x+i][y+j]!='*')
                field[x+i][y+j]++;
}
int main(){
    int T = 1;
    while (cin>>n>>m){
        if (!n && !m)break;
        if (T!=1)cout<<endl;;
        //��l�Ƽƶq��0
        for(int i=1; i<=n; i++)
            for (int j=1; j<=m; j++)
                field[i][j]='0';
        //��J�A�p�G��'*'�A�h�I�sFill�N��K�Ӥ�V�ƶq+1
        for(int i=1; i<=n; i++){
            for(int j=1; j<=m; j++){
                char c;
                c = getchar();
                while (c=='\n') c = getchar();
                if (c == '*'){
                    field[i][j]='*';
                    Fill(i,j);
                }
            }
        }    
        //��X���G
        cout<<"Field #"<<T++<<":"<<endl;
        for (int i=1; i<=n; i++){
            for (int j=1; j<=m; j++) cout<<field[i][j];
            cout<<endl;
        }
    }
}
