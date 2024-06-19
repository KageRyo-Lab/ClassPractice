# -*- coding: utf-8 -*-
"""
Created on Wed Jun 19 22:20:20 2024

@author: KageRyo
"""

### 支援向量機SVM

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
plt.rcParams['font.sans-serif']=['DFKai-sb']
plt.rcParams['axes.unicode_minus']=False
import warnings
warnings.filterwarnings('ignore')
# https://www.kaggle.com/datasets/pankajvermacool/titanic-traincsv
df=pd.read_csv('./titanic_train.csv')

# 資料檢查和資料欄位說明
df.info()

# 檢視目標值Survived的分布
data_survived=pd.concat([df['Survived'].value_counts(),df['Survived'].value_counts(normalize=True)],axis=1, keys=['個數','百分比'])
print(data_survived)

# 移除不必要的欄位
df=df.drop(['PassengerId', 'Name', 'Ticket','Cabin'], axis=1)
print(df.head())

# 檢查遺漏值
print(df.isnull().sum())

## 資料探索
sns.pairplot(data=df, hue='Survived', size=2, 
diag_kws={'bw':0.1})
sns.countplot(x='Sex', order=['female','male'],hue='Survived',data=df)
df.groupby('Survived')['Age'].plot(kind='hist', alpha=0.6, bins=30, legend=True)

# 資料預處理
X_col_num=['Age','SibSp','Parch','Fare']
X_col_cat=['Pclass','Sex','Embarked']
X_cols=X_col_num+X_col_cat
y_col='Survived'
# 標準化
from sklearn.impute import SimpleImputer
from sklearn.preprocessing import StandardScaler
from sklearn.pipeline import make_pipeline
num_pl=make_pipeline(SimpleImputer(strategy='median'),StandardScaler())
print(f'數值型資料的欄位有:{X_col_num}')        #檢查數值管道器的運作
print(num_pl.fit_transform(df[X_col_num])[:3])
# 類別型資料管道器
# 獨熱編碼
from sklearn.preprocessing import OneHotEncoder
cat_pl=make_pipeline(SimpleImputer(strategy='most_frequent'),OneHotEncoder(sparse=False))
cat_pl.fit_transform(df[X_col_cat])
print(cat_pl.fit_transform(df[X_col_cat])[:3]) #檢查類別管道器的運作
# 取得類別管道器中的獨熱編碼欄位
oh=cat_pl.named_steps['onehotencoder']
oh_cols=oh.get_feature_names_out(X_col_cat)
print(oh_cols)  #檢查獨熱編碼欄位
# 將獨熱編碼資料和其他欄位整合在一起
pd.DataFrame(cat_pl.fit_transform(df[X_col_cat]), columns=oh_cols)
print(pd.DataFrame(cat_pl.fit_transform(df[X_col_cat]), columns=oh_cols).head(5)) #檢查是否將獨熱編碼和其他欄位整合
# 整合管道器
from sklearn.compose import ColumnTransformer
data_pl=ColumnTransformer([('num_pl', num_pl, X_col_num),('cat_pl',cat_pl,X_col_cat)])
print(data_pl.fit_transform(df[X_cols])[:1].round(2))   #檢查水平合併器
# 資料拆分
from sklearn.model_selection import train_test_split
X=df[X_cols]
y=df[y_col]
X_train, X_test, y_train, y_test=train_test_split(X, y, test_size=0.33, random_state=42)

## 用管道器連結預測器
# 將SVM預測器加入管道器中
from sklearn.svm import SVC
model_pl_svc=make_pipeline(data_pl, SVC())
print(model_pl_svc)   #檢查管道器加入向量機
# SVM機器學習和預測
from sklearn.metrics import confusion_matrix, accuracy_score, classification_report
model_pl_svc.fit(X_train, y_train)
y_pred=model_pl_svc.predict(X_test)
print('正確率:', accuracy_score(y_test, y_pred).round(2))
print('混淆矩陣:')
print(confusion_matrix(y_test, y_pred))
print('綜合報告:')
print(classification_report(y_test, y_pred))

# 羅吉斯回歸做預測
from sklearn.linear_model import LogisticRegression
model_pl_lr=make_pipeline(data_pl, LogisticRegression())
model_pl_lr.fit(X_train, y_train)
y_pred=model_pl_lr.predict(X_test)
print('正確率:', accuracy_score(y_test, y_pred).round(2))
print('混淆矩陣:')
print(confusion_matrix(y_test, y_pred))
print('綜合報告:')
print(classification_report(y_test, y_pred))

## 管道器的綜合練習
# 將p_class從類別管道移到數值型管道
data_pl=ColumnTransformer([('num_pl', num_pl, ['Age', 'SibSp', 'Parch', 'Fare','Pclass']),
                           ('cat_pl', cat_pl, ['Sex', 'Embarked'])])
model_pl_svc=make_pipeline(data_pl, SVC())
model_pl_svc.fit(X_train, y_train)
y_pred=model_pl_svc.predict(X_test)
print('正確率:', accuracy_score(y_test, y_pred).round(2))
print('混淆矩陣:')
print(confusion_matrix(y_test, y_pred))
# 加入SelectKBest
from sklearn.feature_selection import SelectKBest, f_classif
data_pl=ColumnTransformer([('num_pl', num_pl, X_col_num),('cat_pl', cat_pl, X_col_cat)])
model_pl_svc=make_pipeline(data_pl, SelectKBest(f_classif,k=3), SVC())
model_pl_svc.fit(X_train, y_train)
y_pred=model_pl_svc.predict(X_test)
print('正確率:', accuracy_score(y_test, y_pred).round(2))
print('混淆矩陣:')
print(confusion_matrix(y_test, y_pred))

cols=X_col_num+oh_cols.tolist()       #取出所有欄位名稱，包含獨熱編碼的欄位
selector=model_pl_svc.named_steps['selectkbest']
feature=np.array(cols)[selector.get_support()]   #先將資料變成array的資料型態，再用布林值取出欄位名稱
print(feature) #檢查所取出特徵值欄位

## KBinsDiscretizer
from sklearn.preprocessing import KBinsDiscretizer
X_col_num=['Fare','Age']    #設定欄位
X_col_bin=['SibSp','Parch']
X_col_cat=['Pclass','Sex','Embarked']
num_pl=make_pipeline(SimpleImputer(strategy='mean'),StandardScaler())                                 #資料管道器
bin_pl=make_pipeline(SimpleImputer(strategy='mean'), KBinsDiscretizer(n_bins=5, encode='ordinak'))
cat_pl=make_pipeline(SimpleImputer(strategy='constant', fill_value='missing'), OneHotEncoder())
data_pl=ColumnTransformer([('num',num_pl,X_col_num),('bin',bin_pl,X_col_bin),('cat',cat_pl,X_col_cat)])
data_pl=ColumnTransformer([('num',num_pl, X_col_num),('bin',bin_pl, X_col_bin),('cat',cat_pl, X_col_cat)]) #合併後的資料管道器
model_pl=make_pipeline(data_pl,SVC())   #模型預測
model_pl_svc.fit(X_train, y_train)
y_pred=model_pl_svc.predict(X_test)
print('正確率:', accuracy_score(y_test, y_pred).round(2))
print('混淆矩陣:')
print(confusion_matrix(y_test, y_pred))
