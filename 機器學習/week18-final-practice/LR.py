# -*- coding: utf-8 -*-
"""
Created on Wed Jun 19 20:10:50 2024

@author: KageRyo
"""

### LR線性迴歸

## 資料匯入
import pandas as pd 
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns

#%matplotlib inline
#%config InlineBackend.figure_format = 'retina'  # 可以增加圖片的解析度，讓字體變的清晰
plt.rcParams['font.sans-serif'] = ['Microsoft YaHei'] # 可解決中文字體無法顯示
plt.rcParams['axes.unicode_minus'] = False      # 用來正常顯示圖片中的負號     
pd.set_option('display.max_columns', None)      # 在Console中顯示變數資料的全部欄位        
pd.set_option('display.width', 1000)            # 設定總欄寬為1000 px          
np.set_printoptions(suppress = True)            # 輸出數值時不要科學記號                    

import warnings
warnings.filterwarnings(action = 'ignore')

# 讀取樣本數據資料Boston Housing Prices
# from sklearn.datasets import load_boston
# boston = load_boston()  # version 1.2 以後的版本不適用，請先自行至網頁下載並另存新檔
# https://github.com/scikit-learn/scikit-learn/blob/main/sklearn/datasets/data/boston_house_prices.csv
df = pd.read_csv("./boston_house_prices.csv", header=1)   # 重新讀取資料並指定第一行作為標題行
df.columns, df.head()   # 檢查資料的欄位名稱和前幾行
df.sample(10)
df.info()   # 課本內容中的target即為下載資料的MEDV

# 可利用直方圖初步檢視變數資料
df['MEDV'].plot(kind = 'hist', bins = 30, alpha = 0.8, facecolor = (0.2,0.2,0.4), edgecolor = 'gold')
# Note: (a) bins: 分組的個數(預設為10)
#       (b) alpha: 圖形的透明度
#       (c) density: 使用精確計數(個數; False)或者密度(比率; True)
#       (d) cumulative: 是否使用累計個數
#       (e) facecolor: 表面顏色 
#       (f) edgecolor: 邊界線條顏色
plt.show()   # 顯示圖表
df['MEDV'].describe() # 基本統計量(平均值、標準差、極端值與分位點)

## 相關係數探索
corr_df = df.corr().round(3)    # 函數corr()計算變數兩兩之間的相關性
corr_df["MEDV"].sort_values(ascending = False)  # 取出變數MEDV與其他變數之間的相關係數   
# 用熱力圖heatmap和散布圖scatter來看
plt.figure(figsize = (8, 6)) # 控制圖片的寬度與高度
sns.heatmap(data = corr_df, annot = True, cmap = 'coolwarm') # 須搭配相關係數矩陣才能使用
plt.show()   # 顯示圖表

corr_df[np.abs(corr_df) < 0.6] = 0   # 強制將 corr_df 變數中相關性絕對值< 0.6 的變成0 
sns.heatmap(corr_df, annot = True, cmap = 'cividis') # 圖片的調色盤可以調整
plt.show()   # 顯示圖表

### 繪製變數之間的散布圖(scatter plot)
df.plot(kind = 'scatter', x = 'RM', y = 'MEDV', alpha = 0.8, figsize = (8, 4))   # 注意 X 與Y不要搞反
plt.show()   # 顯示圖表

## 建構迴歸模型前置作業 -> 應變數與自變數設定
x = df[['RM']]; y=df['MEDV'] # 自變數 x 須為DataFrame格式(多元迴歸會用到)
# 資料拆分 1/3->test datasets
from sklearn.model_selection import train_test_split
x_train,x_test,y_train,y_test = train_test_split(x,y,test_size = 0.33, random_state = 42)
# 利用散布圖看看資料情況
plt.figure(figsize = (10, 4)) # 注意~下列所有程式要連同此行全選後一次執行
plt.scatter(x = x_train, y = y_train, color = 'steelblue', alpha = 0.6, label = '訓練集') # 顏色標籤
plt.scatter(x = x_test, y = y_test, color = '#163f00', alpha = 0.6, label = '測試集') # 16進位標籤
plt.xlabel('房間數量'); plt.ylabel('房價') # X座標與Y座標的說明
plt.legend() # 顯示顏色標籤說明
plt.show()   # 顯示圖表

