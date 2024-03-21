# -*- coding: utf-8 -*-
"""
智產技優三1
1411068014
張健勳
"""

import numpy as np 
import pandas as pd 
import matplotlib.pyplot as plt 
import seaborn as sns 
# =============================================================================
# 創建DataErame
# =============================================================================

#dict字典資料格式
dict_scores = {
    'Math':      [90,50,70,70],
    'English':   [60,70,90,55],
    'History':   [33,87,96,59]} 
print(dict_scores)
print(dict_scores['Math'])
df1 = pd.DataFrame(dict_scores)

#list串列資料格式
list_scores=[[90,50,70,70],
             [60,70,90,55],
             [33,87,96,59]]

df2 = pd.DataFrame(list_scores)
df2 = pd.DataFrame(list_scores,columns=['Math','English','History'])#錯的

df2_c = pd.DataFrame(list_scores,columns=['Simon', 'Allen', 'Mary', 'Dora'])
df2_i = pd.DataFrame(list_scores,index=['Math','English','History'])

df2 = pd.DataFrame(list_scores,columns=['Simon', 'Allen', 'Mary', 'Dora'],
                               index=['Math','English','History'])

# =============================================================================
# DataFrame的列索引鍵與欄索引鍵
# =============================================================================
list_scores=[[90,60,33],
             [50,78,87],
             [70,90,96],
             [70,55,59]]
df3 = pd.DataFrame(list_scores)
df3 = pd.DataFrame(data=list_scores,
                   index=['Simon', 'Allen', 'Mary', 'Dora'],
                   columns=['Math','English','History'])
print(df3)

df=pd.DataFrame(list_scores,['Simon', 'Allen', 'Mary', 'Dora'],
                            ['Math','English','History'])
df_copy=df
df_orig = df_copy()
id(df)
id(df_orig)
id(df_copy)
# Note: Check id(df) and id(df_orig), the id will be the same for df_org = df

# =============================================================================
# DataFrame的重要性(Attributes)
# =============================================================================
#list串列及zip函數合併資料
Math= [90,50,70,70];English=[60,70,90,55];History=[33,87,96,59];
list_scores=zip(Math,English,History)
subject=['Math','English','History']    #科目名稱(欄索引鍵)
name = ['Simon','Allen','Mary','Dora']  #科目名稱(列索引鍵)
df = pd.DataFrame(list_scores,index=name,columns=subject)
df_orig = df.copy()#備份原始資料時，使用.copy()函數創建一個新資料(記憶體儲存的位置不同)


df.index   # 指標: .index
df.columns # 指標: .columns
df.values  # 陣列(array)格式的資料
df.shape   # 陣列的大小: shape (rows, columns)
df.dtypes  # DataFrame 中每個欄位的類型

df.columns[2]
df.columns[0:2] #含頭部含尾
df.columns[1:3]
df.values# DataFrame 中的值: values
df.values[0, 2] # 第一個位置選擇列;第二個位置選擇欄,且都是從開始計數
df.values[0:3, 1:3] # 可一次取多個值,但須謹記含頭不含尾的規則及前列後欄的順序
df.values[1:3,:]
df.values[[1,0],:] # 多個且不連續取值,記得使用串列

df_orig_x = df
df.values[0,2]=100 # 可以利用這樣的方式修改數值
print(df) # 注意!!DataFrame的數值會直接被改變!!
print(df_orig_x) # 備份資料也一併被改變

# =============================================================================
# DataFrame索引鍵的修改:特定索引鍵調整
# =============================================================================
df.rename({'Math':'Mathematic'},axis=1)#axis 0 或 1 推斷行或列中的指標
print(df)

df.rename({'Math':'Mathematic'},axis=0)
df=df.rename({'Math':'Mathematic'},axis=1)

df.rename({'Dora':'Jones'},axis=0, inplace = True)
df=df.rename({'Simon': 'Simons'},axis=1)
df=df.rename({'Simon': 'Simons'},axis=0)
print(df)

#columns 與 index~
df = df.rename(columns = {'Mathematic':'Math'}) #直接指定修改 columns 的索引鍵
df = df.rename (index = {'Jones': 'Dora', 'Simons': 'Simon'}) #直接指定修改 index 的索引鍵


