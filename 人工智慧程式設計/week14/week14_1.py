import numpy as np

a=np.array([[5,12,8,14],[6,1,8,14],[21,17,3,0]])
# 1
m=np.mean(a)
print("average m: ",m)
# 2
bm = a[np.where(a > m)]
abm = np.mean(bm)
print("number big than m and average: ",abm)
# 3
lm = a[np.less_equal(a, m)]
alm = np.mean(lm)
print("number small than m and average: ",alm)
# 4
b = np.where(a > m, 1, 0)
print(b)