## 迴歸模型預測器(predict)
# 初始物件
from sklearn.linear_model import LinearRegression
model = LinearRegression() # 建構LR模型預測器
# 機器學習
model.fit(X = x_train, y = y_train) # 利用訓練集的自變數及應變數資料進行迴歸模型建構
print(f'常數項: \n{model.intercept_}; \n自變數係數: \n{model.coef_}') # 提取常數項與迴歸係數值
    # 根據結果可推測房價與房間數量關係：MEDV = -34.22 + 9.04×RM
    # 結果可解釋為房間每增加1個單位，房價會增加9.04個單位(US$1,000)
# 機器預測：利用測試集資料檢測模型的優劣
y_pred = model.predict(X = x_test)   # 將已知的自變數代入模型，計算模型預測值(y-head)
plt.figure(figsize = (8, 6)) # 注意：以下程式碼請連同此行全選後一次執行
plt.scatter(x = x_test, y = y_test,   color = '#163f00',    alpha = 0.6, label = '實際資料')
plt.scatter(x = x_test, y = y_pred, color = 'goldenrod', alpha = 0.6, label = '模型預測資料')
plt.legend()    # 顯示顏色標籤說明
plt.show()      # 顯示圖表

## 模型的優劣性：預測結果評估
# 殘差值：實際資料(y-test)與模型預測資料(y_pred)的差異，亦即模型的誤差(y_test–y_pred)
plt.figure(figsize = (8, 6))
plt.scatter(x = x_test, y = (y_test- y_pred), color = 'tomato')   # 不同實際自變數資料對應的誤差值
plt.axhline(y = 0, c = 'deepskyblue', ls = '--')   # 在圖中增加一條水平線 (樣式為'--')
plt.hist(x = y_test- y_pred, bins = 30, facecolor = (0.2,0.2,0.4), edgecolor = 'gold') # 直方圖檢視殘差
plt.show()      # 顯示圖表
# 使用函數pd.concat() 整合實際資料與模型預估的預測值資料
df_test = pd.concat(objs = [x_test, y_test], axis = 'columns')  # axis = 'index' or 'columns'
df_test['MEDV(pred)'] = y_pred
df_test['ERROR'] = df_test['MEDV'] - df_test['MEDV(pred)']
df_test['ERROR(Abs)'] = np.abs(df_test['ERROR'])   # 取出殘差絕對值
df_test.sort_values(by = 'ERROR(Abs)', ascending = False, inplace = True) # 根據特定欄排序
df_test.head(10)
# 最佳化散佈圖
colors = ['red']*5 + ['blue']*(len(df_test) - 5) # 注意：除了這行程式碼，下面的程式碼請全選後一次執行
fig, axes = plt.subplots(nrows = 1, ncols = 2, figsize = (12, 4))   # 可在一張圖中放入兩個不同的圖
ax = axes[0] ### Figure 1-1
df_test.plot(kind = 'scatter', x = 'RM', y = 'ERROR', c = colors, ax = ax)
for i in df_test.index[ : 5]:
    ax.text(x = df_test.loc[i, 'RM']+0.1, y = df_test.loc[i, 'ERROR']-1, s = i)   # 圖中的文字呈現
    ax.vlines(x = df_test.loc[i, 'RM'], ymin = 0, ymax = df_test.loc[i, 'ERROR'], ls = ':')  # 畫出圖中的垂直虛線
ax.axhline(y = 0, c = 'r', ls = '--') # 畫出圖中的水平虛線(y = 0)
ax.set_title('殘差散布圖(優化)')
ax = axes[1]  ### Figure 1-2
df_test.plot(kind = 'scatter', x = 'RM', y = 'MEDV', c = colors, ax = ax)
df_test.plot(kind = 'scatter', x = 'RM', y = 'MEDV(pred)', c = 'gray', ax = ax)
for i in df_test.index[ : 5]:
    ax.text(x = df_test.loc[i, 'RM']+0.1, y = df_test.loc[i, 'MEDV']-1, s = i)  # 圖中文字呈現
    ax.vlines(x = df_test.loc[i, 'RM'], ymin = df_test.loc[i, 'MEDV'], 
                                        ymax = df_test.loc[i, 'MEDV(pred)'], ls = ':')