# =============================================================================
# DataFrame索引鍵的修改:列或欄
# =============================================================================
df = df_orig.copy()
df.value[0, 2] = 100

# 改單一索引鍵用rename
df.column[0] = 'Mathematic' #(X)
df.index[3] = 'Dabby' # (X)

df.columns = ['M.', 'E.', 'H.'] #與.rename()不同.columns 會直接改變 df 的索引鍵
df.index = list('SAMD') #數量必須與DataFrame的列數一致
df.reset_index()
df.reset_index().index
df.reset_index(drop = True)
print(df)

df_idx = df.reset_index()
print(df_idx)

# =============================================================================
# 如何在DataFrame中放入新的Columns
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
# 利用索引鍵取出特定欄或列
# =============================================================================
# (1) 使用欄索引鍵取出特定欄
df.columns#先檢視DataFrame有哪些欄索引鍵
df.Math# 若要取指定欄的值,可直接將該欄索引鍵作為"類函數"使用(不建議)
df['History']# 用最基本的方式取得指定欄的值(取出的值格式為Series:type(df['History'])
df[ ['History']]#跟上面的結果比較一下兩者之間究竟有什麼差異? (取出的格式為DataFrame)

# (2) 同時取出多個欄
df[['English', 'Math']] # Have to use the form of listdf[ ['English', 'Math', 'Chinese'] ]                                                                                                                                                                                                                                                                                                                                                                                                                     
df[['Chinese', 'English', 'Math']] # DataFrame的順序隨串列順序而定
df['English', 'Math', 'Chinese'] #(X)若直接在中括內使用字串會出錯哦~!!

df.columns[3]
df.columns[1 : 3]# 可以直接用"位置"取得指定欄索引鍵的特定名稱 (格式為字串) 
df[ df.columns[3] ]
df[ df.columns[0 : 2] ] # 取出單一欄時的資料型態為Series
df[ [ df.columns[1], df.columns[3] ] ] # 透過中括號將個別變數的字串轉為串列
df[ df.columns[ [1, 3, 0] ] ] # 也可以這樣做，但..括號越多程式碼越要花時間檢查

# =============================================================================
# 
# =============================================================================
df.index # 檢視所有列索引鍵
#loc含頭也含尾 iloc含頭不含尾
df.loc['Simon']; df.iloc[0] # 使用列索引鍵或位置(type為Series)
df.loc[ ['Mary'] ]; df.iloc[ [2] ] # 使用串列時，回傳資料格式為DataFrame
df.loc[ ['Dora', 'Allen'] ]; df.iloc[[3, 1]] # 指定列索引鍵可任意調整順序( .iloc時需使用串列[ ] )
df.iloc[ : 3] # 可使用數列的方式，搭配.iloc[]取值(不須使用[ ] )
df.iloc[-2 : ] # 直接自倒數第二個開始取值 往前 往後 取值?
df.iloc[ : : 2] # 間隔取值：從第一個開始
df.iloc[-1 : : 2] # 間隔取值：從倒數第一個開始(往後取值，只有1個)
df.iloc[-1 : : -2] # 間隔取值：從倒數第一個開始(往後取值，只有2個)

df.loc[ ['Dora', 'Simon'] ][ ['Chinese', 'English'] ] # 先用列索引鍵取值，再對回傳的DataFrame取值
df.loc[ ['Dora', 'Simon'] , ['Chinese', 'English']] # 使用loc[ [列索引鍵], [欄索引鍵] ]一次性處理
df.iloc[2, 0] # 跟 df.iloc[[2, 0]] 比較一下!! 沒有中括號的參數設定[列索引鍵位置, 欄索引鍵位置]
df.iloc[[2, 0]] 
df.iloc[[3, 0], [3, 1]] # 列索引鍵及欄索引鍵都須要取多個值時，前後皆使用串列
df.iloc[3, [3, 1]] 
df.iloc[[2, 0, 1],2] 

