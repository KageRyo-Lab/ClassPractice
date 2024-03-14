# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""
import numpy as np #import numpy套件並簡化其名稱為 np
import pandas as pd #import pandas套件並簡化其名稱為 pd
import matplotlib.pyplot as plt #import matplotlib.pyplot套件並簡化其名稱為 plt
import seaborn as sns #import seaborn套件並簡化其名稱為 sns
# =============================================================================
# 創建Series資料
# =============================================================================
s1 = pd.Series(data = range(1,5), index = list('abcd'))# 語法中 data 與 index 為Series的內建參數
s2 = pd.Series(index = list('abcda'), data = range(1,6))# 索引鍵可設定相同的文字
s3 = pd.Series(data = range(1,11,3))# 若沒有指定 index，則使用預設的0, 1, 2, 3, 4...作為索引鍵
s4 = pd.Series(data = [1, '2',['a',s1,9453],s3,len])# 可使用串列(list)來儲存資料相似

s1.index
s2.index
s3.index
s4.values
s4[2]
s4[2][1]

# =============================================================================
# Series的處理準則:向量計算
# =============================================================================
string = 'aehgbdcef'
series1 = pd.Series(data = range(1,10),index = list(string))

series1 + 2
series1 * 3
series1 / 100
series1 % 5 # Series 與常數數值運算會針對每個元素個別執行

s5 = series1[['a','b','c','d','e']]# 根據索引鍵排出特定順序取值 (相同索引鍵的資料會一併取出)
s6 = series1[ : 2 : -1]# 利用索引值在變數中的位置順序直接取值
print(f'Data for s1\n{s1}\n\nData for s2\n{s2}')# 每出現一個 \n 代表換行一次
print('s1 + s2 =\n', s1+s2, '\ns1 * s2 =\n', s1*s2) # 顯示結果為聯集，但僅交集部分才進行數值運算
series1['a'] = 'Peter'; series1['e'] = 9999 # 可以使用索引鍵更換相應位置的索引值

len(series1)
series1.size; series1.count() # 計算非NaN的個數 (後續會著重介紹NaN是什麼東西)
(s1*s2).count() # 算式計算完畢後亦可直接套用函數

# =============================================================================
# Series的基本數值函數
# =============================================================================
import random # 須要的套件皆可隨時import 
series1 = pd.Series(data = [random.random() for _ in range(10)], index = list('ahgbadcefa') )
series2 = pd.Series(data = [1, 2, 3, 4, np.nan])
series3 = pd.Series(data = ['a', 'a', 'b', 'c', 'd', 'c', 'a', 'b']) 

series1.describe() # 元素中若存在非數值 (Ex. 字串)，將無法計算相應的敘述統計量
series2.describe()
series3.describe() # 不論混合數字與字串(或其他型態)及純字串，皆視為名目類別資料 

print(f'Mean = {series1.mean()}\nStd. = {series1.std()}\nMax & Min = {series1.max()} & {series1.min()}') 
series1.var()
series1.std() # 樣本變異數(標準差) (分母是 N-1)
series1.quantile(q = 0.50) # " q " 為內建的參數
series1.quantile(q = [0.01, 0.25, 0.75, 0.99]) # 取百分位數時，可利用串列一次取出多個

# =============================================================================
# Series的類別型資料處理方法
# =============================================================================
string = 'abcdeacdbeebcad'
series2 = pd.Series(data = [1, 1, 2, 3, 3, 4, 4, 'a', 'b', 4, 'a', 'b', 'a', 2, '3'], index = list(string.upper()))

series2.unique() # unique():出現的類別
series2.nunique() #nunique():總共幾個類別
series2.value_counts() # 注意數字與字串既使長的一樣，但會被歸類為不同類別的資料
series2.value_counts(normalize = True) # 參數 normalize = True 可顯示元素個數所佔百分比
series2.value_counts(normalize = True).round(2) # 將元素值以四捨五入至小數第二位
series2.sort_index() # 原始變數的順序不會被改變 (若非純文字或純數字，將無法排序)
series2.sort_index(ascending = False) # 只要是排序相關函數，都可以使用 ascending 參數 
series2.value_counts().sort_values() # 根據類別資料的個數重新排序
# =============================================================================
# 創建DataFrame
# =============================================================================

#以字典資料格式創建 DataFrame
dict_scores = {'Math':   [90,50,70,70],
               'English':[60,70,90,55],
               'History':[33,87,96,56]}
print(dict_scores)
print(dict_scores['Math'])
df1 = pd.DataFrame(dict_scores)

#以串列(list)資料格式創建 DataFrame
list_scores = [[90,50,70,70],
               [60,70,90,55],
               [33,87,96,59]]
df2 = pd.DataFrame(list_scores) 
df2 = pd.DataFrame(list_scores, columns = ['Math','English','History']) 

df2_c = pd.DataFrame(list_scores, columns = ['Simon','Allen','Mary','Dora'])
df2_i = pd.DataFrame(list_scores, index = ['Math','English','History'])

df2 = pd.DataFrame(list_scores, columns = ['Simon','Allen','Mary','Dora'],
                                index = ['Math','English','History'])

df3 = pd.DataFrame(list_scores)
df3 = pd.DataFrame(data = list_scores,
                   index = ['Math','English','History'],
                   columns = ['Simon','Allen','Mary','Dora'])
print(df3) 

df = pd.DataFrame(list_scores,['Math','English','History'],['Simon','Allen','Mary','Dora'])
df_copy = df
df_orig = df.copy()
id(df) #id資料儲存的位置
id(df_orig)
id(df_copy)

