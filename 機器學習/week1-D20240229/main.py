import random
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt  # 繪圖庫

# 建立 Series
s1 = pd.Series(data=range(1, 5), index=list('abcd'))  # 使用指定索引
s2 = pd.Series(index=list('abcda'), data=range(1, 6))  # 使用重複索引
s3 = pd.Series(data=range(1, 11, 3))  # 使用預設整數索引
s4 = pd.Series(data=[1, '2', ['a', s1, 9453], s3, len])  # 包含多種資料類型

# 存取索引和值
print(s1.index)  # 列出 s1 的索引
print(s4.values)  # 列出 s4 的值
print(s4[2])  # 存取 s4 的第三個元素
print(s4[2][1])  # 存取 s4 第三個元素的第二個元素

# 數值運算
string = 'aehgbdcef'
series1 = pd.Series(data=range(1, 10), index=list(string))
print(series1 + 2)  # 每個元素加 2
print(series1 * 3)  # 每個元素乘 3
print(series1 / 100)  # 每個元素除以 100
print(series1 % 5)  # 每個元素取餘數

# 選取和對齊
s5 = series1[['a', 'b', 'c', 'd', 'e']]  # 選取特定索引元素
s6 = series1[:2:-1]  # 反向選取前兩個元素

# 列印 Series
print(f's5: {s5}\ns6: {s6}')  # 格式化列印

# 運算和對齊
print(f's5 + s6:\n{s5 + s6}\ns5 * s6:\n{s5 * s6}')

# 更改元素值和運算
series1['a'] = "KageRyo"  # 更改元素值
series1['e'] = 9999
print(series1 * 2)  # 數值元素乘 2，字串元素重複 2 次

# 統計
print(len(series1))  # 元素個數
print(series1.size)  # 元素個數
print(series1.count())  # 非空值元素個數
print((s5 * s6).count())  # 非空值元素相乘後的非空值個數

# 統計描述
series1 = pd.Series(data=[random.random()
                    for _ in range(10)], index=list('ahgbadcefa'))
series2 = pd.Series(data=[1, 2, 3, 4, np.nan])  # 包含空值
series3 = pd.Series(data=['a', 'a', 'b', 'c', 'd', 'a', 'b'])  # 字串序列
print(series1.describe())  # 數值序列描述性統計
print(series2.describe())  # 包含空值的序列描述性統計
print(series3.describe())  # 字串序列描述性統計

# 統計量
print(f'平均值：{series1.mean()}\n標準差：{series1.std()}\n最大值：{series1.max()}\n最小值：{series1.min()}')
print(series1.var())  # 變異數
print(series1.std())  # 標準差
print(series1.quantile(q=0.50))  # 中位數
print(series1.quantile(q=[0.01, 0.25, 0.75, 0.99]))  # 分位數
