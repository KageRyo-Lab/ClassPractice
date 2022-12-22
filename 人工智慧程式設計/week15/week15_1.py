import pandas as pd

s=pd.Series([77,34,78,20,12,35])

print("question1: ")
# 提取s中索引前三個元素
s1=s[0:3:1]
print(s1)

print("\nquestion2: ")
# 計算 2s+(s^2)
for i in range(len(s)):
    s2=2*s[i]+(s[i]**2)
    print(s2,end=' ')

print("\n\nquestion3: ")
# 提取s中的偶數並將它們平均
s3=0
count=0
for i in range(len(s)):
    if(s[i]%2==0):
        s3+=s[i]
        count+=1
s3=s3/count
print(s3)