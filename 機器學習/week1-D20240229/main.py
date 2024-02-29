import numpy as np
import pandas as pd
import matplotlib.pyplot as plt

# 創建一些 Series
s1 = pd.Series(data=range(1, 5), index=list('abcd'))
s2 = pd.Series(index=list('abcda'), data=range(1, 6))
s3 = pd.Series(data=range(1, 11, 3))
s4 = pd.Series(data=[1, '2', ['a', s1, 9453], s3, len])

# 存取 Series 的索引和值
s1.index
s3.index
s4.values
s4[2]
s4[2][1]

# 創建一個 Series 並進行數學運算
string = 'aehgbdcef'
series1 = pd.Series(data=range(1, 10), index=list(string))

series1 + 2
series1 * 3
series1 / 100
series1 % 5

# 存取 Series 的部分數據
s5 = series1[['a', 'b', 'c', 'd', 'e']]
s6 = series1[:2:-1]

# 輸出結果
print(f'Data for s5 {s5} ; Data for s6 {s6}')
print(f'Data for s5\n{s5}\nData for s6\n{s6}')
print(f'Data for s5\n{s5}\n\nData for s6\n{s6}')

print('s5 + s6 =\n', s5 + s6, '\n\ns5 * s6=\n', s5 * s6)
print(f's5 + s6 =\n{s5 + s6}\n\ns5 * s6=\n{s5 * s6}')

# 修改 Series 的值並進行數學運算
series1['a'] = "KageRyo"
series1['e'] = 9999
series1 * 2
