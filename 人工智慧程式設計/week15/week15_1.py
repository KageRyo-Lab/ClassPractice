import pandas as pd

s=pd.Series([77,34,78,20,12,35])

print("question1: ")
# 提取s中索引前三個元素
s1=s[:3]
print(s1)

print("\nquestion2: ")
# 計算 2s+(s^2)
s2=2*s+(s**2)
print(s2)

print("\n\nquestion3: ")
# 提取s中的偶數並將它們平均
s3=s[s%2==0].mean()
print(s3)