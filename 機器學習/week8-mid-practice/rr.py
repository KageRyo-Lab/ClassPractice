# -*- coding: utf-8 -*-
"""
Created on Wed Apr 17 23:48:30 2024

@author: KageRyo
"""

import pandas as pd
import numpy as np
df_csv = pd.read_csv('./Big Data Sample (CSV).csv')#匯入檔案 
#看老師給的檔名是什麼 路徑：C:\Users\User\Desktop\Big Data Sample (CSV).csv
df_orig = df_csv.copy()

#第二題 複製檔
df = df_orig.copy()#複製檔

#第三題 轉DataFrame
df=pd.DataFrame(df_csv)#轉乘DataFrame

#第四題 設定欄列索引鍵
#設定欄列索引鍵 #columns欄索引鍵=>直的 index列索引鍵=>橫的
df = pd.DataFrame(data = df_csv,
                  index = ['Simon', 'Allen', 'Mary', 'Dora'],
                  columns = ['姓名', '國籍', '聯絡方式'])
#新增欄 df['Chinese']=df_ch
#新增列 df.loc['名稱']=資料

#第五題 每個項目有幾個類別行
df.nunique() #nunique():總共幾個類別
df.info() #info():總共幾個數值
df_csv.info() # 各欄位的資料型態為何
col_num = ['年齡', '年資', '銷售數量', '銷售金額'] # 數值型資料
col_obj = ['姓名', '出生日期', '國籍', '性別', '聯絡方式', '業務單位'] # 類別型資料
df_num = df_csv[col_num]; df_obj = df_csv[col_obj] # 將不同類型的資料分開儲存於相應變數

#解資料佔比（百分比）
df['姓名'].value_counts(normalize=True) #''中間放欄位名稱，normalize=True代表百分比表示
df.value_counts()