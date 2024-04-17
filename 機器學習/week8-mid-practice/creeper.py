import pandas as pd
import numpy as np

"""
import data
"""
dict_ = {'Math':[90,50,70,70],'English':[60,70,90,55],'History':[33,87,96,59]}

df_csv = pd.read_csv('./Big Data Sample (CSV).csv')

list_ = [[90,50,70,70],[60,70,90,55],[33,87,96,59]]

"""
data backup
"""
df_csv_bak = df_csv.copy()
dict_bak = dict_.copy()
list_bak = list_.copy()

"""
data to dataframe
"""
df_dict = pd.DataFrame(dict_bak)
df_list = pd.DataFrame(list_bak,index=['Math','English','History'])

"""
change row and column
"""
df_dict.insert(loc=0, column='test', value=[50,0,0,0]) #must fill values(or use np.nan instead)
df_dict = df_dict.drop(labels='test',axis=1) #remove column called 'test'(axis 0 = raw,axis 1 = column)

"""
view data(avarage,maximum,minimum...)
max(),min(),mean(),median(),std(),var(),mad(),sum(),count(),prod()
"""
df_dict.max() #最大值
df_dict.min() #最小值
df_dict.mean() #平均值
df_dict.median() #平均值
df_dict.std() #標準差

test = df_csv[df_csv['年資']>40]