#結合串列(list)及zip函數合併資料
Math = [90,50,70,70]; English = [60,70,90,55]; History = [33,87,96,56]
list_scores = zip(Math,English,History)
subiect = ['Math','English','History'] #科目名稱(欄索引鍵)
name = ['Simon','Allen','Mary','Dora'] #科目名稱(列索引鍵)
df = pd.DataFrame(list_scores,columns = subiect,index = name)
df_orig = df_copy #備份原始資料時，使用.copy()函數創建一個新資料(記憶體儲存的位置不同)

# =============================================================================
# DataFrame的重要屬性(Attributes)
# =============================================================================
df.index # 指標: .index
df.columns # 指標: .columns
df.values # 陣列(array)格式的資料
df.shape # 陣列的大小: shape (rows, columns)
df.dtypes # DataFrame 中每個欄位的類型

df.columns[2]
df.columns[0: 2]
df.columns[1:3]
df.values # DataFrame 中的值: values
df.values[0, 2] # 第一個位置選擇列;第二個位置選擇欄,且都是從開始計數
df.values[0:3, 1:3] # 可一次取多個值,但須謹記含頭不含尾的規則及前列後欄的順序
df.values[1:3,:]
df.values[[1,0],:] # 多個且不連續取值,記得使用串列

df_orig_x = df
df.values[0, 2] = 100 # 可以利用這樣的方式修改數值
print(df) # 注意!!DataFrame的數值會直接被改變!!
print(df_orig_x) # 備份資料也一併被改變

#注意：
# (a) DataFrame 中值的類型是陣列
# (b) 選擇陣列中元素 [row, column] 的具體位置

# 找到特定的指標並更改欄位的指標
df.rename({'Math': 'Mathematic'}, axis = 1)

print(df) # df 沒有跟著修改!!(在上面加上 inplace = True 即可直接修正)

# 注意：
# (a) 參照變數 "df"!! 它沒有改變
# (b) axis 0 或 1 推斷行或列中的指標

# (c) 如果舊指標在 DataFrame 中找不到，它將不起作用
df.rename({'Math': 'Mathematic'}, axis = 0) # 座標參數錯誤時，不會有任何變化

df = df.rename({'Math': 'Mathematic'}, axis = 1) # 將修改後的結果重新儲存到變數 df

df.rename({'Dora': 'Jones'}, axis = 0, inplace = True) # 加上 inplace = True 即可直接
df = df.rename({'Simon': 'Simons'}, axis = 1) #座標參數錯誤時，無法在 columns 中找到Somon
df = df.rename({'Simon': 'Simons'}, axis = 0) #座標參數錯誤時，無法在 columns 中找到Somon
print(df)
# 記得座標參數很麻煩?可以試試看用參數 columns 與 index~
df = df.rename(columns = {'Mathematic':'Math'}) #直接指定修改 columns 的索引鍵
df = df.rename (index = {'Jones': 'Dora', 'Simons': 'Simon'}) #直接指定修改 index 的索引鍵

### Reest the Indicators for Index: reset_index() &.set_index
df = df_orig.copy()
df.columns[0] = 'Mathematic' #(X) It does not work
df.index[3] = 'Dabby'        #(X) It does not work

df.columns = ['M.', 'E.', 'H.'] #與.rename()不同.columns 會直接改變 df 的索引鍵
df.index = list('SAMD') #數量必須與DataFrame的列數一致
df.reset_index() #原有的列索引鍵將直接轉入DataFrame的第一欄(預設值取代),而其欄索引鍵則以 index 表示
df.reset_index().index #c.f. df.index
df.reset_index(drop = True) # drop = True: do not collect the indicators of index
print(df)

df_idx = df.reset_index()
print(df_idx)
# Note:
# (a) drop = True: 捨棄原來的列索引鍵
# (b) inplace = True: 直接修改原有的變數
# =============================================================================
# 如何在DataFrame中加入新的Columns
# =============================================================================
df = df_orig.copy() #記得取用備份資料的時候還是要.copy()
df_ch = pd.DataFrame({'C. Score': [78, 50]}, index = ['Simon', 'Mary']) #新建立國文成績作為範例

print(df_ch)
df['Chinese'] = df_ch #欲新增的欄索引鍵不須與外加的DataFrame資料相同
print(df)#列索引欄沒有對應到的部分會填入NaN

df = df_orig.copy()
df_ch_2 = pd.DataFrame({'C. Score': [78, 50, 99]}, index = ['Mary', 'Bryant', 'Simon']) #多一個Bryant
print(df_ch_2) #Bryant的國文成績並不會出現在新增後的DataFrame中
df['Chinese'] = df_ch_2 #新資料中存在但原DataFrame中沒有的,將直接被忽略
print(df)# Bryant的國文成績並不會出現在新增後的DataFrame中
df_orig = df.copy()

# =============================================================================
#利用索引鍵取出特定欄或列
# =============================================================================
### (1) 使用欄索引鍵取出特定欄
df.columns#先檢視DataFrame有哪些欄索引鍵
df.Math# 若要取指定欄的值,可直接將該欄索引鍵作為"類函數"使用(不建議)
df['History']# 用最基本的方式取得指定欄的值(取出的值格式為Series:type(df['History'])
df[ ['History']]#跟上面的結果比較一下兩者之間究竟有什麼差異? (取出的格式為DataFrame)

### (2) 同時取出多個欄
df[['English', 'Math']] # Have to use the form of listdf[ ['English', 'Math', 'Chinese'] ]                                                                                                                                                                                                                                                                                                                                                                                                                     
df[['Chinese', 'English', 'Math']] # DataFrame的順序隨串列順序而定
df['English', 'Math', 'Chinese'] #(X)若直接在中括內使用字串會出錯哦~!!