df.loc['Simon' : 'Mary'] # 列索引鍵或欄索引鍵一樣可以用" : "執行連續範圍取值
#df['Simon' : 'Mary'] 與上一列功能相同
#df['English' : 'Chinese'] #(X)不能用在欄索引
df.loc['English' : 'Chinese'] # (X) 預設是列索引鍵在前，所以直接這樣打是不行的!!
#df.loc[列 : 列, 行 : 行]
df.loc[ : , 'English' : 'Chinese'] # 須要利用欄索引鍵時，列索引鍵的參數位置必須要填上" : "
df.loc['Simon' : 'Allen', : ] # 欄索引鍵的參數位置可不填" : " (養成好習慣!!強烈建議填上)
df.loc['Simon' : 'Allen', 'English' : 'Chinese'] # 列索引鍵與欄索引鍵可個別使用
#df.loc[['Simon' : 'Allen'], ['English' : 'Chinese']] (X)
df.iloc[0 : 2, 1 : 4] # 使用位置時，記得含頭不含尾

df.loc[['Allen' , 'Dora'], ['Chinese', 'English']]
df.loc[['Allen' , 'Dora'], ['Chinese', 'English']]=999 #與使用函數不同
print(df)
df=df_orig.copy()

# =============================================================================
# 欄與列的新增與調整
# =============================================================================
df['Gender'] = 30 # 直接新增欄位時，原始資料將被直接置換且無法恢復
df[ ['Gender', 'Test1', 'Test2'] ] = np.nan # 一次新增多個欄位時，可使用串列輸入多個欄索引鍵
df['Gender'] = ['Male', 'Male', 'Female', 'Female'] # 預設新增的欄位會在原資料的最後一欄
#insert新增 drop刪除
df.insert(loc = 1, column = 'Class', value = [1, 2, 1, 2]) # .insert() 可新增欄位至指定位置(無法恢復)

df.drop(labels = 'Gender', axis = 1)
df.drop(labels = 'Gender', axis = 1, inplace = True) # 刪除特定Column (參數labels = 可略)
df.drop(labels = ['Test2', 'Test1'], axis = 1) # 同時刪除多個特定Columns (沒找到將會報錯)
df.reindex(columns = ['Gender', 'Class', 'Chinese', 'English', 'Math', 'History']) # 重新調整欄位順序
#df.drop(labels = ['Class','Gender'],axis = 1,inplace = True) #回復原本資料

df.loc['Nancy'] = np.nan # 新增一列時必須使用.loc 函數，且默認加在最後一列
df.loc[['Simon','Dore']]
df.loc[['A', 'B']] = np.nan # (X) 無法一次新增多列

df.loc['Alisa'] = ['Female', 90, 95, 87, 98] # (X) 注意串列內元素的數量須與欄位數量一致
df.loc['Alisa'] = ['Female', 2, 90, 95, 87, 98] # 務必注意新增的資料順序是否如你所想的那樣!!
df.reindex(index = ['Allen', 'Alisa', 'Dora', 'Mary', 'Simon']) # 調整列索引鍵順序時，參數為index

df.drop(ladels='Alisa') # 刪除特定列時，可省略 axis 參數(預設)
df.drop(ladels=['Nancy', 'Alisa']) # 同時刪除多列時記得使用串列df.drop (['Nancy', 'Alisa'], axis = 'index') # 建議各位捨棄0 與1，寫清楚~
df.drop(ladels=['Nancy', 'Alisa'],axis='index') 
df.drop(ladels='Gender', axis = 'columns') # 刪除欄時務必使用axis 參數df.drop(['Gender', 'Class'], axis = 'columns') # 同時刪除多欄時記得使用串列
print(df)
df.drop(ladels='Gender', axis = 'columns',inplace=True)

df.rename({'Nancy' : 'Nico'}, axis = 'index', inplace = True) # 利用大括號{ } 調整列索引鍵名稱
df['Average'] = df.mean(axis = 'columns') # 計算平均時，NaN會自動被忽略
print(df)
df = df.rename({'Average' : 'Ave.'}, axis = 'columns') # 調整欄索引鍵時亦使用大括號{ }

df['Ave. S.'] = df[ ['Math', 'English', 'History', 'Chinese'] ].mean(axis = 'columns') # 只提取需納入的欄
df.loc['Ave. Subject'] = df.mean(axis = 'index') # 增加新列時使用.loc函數equivlent to df.mean()
print(df)

