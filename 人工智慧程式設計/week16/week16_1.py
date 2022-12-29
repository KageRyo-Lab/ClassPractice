import pandas as pd

dic0={
    'Java':[87,65,26,89,67],
    'C++':[63,98,66,89,80], 
    'Python':[78,25,76,43,69]
}

d=pd.DataFrame(dic0, index=['Tom','Bob','Tim','Wien','Lily'])
print(d)