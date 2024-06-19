# -*- coding: utf-8 -*-
"""
Created on Wed Jun 19 21:00:56 2024

@author: KageRyo
"""

### MLR多元線性迴歸

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
X = df.drop('MEDV', axis = 'columns') # 模型的自變數們(X使用大寫字母，代表內涵多個自變數的矩陣)
y = df['MEDV']  # 模型的應變數
# 資料拆分 1/3->test datasets
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.33, random_state = 42)

## 多元線性迴歸模型預測器(predict)
# 初始物件
from sklearn.linear_model import LinearRegression
model = LinearRegression()
# 機器學習：利用訓練集的資料來讓機器自己『學習(fit)』
model.fit(X = X_train, y = y_train) # 利用訓練集資料的『所有自變數』建構多元線性迴歸模型
print(f'常數項：\n{model.intercept_}；\n變數係數：\n{model.coef_}')  # 提取常數項與係數值
# 由於自變數太多，不容易一目了然的知道模型係數所對應的自變數為何
beta_coef= pd.DataFrame(zip(X.columns, model.coef_), columns = ['自變數名稱', '迴歸係數'])
beta_coef.sort_values(by = '迴歸係數', ascending = False, inplace= True) # 依照係數大小排序

## 為何須要將資料進行標準化處理呢?
# 由於自變數衡量單位及數值相異，會造成其迴歸係數值的差異，進而影響結果的評斷
# 利用管道器(make_pipeline)將標準化轉換器及迴歸預測器連結
from sklearn.preprocessing import StandardScaler
from sklearn.linear_model import LinearRegression
from sklearn.pipeline import make_pipeline
model_pip = make_pipeline(StandardScaler(), LinearRegression()) # 建立管道器連結轉換器及預測器
model_pip.fit(X_train, y_train) # 將資料先標準化後，再重新利用線性迴歸模型進行訓練
model_pip.named_steps.keys() # 查看管道器內有哪些東西(索引鍵)
X_reg = model_pip.named_steps['linearregression']   # 單獨取出管道器訓練後的線性迴歸模型結果
beta_coef_ss = pd.DataFrame(zip(X.columns, X_reg.coef_), columns = ['自變數名稱', '迴歸係數'])
beta_coef_ss.sort_values(by = '迴歸係數', ascending = False, inplace = True)

## 評估模型的優劣，模型預測結果及殘差還是關鍵：
# 模型預測結果(predict)
y_pred = model_pip.predict(X= X_test)   # 透過管道器可直接將測試集資料標準化後代入迴歸模型
plt.hist(x = y_test-y_pred, bins = 30, facecolor= (0.2,0.2,0.4), edgecolor= 'gold') # 直方圖檢視殘差
plt.xlabel('多元線性迴歸殘差')
plt.show()
# 利用訓練集資料檢視殘差平方和、殘差絕對值和及解釋變異量
from sklearn.metrics import mean_squared_error, mean_absolute_error, r2_score
print(f'MeanSquared Error: {mean_squared_error(y_test, y_pred).round(3)}')
print(f'MeanAbsolute Error:{mean_absolute_error(y_test, y_pred).round(3)}')
print(f'RSquare Score:{r2_score(y_test, y_pred).round(3)}')

## 多元線性迴歸模型中不同自變數的挑選
# 透過水平合併器(ColumnTransformer)選取須要的自變數
# 必須預先知道要選取的變數在資料集內的欄索引鍵名稱
from sklearn.compose import ColumnTransformer
data_pip = ColumnTransformer( 
transformers = [('regressor_select', StandardScaler(), ['RM', 'RAD', 'LSTAT', 'DIS'] )])
from sklearn.pipeline import make_pipeline
model_pip = make_pipeline(data_pip, LinearRegression())  # 管道器連結水平合併器及迴歸預測器
model_pip.fit(X = X_train, y = y_train)
y_pred_4x = model_pip.predict(X = X_test) # 測試集不須先取出自變數RM、RAD、LSTAT及DIS
print(f'R square for All Variabels:\n{r2_score(y_test, y_pred).round(3)}\n\
      R square for 4 Variables:\n{r2_score(y_test, y_pred_4x).round(3)}')

## 變數資料只能利用標準化處理嗎?
# 迴歸分析的假設條件：對所有自變數而言，誤差項是符合常態分配的隨機變數
    # 自變數的資料對稱(不偏)且呈現鐘形分配
    # 誤差項須服從期望值為0、變異數為s2的常態分配
fig, axes = plt.subplots(nrows = 1, ncols = 2, figsize = (12, 4))
df['LSTAT'].hist(alpha = 0.4, bins = 30, ax = axes[0])
axes[0].set_title('LSTAT原始資料')
np.log1p(df['LSTAT']).hist(alpha = 0.4, bins = 30, ax = axes[1])   # 將 LSTAT 進行自然對數(log)轉換
axes[1].set_title('log(LSTAT) 資料')
plt.show()
    
## 變數LSTAT對數轉換前後的迴歸模型比較
# 自變數LSTAT原始資料vs.房價(MEDV)
from sklearn.compose import ColumnTransformer; from sklearn.pipeline import make_pipeline
data_lstat = ColumnTransformer(transformers = [('regressor_select', 'passthrough', ['LSTAT'] )])
model_lstat = make_pipeline(data_lstat, LinearRegression())  # 將未處理的原始資料代入
model_lstat.fit(X = X_train, y = y_train)
y_pred_lstat = model_lstat.predict(X = X_test)  # MEDV = b0 + b1 × LSTAT的測試集結果
# 自變數經過對數轉換後的LSTAT資料vs.房價(MEDV)
from sklearn.compose import ColumnTransformer; from sklearn.pipeline import make_pipeline
data_lstat = ColumnTransformer(transformers = [('regressor_select', 'passthrough', ['LSTAT'] )])
from sklearn.preprocessing import FunctionTransformer
model_Lnlstat = make_pipeline(data_lstat, FunctionTransformer(func = np.log1p), LinearRegression())
model_Lnlstat.fit(X = X_train, y = y_train)
y_pred_Lnlstat = model_Lnlstat.predict(X = X_test) # MEDV = b0 + b1 × Ln(LSTAT)的測試集結果
# 機器預測：利用測試集資料檢測模型的優劣
plt.scatter(x = X_test['LSTAT'], y = y_test, color = '#3B4338', alpha = 0.6, label = '實際資料')
plt.scatter(x = X_test['LSTAT'], y = y_pred_lstat, color = '#E6C786', label = '預測結果')
plt.legend(); plt.title('MEDV = b0 + b1 × LSTAT'); plt.xlabel('LSTAT')
plt.show()
plt.scatter(x = np.log1p(X_test['LSTAT']), y = y_test, color = '#8D6A6E', alpha = 0.6, label = '實際資料')
plt.scatter(x = np.log1p(X_test['LSTAT']), y = y_pred_Lnlstat, color = '#C4C1BC', label = '預測結果')
plt.legend(); plt.title('MEDV = b0 + b1 × Ln(LSTAT)'); plt.xlabel('Ln(LSTAT)')
plt.show()
# 比較兩個模型的預測能力(R2)
print(f'R Square of LSTAT:\n {r2_score(y_test, y_pred_lstat).round(3)}')
print(f'R Square of Ln(LSTAT):\n {r2_score(y_test, y_pred_Lnlstat).round(3)}')