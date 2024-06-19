# -*- coding: utf-8 -*-
"""
Created on Wed Jun 19 22:52:33 2024

@author: KageRyo
"""

### Decision Tree 決策樹
# 載入資料
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
plt.rcParams['font.sans-serif']=['DFKai-sb']
plt.rcParams['axes.unicode_minus']=False
import warnings
warnings.filterwarnings('ignore')
df=pd.read_csv('titanic_train.csv')
df=df.drop(['PassengerId', 'Name', 'Ticket','Cabin'], axis=1)
X_col_num=['Age','SibSp','Parch','Fare']
X_col_cat=['Pclass','Sex','Embarked']
X_cols=X_col_num+X_col_cat
y_col='Survived'
from sklearn.model_selection import train_test_split
X=df[X_cols]
y=df[y_col]
X_train, X_test, y_train, y_test=train_test_split(X, y, test_size=0.33, random_state=42)
print(df.head())      #檢視載入資料

# 加入管道器
from sklearn.pipeline import make_pipeline
from sklearn.impute import SimpleImputer
from sklearn.preprocessing import OneHotEncoder
from sklearn.compose import ColumnTransformer
num_pl=make_pipeline(SimpleImputer(strategy='median'))
cat_pl=make_pipeline(SimpleImputer(strategy='most_frequent'), OneHotEncoder(sparse=False))
data_pl=ColumnTransformer([('num_pl',num_pl, X_col_num),('cat_pl',cat_pl,X_col_cat)])
data_pl.fit_transform(X_train)
print(data_pl.fit_transform(X_train)[:1]) #檢視資料

## 決策樹預測模型
# 使用訓練集觀察結果
from sklearn.tree import DecisionTreeClassifier
from sklearn.metrics import confusion_matrix, accuracy_score
model_pl_tree=make_pipeline(data_pl, DecisionTreeClassifier(random_state=42))
model_pl_tree.fit(X_train, y_train)
y_pred=model_pl_tree.predict(X_train)
print('正確率:', accuracy_score(y_train, y_pred).round(2))
print('混淆矩陣:')
print(confusion_matrix(y_train, y_pred))
# 使用測試集觀察結果
y_pred=model_pl_tree.predict(X_test)
print('正確率:', accuracy_score(y_test, y_pred).round(2))
print('混淆矩陣:')
print(confusion_matrix(y_test, y_pred))

## 解決決策樹過度擬合的問題
# 常用的解決方法:限制決策樹的「深度」
model_pl_tree=make_pipeline(data_pl, DecisionTreeClassifier(max_depth=4, random_state=42))
model_pl_tree.fit(X_train, y_train)
print('「訓練集」的正確率: ', model_pl_tree.score(X_train, y_train).round(2))
print('「測試集」的正確率: ', model_pl_tree.score(X_test, y_test).round(2))
# 用不同的決策樹深度來觀察
acc_train=[]
acc_test=[]
n_depth= range(2,25)
for n in n_depth:
    model_pl_tree=make_pipeline(data_pl, DecisionTreeClassifier(max_depth=n, random_state=42))
    model_pl_tree.fit(X_train, y_train)
    acc_train.append(model_pl_tree.score(X_train, y_train))
    acc_test.append(model_pl_tree.score(X_test, y_test))
plt.plot(n_depth, acc_train, marker='o', label='訓練集')
plt.plot(n_depth, acc_test, c='green', marker='+', ls='--', label='測試集')
plt.xticks(n_depth, n_depth)
plt.legend()
plt.show()
# 使用最小分割樣本數進行分析
acc_train=[]
acc_test=[]
n_range=range(2,100,3)
for n in n_range:
    model_pl_tree=make_pipeline(data_pl, DecisionTreeClassifier(random_state=42, 
                                                                min_samples_split=n))
    model_pl_tree.fit(X_train, y_train)
    acc_train.append(model_pl_tree.score(X_train, y_train).round(2))
    acc_test.append(model_pl_tree.score(X_test, y_test).round(2))
plt.plot(n_range, acc_train, marker='o', label='訓練集')
plt.plot(n_range, acc_test, c='green', marker='+', ls='--', label='測試集')
plt.legend()
plt.show()

## 探索特徵值
# 用決策樹了解哪些特徵值是重要的
model_pl_tree=make_pipeline(data_pl, DecisionTreeClassifier(max_depth=4, random_state=42))
model_pl_tree.fit(X_train, y_train)
tree=model_pl_tree.named_steps['decisiontreeclassifier']
feature_importance=tree.feature_importances_.round(3)
print(feature_importance)   # 檢視資料
# 取出係數的對應欄位
print(f' 數值型特徵值{X_col_num}')
print(f' 類別型特徵值{X_col_cat}')
cat_pl=data_pl.named_transformers_['cat_pl']
oh_cols=cat_pl.named_steps['onehotencoder'].get_feature_names_out(X_col_cat)
print(f' 獨熱編碼後的特徵值。{oh_cols}')
cols=X_col_num+oh_cols.tolist()
print(f' 所有欄位{cols}')
# 係數大小排序
col=pd.DataFrame(feature_importance, index=cols, columns=['係數']).sort_values(by='係數', ascending=False)
print(col)     #檢視資料

## 策樹圖的繪製與解讀
from sklearn.tree import export_graphviz 
import pydot
from IPython.display import Image
features=cols     #features變數存放所有欄位名稱
class_names=['Dead', 'Alive']   #class_names變數存放目標值表呈現的文字意義
dot_data=export_graphviz(
         model_pl_tree.named_steps['decisiontreeclassifier'],
         out_file=None, feature_names=features, class_names=class_names,
         proportion =False, max_depth=3,
         filled=True, rounded=True
         )
graph=pydot.graph_from_dot_data(dot_data)  
graph[0].write_png('tree.png')    #將結果存到檔案裡
Image(graph[0].create_png())

