with open('math.txt','w') as f:
    f.writelines(['23 45 23 65\n','44 56 88 21\n','50 67 89 12\n'])
with open('math.txt','r') as f:
    dt=f.readlines()
    
print("math.txt內容：\n",dt)

d1=[d.replace(' ',',') for d in dt]
d2=[list(eval(r)) for r in d1]
data=[]

for i in range(3):
    for j in range(4):
        data+=d2[i]

print("最大值：",max(data),"\n最小值：",min(data))