ax.set_title('資料散布圖(優化)')
# 模型整體的解釋變異量(R square)，其數值必定介於 0 ~ 1 之間，越接近1代表模型的配適度越高(自變量對應變量的解釋力越強)
from sklearn.metrics import mean_squared_error, mean_absolute_error, r2_score
print(f'Mean Squared Error: {mean_squared_error(df_test["MEDV"], df_test["MEDV(pred)"])}')
print(f'Mean Absolute Error:{mean_absolute_error(df_test["MEDV"], df_test["MEDV(pred)"])}')
print(f'R Square Score:{r2_score(df_test["MEDV"], df_test["MEDV(pred)"])}')
# 運用建構的模型預測未來：Ex.房間數量增加到6間時的房價?
model.predict([[6]])    
# 預測模型的結果是Y = -34.22 + 9.04 ×RM (這麼多層中括號實屬無奈啊~)
model.intercept_ + model.coef_ * 6    # 直接利用模型估算出的係數值及常數項計算模型預測值

## 假設房價與房間數量模型為非線性關係(二次式)：Y=b
## n次多項式模型
print(x_train)
# 先看一下原始資料的訓練集長啥樣吧~
x_train['RM_2'] = x_train['RM']**2 # 在原始資料訓練集中新增房間數量的平方資料欄位
x_test['RM_2'] = x_test['RM']**2
# 在原始資料測試集中新增房間數量的平方資料欄位
model_2 = LinearRegression()
# 建構一個新的迴歸模型預測器
model_2.fit(X = x_train, y = y_train) # 注意!!此時 x_train 內有兩欄(RM and RM_2)
y_pred_2 = model_2.predict(x_test) # 注意!!此時 x_test 內也有兩欄(RM and RM_2)
print(f'R Square Score:{r2_score(y_test, y_pred_2)}') # R Square 與原始模型相比有所提升
model_2.intercept_, model_2.coef_ # 檢視一下建構完成的二次多項式模型的常數項及係數吧~
b0 = model_2.intercept_.round(3); b1 = model_2.coef_.round(3)[0]; b2 = model_2.coef_.round(3)[1]
print(f'二次多項式模型:\n 單位房價= {b0}+ {b1}*房價+ {b2}*房價^2')
plt.figure(figsize = (8, 6)) # 注意：以下程式碼請連同此行全選後一次執行
plt.scatter(x = x_test.iloc[: , 0], y = y_test,       
color = '#163f00',    alpha = 0.6, label = '實際資料')
plt.scatter(x = x_test.iloc[: , 0], y = y_pred_2, color = 'goldenrod', alpha = 0.6, label = '預測資料')
plt.legend(); plt.xlabel('房間數量'); plt.ylabel('房價')

## 多項式模組轉換器
# 利用特徵值轉換器(PolynomialFeatures)可輕鬆創造n次方變數
x_train.drop(labels = 'RM_2', axis = 'columns', inplace = True) # 還原訓練集資料
x_test.drop(labels = 'RM_2', axis = 'columns', inplace = True)   # 還原測試集資料
from sklearn.preprocessing import PolynomialFeatures
polynomial = PolynomialFeatures(degree = 2) # 創建一個具有二次式的特徵值轉換器
x_poly = polynomial.fit_transform(X = x_train)
x_poly[0 : 5, :]   # 檢視經過轉換器處理後的資料(格式為陣列，故無法使用head 或sample)
# 透過管道器(make_pipeline)將特徵值轉換器和迴歸預測器連結，讓管道器同時具備資料轉換及模型預測的功能
from sklearn.pipeline import make_pipeline
model_pl2_lm = make_pipeline(PolynomialFeatures(degree = 2), LinearRegression()) # 建立管道器
model_pl2_lm.fit(X = x_train, y = y_train)            
# 多項式迴歸模型的訓練
y_pred_pip = model_pl2_lm.predict(X = x_test) # 模型預測值計算的過程是不是變的簡潔許多呢~
print(f'R Square Score:{r2_score(y_test, y_pred_pip)}') # 想一想..模型次方數越高預測能力越好嗎?

## 過擬合
error_train = []; error_test = []    # 創見兩個空的串列變數
for order in range(1, 10):
    model_pl = make_pipeline(PolynomialFeatures(degree = order), LinearRegression()) # 不同次方
    model_pl.fit(X = x_train, y = y_train) # 利用訓練集的自變數及應變數資料建構模型
    y_pred_train = model_pl.predict(X = x_train); y_pred_test = model_pl.predict(X = x_test)
    error_train.append(mean_squared_error(y_train, y_pred_train)) # 將生成的資料新增進原有串列
    error_test.append(mean_squared_error(y_test, y_pred_test)) 
plt.plot(range(1, 10), error_train, marker = '.', ls = '--', label = '訓練集')
plt.plot(range(1, 10), error_test , marker = 'o', ls = '-.', label = '測試集')
plt.legend(); plt.xlabel('多項式模型次方數'); plt.ylabel('殘差平方和')
