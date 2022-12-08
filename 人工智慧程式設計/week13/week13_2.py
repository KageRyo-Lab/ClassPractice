# 設a=np.arange(20).reshape((4,5))，並回答以下問題:
# 1) 存取陣列a中，列索引為0~2，行索引為1~3的元素
# 2) 存取陣列a中所有可以被3整除的數
# 3) 試使用花式索引提出陣列a的元素如下
# 6 8 9
# 11 13 14
import numpy as np

a=np.arange(20).reshape((4,5))
print("the whole array a:\n",a)
print("======================")
# 1
a_slice1 = a[0:3, 1:4]
print(a_slice1)
print("======================")
# 2
a_slice2 = a[a % 3 == 0]
print(a_slice2)
print("======================")
# 3
a_fancy = a[1:3,[1,3,4]]
print(a_fancy)