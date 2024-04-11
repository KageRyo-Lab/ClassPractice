# -*- coding: utf-8 -*-
"""
Created on Thu Mar 28 10:35:03 2024

@author: KageRyo
"""

import numpy as np
import pandas as pd

data = pd.read_csv('./Big Data Sample (CSV).csv')   # 相對路徑
print(data)

data.head(10) # 怎麼跟Excel 中第一列的資料(自己設定的欄位名稱)不太一樣呢?
data.tail(10); data.sample(10) # 取尾端資料 或 隨機抽取資料
data.columns # 找出資料中的欄索引鍵名稱


data.info() # 各欄位的資料型態為何
col_num = ['年齡', '年資', '銷售數量', '銷售金額'] # 數值型資料
col_obj = ['姓名', '出生日期', '國籍', '性別', '聯絡方式', '業務單位'] # 類別型資料
df_num = data[col_num]; df_obj = data[col_obj] # 將不同類型的資料分開儲存於相應變數

# SimpleImputer轉換器：讓程式自動學習(fit)並且轉換(transform)
from sklearn.impute import SimpleImputer
si = SimpleImputer(strategy = 'median') # 將轉換器的策略(strategy)預先存入變數 si 中
x_num_na = si.fit_transform(df_num) # 將該欄位的遺漏值用 median 取代
print(si.statistics_) # 檢視各欄位經過"學習"並"轉換"後的參數值
df_num_na = df_num.fillna(value = df_num.median()) # 當然..也可以使用 fillna 函數來處理

# 正規化
from sklearn.preprocessing import StandardScaler, MinMaxScaler
ss = StandardScaler() # 將標準化處理轉換器預先存入變數 ss 中
x_num_na_ss = ss.fit_transform(x_num_na) # 將每個數值欄位的資料進行標準化處理
print(x_num_na_ss) # 注意~ ↑ 上列數值型的原始資料已預先被變數 si 

# 管線器
from sklearn.pipeline import make_pipeline
# 將不同資料轉換器按照處理順序集中寫入至管道器中
pip_num = make_pipeline(SimpleImputer(strategy = 'median'), StandardScaler()) 
x_num_na_ss = pip_num.fit_transform(df_num) # 將原始資料丟入管道器處理

# 切換管線器內的轉換器方法
from sklearn.pipeline import make_pipeline
pip_num = make_pipeline(SimpleImputer(strategy = 'median'), StandardScaler())
x_num_na_ss = pip_num.fit_transform(df_num) # 直接處理數值型的原始資料

pip_num.set_params(standardscaler = None) # 將已設定之轉換器關閉
x_num_na_none = pip_num.fit_transform(df_num) # 跟 x_num_na_ss 比較看看吧~

pip_num = make_pipeline(SimpleImputer(strategy = 'median'), StandardScaler())
pip_num.set_params(standardscaler = MinMaxScaler()) # 將已設定之轉換器變更
x_num_na_mm = pip_num.fit_transform(df_num)

# 類別資料預處理
col_obj = ['國籍', '性別', '業務單位']
df_obj = data[col_obj] # 取出需要的類別資料
si = SimpleImputer(strategy = 'most_frequent') # 使用各類別型欄位的資料眾數取代遺漏值
x_obj_na = si.fit_transform(df_obj) # 類別型及數值型資料皆可放入轉換器 fit_transform 中
print(x_obj_na) 

# 獨熱編碼
from sklearn.preprocessing import OneHotEncoder
oh = OneHotEncoder(sparse = False) # 獨熱編碼轉換器 (sparse = False 可輸出 array資料型態)
x_obj_na_oh = oh.fit_transform(x_obj_na) # 將每個類別欄位的資料取出相應的虛擬變數矩陣
print(x_obj_na_oh) # 誰能告訴我…每個欄位分別代表什麼呢?

# 類別型資料的管道器處理
from sklearn.pipeline import make_pipeline
# 將不同資料轉換器按照處理順序集中至管道器中
pip_obj = make_pipeline(SimpleImputer(strategy = 'most_frequent'),
OneHotEncoder())
x_obj_na_oh = pip_obj.fit_transform(df_obj)

# 可在類別型資料遺漏值處填入指定文字 (Ex. Missing)
pip_obj = make_pipeline(SimpleImputer(strategy = 'constant',\
                                      fill_value = 'Missing'), 
                        OneHotEncoder())
x_obj_na_oh = pip_obj.fit_transform(df_obj)

# 不同資料型態的管道器結合
col_num = ['年齡', '年資', '銷售數量', '銷售金額']; col_obj = ['國籍', '性別', '業務單位'] # 預處理資料
### 根據不同類型資料製造相應的資料預處理過程管道器
from sklearn.pipeline import make_pipeline
pip_num = make_pipeline(SimpleImputer(strategy = 'median'), StandardScaler()) # 數值型資料
pip_obj = make_pipeline(SimpleImputer(strategy = 'most_frequent'), OneHotEncoder()) # 類別型資料
### 利用水平合併器將不同管道器整併
from sklearn.compose import ColumnTransformer
pip_data = ColumnTransformer(transformers = [('pip_num', pip_num, col_num), 
('pip_obj', pip_obj, col_obj)])
pip_data.fit_transform(data).round(2) # 利用整併後管道器進行資料預處理

# 水平合併器的控制欄位應用
from sklearn.pipeline import make_pipeline
pip_num = make_pipeline(SimpleImputer(strategy = 'median'), StandardScaler()) # 數值型資料
pip_obj = make_pipeline(SimpleImputer(strategy = 'most_frequent'), OneHotEncoder()) # 類別型資料
### 利用水平合併器將不同管道器整併
from sklearn.compose import ColumnTransformer
pip_data = ColumnTransformer(transformers = [('pip_num', 'drop', col_num), 
                                            ('pip_obj', pip_obj, col_obj)])
pip_data.fit_transform(data) # 利用整併後管道器進行資料預處理
### 參數transformers中的輸入值 column 與參數 remainder
pip_data = ColumnTransformer(transformers = [('pip_num', pip_num, ['銷售數量', '銷售金額']), 
('pip_obj', pip_obj, col_obj)], 
remainder = 'passthrough') # 其他沒處理的資料直接通過
pip_data.fit_transform(data) # 檢視一下處理結果吧~

# 怎麼把預處理完的資料重新命名呢?
pip_data = ColumnTransformer(transformers = [('pip_num', pip_num, col_num), 
                                             ('pip_obj', pip_obj, col_obj)], remainder = 'drop')
pip_data.fit_transform(data) # 利用整併後管道器進行資料預處理
pip_data.named_transformers_['pip_obj'] # 查看管道器內類別資料的處理項目及其處理順序
pip_data.named_transformers_['pip_obj'].named_steps.keys() # 資料處理的索引鍵
pip_data.named_transformers_['pip_obj'].named_steps['onehotencoder'].get_feature_names_out()
col_oh = pip_data.named_transformers_['pip_obj'].named_steps['onehotencoder'].\
get_feature_names_out(['國籍', '性別', '業務']) # 重組不同類別資料索引鍵名稱
columns = col_num + col_oh.tolist() # 所有資料(數值與類別)索引鍵重新組合成新的串列備用
df_pip = pd.DataFrame(pip_data.fit_transform(data), columns = columns) # 將處理的陣列變成 D.F.
