# -*- coding: utf-8 -*-
"""
Created on Wed Apr 17 20:10:33 2024

@author: KageRyo

1. import data
2. data backup
3. data to dataframe
4. change row and column
5. how many data type
6. split data(use index with boolean)
7. missing data
8. string to date type and change it
9. z-score
10. dummy value
"""
import numpy as np
import pandas as pd

# 1. import data
df = pd.read_csv('./Big Data Sample (CSV).csv') # 匯入 CSV 檔案

# 2. data backup
df_backup = df.copy() # backup 
print("\n=== Data Backup ===\n")
print(df_backup)   # 印出來看一下

# 3. data to dataframe
df=pd.DataFrame(df) #轉DataFrame
print("\n=== DataFrame ===\n")
print(df)
#Math= [90,50,70,70];English=[60,70,90,55];History=[33,87,96,59];
#list_scores=zip(Math,English,History)
#subject=['Math','English','History']    #科目名稱(欄索引鍵)
#name = ['Simon','Allen','Mary','Dora']  #科目名稱(列索引鍵)

#df_new=pd.DataFrame(list_scores,['Simon', 'Allen', 'Mary', 'Dora'],
#                                ['Math','English','History'])
#print(df_new)   # 印出來看一下

# 4. change row and column
#row
#df.loc[145] = np.nan # 新增空row(列)
df.loc[146] = ['張源修', '1999/12/31', '臺灣', '男', 50, '(+886)0900123456', '搞笑藝人', 20, 189, 3240]  # 新增內容
df.drop(1, axis=0, inplace=True)
df.reset_index(drop=True, inplace=True) # 重新排序index值
print("\n=== Modified DataFrame after Adding and Dropping Rows ===\n")
print(df)

#交換row145 和 row1
row_1 = df.iloc[1].copy()    # 先把第1行複製出來
row_146 = df.iloc[-1].copy() # 先把第146行複製出來
df.iloc[1] = row_146 # 交換
df.iloc[-1] = row_1  # 交換
df.reset_index(drop=True, inplace=True) # 重新排序index值
print("\n=== DataFrame after Row Swap ===\n")
print(df)

#column
#df['排名'] = np.nan   # 預設新增的欄位會在原資料的最後一欄
#df = df.reindex(columns = ['姓名', '出生日期', '國籍', '性別', '排名', '年齡','聯絡方式','業務單位','年資','銷售數量','銷售金額']) # 重新調整欄位順序
#print(df)
# df.drop(labels = '年齡', axis = 1, inplace = True)        # 刪除特定Column(參數labels= 可略)
# df = df.drop(labels = ['銷售數量', '銷售金額'], axis = 1)  # 同時刪除多個特定Columns (沒找到將會報錯)
# print(df)

# 5. how many data type
print("\n=== DataFrame Indexes, Columns, and Shapes ===\n")
print(df.index); print(df.columns)    # 顯示DataFrame的所有列索引鍵或欄索引鍵
print(df.values)   # 顯示DataFrame的所有內容，其資料格式為陣列(array)
print(df.shape)    # 顯示DataFrame的維度，第一維度是列(Rows)的數量；第二維度是行(Columns)的數量
print(df.dtypes)   # 顯示DataFrame中每個欄位的資料型態
print("\n=== DataFrame Info ===\n")
print(df.info)     # 這個也可以用 

# 6. split data (use index with boolean)
col_num = ['年齡', '年資', '銷售數量', '銷售金額']                      # 數值型資料
col_obj = ['姓名', '出生日期', '國籍', '性別', '聯絡方式', '業務單位']   # 類別型資料
df_num = df[col_num]
df_obj = df[col_obj] # 將不同類型的資料分開儲存於相應變數

# 7. missing data
# 匯入所需Library
from sklearn.impute import SimpleImputer
from sklearn.preprocessing import StandardScaler, MinMaxScaler, OneHotEncoder
from sklearn.pipeline import make_pipeline

# 定義數據列的類型
col_num = ['年齡', '年資', '銷售數量', '銷售金額']  # 數值型資料列
col_obj = ['國籍', '性別', '業務單位']             # 類別型資料列

# 分離數值型和類別型資料
df_num = df[col_num]
df_obj = df[col_obj]

# 數值資料的缺失值處理和標準化
## 建立數值型資料的處理管道
pip_num = make_pipeline(
    SimpleImputer(strategy='median'),  # 使用中位數填補缺失值
    StandardScaler()                   # 應用標準化
)
x_num_na_ss = pip_num.fit_transform(df_num)  # 處理數值型數據
print("\n=== Median Values Learned ===\n")
print(pip_num.named_steps['simpleimputer'].statistics_)  # 檢視學習到的中位數