# =============================================================================
# DataFrame的合併 .concat
# =============================================================================
df=df_orig.copy()
scores2 = {'Art' : [100, 95, 83, 78], 'Music' : [98, 65, 78, 69]}
df2 = pd.DataFrame(scores2, index = ['Allen', 'Simon', 'Mary', 'Dora']) # 列索引鍵順序與原資料一致
scores3 = {'Statistic' : [35, 46, 68, 29, 98], 'Probability' : [50, 62, 98, 78, 100]}
df3 = pd.DataFrame(scores3, index = ['Allen', 'Simon', 'Alice', 'Dora', 'Bryant'])
print(df)
print(df2)
print(df3)


pd.concat([df, df2], axis = 'columns') # 參數axis控制列或欄合併(Ex. 欄合併時則根據列索引鍵整合)
pd.concat([df, df3], axis = 'columns') # 列索引鍵順序不一致且有新增、減少或大小不一致皆可合併
pd.concat([df3, df], axis = 'columns') # 兩個DataFrame的前後順序會影響合併後列與欄索引鍵順序
pd.concat([df, df2, df3], axis = 'columns') # 同時合併多個馬A通哦~
pd.concat([df, df2], axis = 'index') # 參數設定錯誤會怎樣呢? (axis = 'index' 是新增"列索引鍵")

df4 = pd.DataFrame([ [87, 59, 79, 95], [67, 62, 74, 90] ],
                               columns = df.columns, index = ['Amanda', 'Allen'])
df5 = pd.DataFrame([ [98, 94, 92, 88, 100, 99], [100, 99, 89, 78, 70, 98] ], index = ['Bryant', 'Simon'],
                               columns = ['Chinese', 'English', 'Math', 'Art', 'Probability', 'Statistic'])
pd.concat([df, df4], axis = 'index') # 新增資料的欄索引鍵與原資料一致
pd.concat([df, df5], axis = 'index') # 新增資料的欄索引鍵與原資料不同亦會根據欄索引鍵自動對齊

pd.concat([df, df4], axis = 'columns') # Allen可能是同名同姓，但資料卻新增至原資料的Allen
pd.concat([df, df3], axis = 'index') # 只是要新增科目成績，但這邊誤會成增加新人及科目
# =============================================================================
# 不同DataFrame的合併：.merge()
# =============================================================================
df=df_orig.copy()
df.insert(loc = 0, column = 'Gender', value = ['Male','Male','Female','Transgender']) # 新增性別欄位
df_salary = pd.DataFrame([ ['Male', 100], ['Female', 150] ], 
                           columns = ['Gender', 'Salary'])
print(df)
print(df_salary) # 新增一個外部資料DataFrame (Ex. 性別與時薪)

df.merge(df_salary, on = 'Gender') # 預設參數 how = 'inner'，可以省略
df.merge(df_salary, on = 'Gender', how = 'inner')

df.merge(df_salary, on = 'Gender', how = 'outer')
pd.merge(left = df, right = df_salary, on = 'Gender', how = 'left')
pd.merge(left = df, right = df_salary, on = 'Gender', how = 'right')


# =============================================================================
# 資料含有日期的處理方式：pd.to_datetime
# =============================================================================
df=df_orig.copy()
df['Date(x)'] = ['2019-01-29', '2020-02-27', '2021-02-28', '2022-09-29'] # 每個元素只是單純字串格式
df['Date(o)'] = pd.to_datetime(['2019-01-29', '2020-02-27', '2021-02-28', '2022-09-29'])
print(df)

df['Date(x)'] + pd.Timedelta(days = 2) # (X)只是文字
df['Date(o)'] + pd.Timedelta(days = 2) # 指令可換成：pd.Timedelta(2, 'D')可以跨天, (M, Y, y 功能已被取消)

df['Date(M)'] = pd.to_datetime(['2022-01', '2022-02', '2022-03', '2022-04']) # 未完整自動採記當月1 日
df['Date(Y)'] = pd.to_datetime(['2022', '2023', '2024', '2025']) 

df['Date(o)'].dt.year # 在日期型態中單獨取出年份
df['Date(o)'].dt.quarter # 在日期型態中單獨取出所屬季度(1, 2, 3, 4)
df['Date(o)'].dt.month # 在日期型態中單獨取出月份
df['Date(o)'].dt.day # 在日期型態中單獨取出日期
df['Date(o)'].dt.day_name() # 取得當天是星期幾 (注意此函數與上面幾個的差異)

idx_date = ( df['Date(o)'].dt.month == 2 ) # 判斷資料是否屬於某個月份，並儲存判斷式的布林值
df[idx_date]
