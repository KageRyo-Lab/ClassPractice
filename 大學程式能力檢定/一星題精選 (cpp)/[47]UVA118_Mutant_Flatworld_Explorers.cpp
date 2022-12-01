// CPE 一星精選題 47
// UVA118 Mutant Flatworld Explorers

#include <iostream>
using namespace std;
bool b[64][64];
const char ot[]={'N','E','S','W'};
const int dx[]={0,1,0,-1};
const int dy[]={1,0,-1,0};
int main()
{
	int n,m,x,y,mv,nx,ny;
	char o;
	string t;
	cin>>n>>m;
	while(cin>>x>>y>>o>>t){	
		bool l;
		for(int i=0;i<4;i++){
			if(ot[i]==o){
				mv=i;
				l=false;
			}
		}
		for(int i=0;i<t.size();i++){
			if(t[i]=='L')mv=--mv&3;
			else if(t[i]=='R')mv=++mv&3;
			else if(t[i]=='F'){
				nx=x+dx[mv];
				ny=y+dy[mv];
				if(nx<0||nx>n||ny<0||ny>m){
					if(b[x][y])continue;
					else{
						b[x][y]=1;
						l=true;break;
					}	
				}
				x=nx,y=ny;		
			}	
		}
		// 輸出結果
		cout<<x<<" "<<y<<" "<<ot[mv];
		if(l)cout<<" LOST";
		cout<<endl;
	}
}
