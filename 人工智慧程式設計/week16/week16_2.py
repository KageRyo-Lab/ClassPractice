import pandas as pd

dic0={
    'Java':[87,65,26,89,67],
    'C++':[63,98,66,89,80], 
    'Python':[78,25,76,43,69]
}

d=pd.DataFrame(dic0, index=['Tom','Bob','Tim','Wien','Lily'])

"""
1. 提取Tom和Wien的所有成績
2. 提取所有學生Python的所有成績
3. 提取Bob的C++成績
4. 提取Lily的Java和Python成績
"""

s1=d.loc[['Tom','Wien']]
print(s1,'\n')

s2=d.loc[:, 'Python']
print(s2,'\n')

s3=d.loc['Bob','C++']
print(s3,'\n')

s4=d.loc['Lily',['Java','Python']]
print(s4)
