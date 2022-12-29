import pandas as pd

dic0={
    'Java':[87,65,26,89,67],
    'C++':[63,98,66,89,80], 
    'Python':[78,25,76,43,69]
}

d=pd.DataFrame(dic0, index=['Tom','Bob','Tim','Wien','Lily'])

"""
1. 列出Python小於60分的學生資料
2. 列出所有科目都大於60分的學生
3. 列出C++分數小於70但Java分數小於60分的學生
"""

s1=d[d['Python']<60]
print(s1,'\n')

s2=d[(d['Python']>60) & (d['C++']>60) & (d['Java']>60)]
print(s2,'\n')

s3=d[(d['C++']<70) & (d['Java']<60)]
print(s3)