## 管道中臨時關閉標準化
pip_num.set_params(standardscaler=None)  # 關閉標準化轉換器
x_num_na_none = pip_num.fit_transform(df_num)  # 只進行缺失值填補

## 管道中切換到最小最大標準化
pip_num.set_params(standardscaler=MinMaxScaler())  # 更換為最小最大標準化
x_num_na_mm = pip_num.fit_transform(df_num)  # 重新處理數值型數據

# 類別型資料的缺失值處理和獨熱編碼
## 建立類別型資料的處理管道
pip_obj = make_pipeline(
    SimpleImputer(strategy='most_frequent'),  # 使用眾數填補缺失值
    OneHotEncoder(sparse_output=False)        # 獨熱編碼，非稀疏矩陣（舊版本要用 sparse）
)
x_obj_na_oh = pip_obj.fit_transform(df_obj)  # 處理類別型數據
print("\n=== One-Hot Encoded Data ===\n")
print(x_obj_na_oh)

## 管道中使用指定文字填補缺失值
pip_obj = make_pipeline(
    SimpleImputer(strategy='constant', fill_value='Missing'),  # 使用'Missing'填補缺失值
    OneHotEncoder()
)
x_obj_na_oh = pip_obj.fit_transform(df_obj)  # 處理類別型數據

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
pip_data.fit_transform(df).round(2) # 利用整併後管道器進行資料預處理

# 水平合併器的控制欄位應用
from sklearn.pipeline import make_pipeline
pip_num = make_pipeline(SimpleImputer(strategy = 'median'), StandardScaler()) # 數值型資料
pip_obj = make_pipeline(SimpleImputer(strategy = 'most_frequent'), OneHotEncoder()) # 類別型資料
### 利用水平合併器將不同管道器整併
from sklearn.compose import ColumnTransformer
pip_data = ColumnTransformer(transformers = [('pip_num', 'drop', col_num), 
                                            ('pip_obj', pip_obj, col_obj)])
pip_data.fit_transform(df) # 利用整併後管道器進行資料預處理
### 參數transformers中的輸入值 column 與參數 remainder
pip_data = ColumnTransformer(transformers = [('pip_num', pip_num, ['銷售數量', '銷售金額']), 
('pip_obj', pip_obj, col_obj)], 
remainder = 'passthrough') # 其他沒處理的資料直接通過
pip_data.fit_transform(df) # 檢視一下處理結果吧~

# 怎麼把預處理完的資料重新命名呢?
pip_data = ColumnTransformer(transformers = [('pip_num', pip_num, col_num), 
                                             ('pip_obj', pip_obj, col_obj)], remainder = 'drop')
pip_data.fit_transform(df) # 利用整併後管道器進行資料預處理
pip_data.named_transformers_['pip_obj'] # 查看管道器內類別資料的處理項目及其處理順序
pip_data.named_transformers_['pip_obj'].named_steps.keys() # 資料處理的索引鍵
pip_data.named_transformers_['pip_obj'].named_steps['onehotencoder'].get_feature_names_out()
col_oh = pip_data.named_transformers_['pip_obj'].named_steps['onehotencoder'].\
get_feature_names_out(['國籍', '性別', '業務']) # 重組不同類別資料索引鍵名稱
columns = col_num + col_oh.tolist() # 所有資料(數值與類別)索引鍵重新組合成新的串列備用
df_pip = pd.DataFrame(pip_data.fit_transform(df), columns = columns) # 將處理的陣列變成 D.F.

# 8. string to date type and change it
df['出生日期'] = pd.to_datetime(df['出生日期']) # 將 '出生日期' 欄位的字符串轉換為 datetime 類型
df['出生日期'] += pd.Timedelta(days=10)        # 給所有日期加上 10 天
print("\n=== Adjusted Dates ===\n")
print(df['出生日期'])                          # 印出修改後的日期查看

# 9. z-score
from scipy.stats import zscore
df['銷售金額_zscore'] = zscore(df['銷售金額']) # 計算 '銷售金額' 的 z-score 並創建一個新的欄位來存儲這些值
print("\n=== Z-Scores for Sales Amounts ===\n")
print(df[['銷售金額', '銷售金額_zscore']]) # 印出 z-score 查看

# 10. dummy value
df = pd.concat([df, pd.get_dummies(df['國籍'], prefix='國籍')], axis=1)
print("\n=== DataFrame with Dummy Variables for Nationality ===\n")
print(df.head())
