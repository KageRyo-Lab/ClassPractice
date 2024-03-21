# -*- coding: utf-8 -*-
"""
智產技優三1
1411068014
張健勳
[更新]
"""
import numpy as np
import pandas as pd

### 建立/預存初始設定

Math= [90,50,70,70];English=[60,70,90,55];History=[33,87,96,59];
list_scores=zip(Math,English,History)
subject=['Math','English','History']    #科目名稱(欄索引鍵)
name = ['Simon','Allen','Mary','Dora']  #科目名稱(列索引鍵)

df=pd.DataFrame(list_scores,['Simon', 'Allen', 'Mary', 'Dora'],
                            ['Math','English','History'])

print(df)

### 新增Gender

df['Gender'] = ['Male', 'Male', 'Female', 'Female'] # 預設新增的‵欄位會在原資料的最後一欄
df = df.reindex(columns=['Gender'] + subject) # 調整欄位順序

print(df)
   
### 填補缺失值

df.fillna(0, inplace=True)  # 填補缺失值為0

### 新增同學
df.loc['Bryant'] = [85, 75, 80, 'Male']

### 平均
    
df['Ave.'] = df[['Math', 'English', 'History']].mean(axis='columns') # 只提取需納入的欄

print(df)

### 排序

df['Rank'] = df['Ave.'].rank(ascending=False).astype(int) # 新增平均排名

print(df)
