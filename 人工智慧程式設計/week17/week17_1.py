import pandas as pd
d=pd.DataFrame([[40,50,36],[12,19,21]],columns=["a", "b", "c"])

"""
1. 分別計算d的每一列和每一欄的平均值。
2. 計算d中，所有元素的加總。
3. 計算d每一欄之元素標準差。
"""

s1=d.mean()
print(s1)
s11=d.mean(axis=1)
print(s11)

s2=d.sum().sum()
print(s2)

s3=d.std()
print(s3)