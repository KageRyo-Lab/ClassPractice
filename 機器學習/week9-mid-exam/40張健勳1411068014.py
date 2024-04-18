# -*- coding: utf-8 -*-
"""
期中考答題
姓名：張健勳
學號：1411068014
"""
print("=== 作答人 ===")
print("張健勳\n1411068014\n")

# =============================================================================
# 1.(5%)請將外部資料匯入至python，並儲存至變數df中(DataFrame格式)，
#       同時，請利用變數製作一個原始資料的備份變數df_original
# =============================================================================
# 載入所需的Library
import numpy as np
import pandas as pd

df = pd.read_csv('./Machine Learning Sample.csv') # 用相對路徑匯入 CSV 檔案
df_orig = df.copy() # 備份原始 DataFrame 到 df_orig
print("\n=== 匯入CSV成為DataFrame並備份 ===\n")
print(df_orig)

# =============================================================================
# 2.(5%)請將姓名設定為列索引鍵，並列出匯入資料的所有欄索引鍵為何?
# =============================================================================
df.set_index('姓名   ', inplace = True)
print("\n=== 設定姓名為列索引鍵 ===\n")
print(df) # 印出來看一下

print("\n=== 列索引鍵(index) ===\n")
print(df.index)
print("\n=== 欄索引鍵(columns) ===\n")
print(df.columns) # 顯示DataFrame的所有列索引鍵或欄索引鍵

# =============================================================================
# 3.(5%)請分別檢視性別、國籍及業務單位中，各類別的比例為多少?
# =============================================================================
print("\n=== 比例 ===\n")

print(df[['性別']].value_counts(normalize=True))
print(df['性別'].unique())

print(df[['國籍']].value_counts(normalize=True))
print(df['國籍'].unique())

print(df[['業務單位']].value_counts(normalize=True))
print(df['業務單位'].unique())

# =============================================================================
# 4.(5%)請找出所有藝人年齡及年資的1%、25%、75%及99%分位點數值為何?
# =============================================================================
print("\n=== 藝人年齡 1% 25% 75% 99% 分位數 ===\n")
print(df['年齡'].quantile(q = [0.01, 0.25, 0.75, 0.99]))
print("\n=== 藝人年資 1% 25% 75% 99% 分位數 ===\n")
print(df['年資'].quantile(q = [0.01, 0.25, 0.75, 0.99]))

# =============================================================================
# 5.(10%)請將名單中男、女藝人資料分別存至變數df_Male與df_Female (DataFrame格式)，
#        資料呈現時請按照年資分別進行排序(男藝人由小至大、女藝人由大至小)
# =============================================================================
df_Male = df[df["性別"]=="男"]
df_Female = df[df["性別"]=="女"]
print("\n=== 男藝人 ===\n")
print(df_Male.sort_values(by="年資", ascending=True))
print("\n=== 女藝人 ===\n")
print(df_Female.sort_values(by="年資", ascending=False))

# =============================================================================
# 6.(10%)請分別找出演員中高於及低於全部藝人年齡平均數的銷售金額敘述統計 
#        (包含有效觀察值數量、平均數、標準差、最大值、四分位數、中位數、最小值)
# =============================================================================
print("\n=== 年齡平均值 ===\n")
oldAvg = df["年齡"].mean()
print(oldAvg)

print("\n=== 年齡由大到小排序 ===\n")
oldAvgSort = df.sort_values(by = '年齡', ascending=False)
print(oldAvgSort)

print("\n=== 銷售金額的敘述統計(全部) ===\n")
print(df['銷售金額'].describe()) # 針對每個欄索引鍵顯示基本統計量 (count, mean, std, min, 25%, median, 75%, max)

print("\n=== 銷售金額的敘述統計(年齡平均以上) ===\n")
print(oldAvgSort[oldAvgSort["年齡"]>oldAvg].describe())

print("\n=== 銷售金額的敘述統計(年齡平均以下) ===\n")
print(oldAvgSort[oldAvgSort["年齡"]<oldAvg].describe())

# =============================================================================
# 7.(15%)請列出變數df中存在遺漏值的欄位，檢查每個變數各出現幾個遺漏值後，
#        分別利用眾數及中位數填補類別資料及數值資料的遺漏值
# =============================================================================
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

# 算遺漏值數量
print("\n=== 遺漏值數量(數值型資料) ===\n")
print(df_num.isnull().sum()) # 將每一欄的數值各自加總(True = 1; False = 0)
print("\n=== 遺漏值數量(類別型資料) ===\n")
print(df_obj.isnull().sum()) # 將每一欄的數值各自加總(True = 1; False = 0)

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
    OneHotEncoder(sparse=False)        # 獨熱編碼，非稀疏矩陣（舊版本要用 sparse）
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

print("\n=== 填補完Missing Data的結果 ===\n")
print(df_pip)

# =============================================================================
# 8.(15%)請在出生日期欄位的右邊新增一個欄位顯示生日當天是星期幾，
#        欄索引鍵請直接以『星期幾』命名 (注意：出生日期與星期幾兩欄須並列)
# =============================================================================
df['出生日期'] = pd.to_datetime(df['出生日期']) # 出生日期轉成datetime
df['星期幾'] = df['出生日期'].dt.day_name() # 取得當天是星期幾 (注意此函數與上面幾個的差異)
df = df.reindex(columns = ['出生日期','星期幾' , '國籍', '性別', '年齡','聯絡方式','業務單位','年資','銷售數量','銷售金額']) # 重新調整欄位順序
print("\n=== 新增星期幾欄位 ===\n")
print(df)

# =============================================================================
# 9.(15%)請將銷售數量與銷售金額分別利用MinMax 及 ZScores的方法進行標準化，
#        並將標準化後的資料新增至變數df，
#        其欄索引鍵名稱分別標記為『銷售數量(MM)』及『銷售金額(ZS)』
# =============================================================================
# MinMax
# 數值資料的缺失值處理和標準化
## 建立數值型資料的處理管道
df_num = df['銷售數量']
df_num = df['銷售數量']
df_MM = (df_num - df_num.mean()) / df_num.std(ddof = 0) 
df['銷售數量(MM)'] = df_MM
df = df.reindex(columns = ['出生日期','星期幾' , '國籍', '性別', '年齡','聯絡方式','業務單位','年資','銷售數量','銷售數量(MM)','銷售金額']) # 重新調整欄位順序
print("\n=== 銷售數量(MM) ===\n")
print(df[['銷售數量', '銷售數量(MM)']]) # 印出 z-score 查看

# ZS
df_num = df['銷售金額']
df_ZS = (df_num - df_num.mean()) / df_num.std(ddof = 0) 
df['銷售金額(ZS)'] = df_ZS
print("\n=== 銷售金額(ZS) ===\n")
print(df[['銷售金額', '銷售金額(ZS)']]) # 印出 z-score 查看

# =============================================================================
# 10.(15%)將性別、國籍與業務單位轉化為虛擬變數後新增至變數df中，
#         新增資料的欄索引鍵請標註清楚該欄位代表的變數為何 
#         (Ex. 國籍-臺灣、性別-男、業務-主持)
# =============================================================================
df = pd.concat([df, pd.get_dummies(df['國籍'], prefix='國籍')], axis=1)
df = pd.concat([df, pd.get_dummies(df['性別'], prefix='性別')], axis=1)
df = pd.concat([df, pd.get_dummies(df['業務單位'], prefix='業務')], axis=1)
print("\n=== DataFrame with Dummy Variables for Nationality ===\n")
print(df.head())



