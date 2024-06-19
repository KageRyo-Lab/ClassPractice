# -*- coding: utf-8 -*-
"""
Created on Wed Jun 19 23:44:36 2024

@author: KageRyo
"""

### 交叉驗證

## 載入資料
# 使用DESCR來了解資料
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
plt.rcParams['font.sans-serif']=['DFKai-sb']
plt.rcParams['axes.unicode_minus']=False
import warnings
warnings.filterwarnings('ignore')
from sklearn.datasets import load_breast_cancer    #載入資料
breast_cancer=load_breast_cancer()
print('\n'.join(breast_cancer['DESCR'].split('\n')[:15]))    #使用DESCR觀察資料

# 檢視特徵
print(breast_cancer['feature_names'])

# 整合資料
df=pd.DataFrame(data=breast_cancer['data'], columns=breast_cancer['feature_names'])
df['target']=breast_cancer['target']
print(df.head()) #檢視資料

# 檢查是否有遺漏
df.info()

# 觀察目標值
print(f'標籤0為{breast_cancer["target_names"][0]}，是惡性腫瘤的意思')
print(df['target'].value_counts(normalize=True))

# 資料探索
sns.pairplot(df, vars=['mean radius','mean texture','mean perimeter','mean area'], hue='target', size=2)

# 散布圖
sns.scatterplot(x='mean radius', y='mean texture', data=df, hue='target')

# 繪製所有變數的相關係數
plt.figure(figsize=(20,10))
sns.heatmap(df.corr(),annot=True)
plt.show()

# 各變數與目標值的關係性
print(df.corr()['target'])

## 整理資料:包括X、y和資料切割
# 欄位處理、將資料切割成訓練集和測試集
X_cols=df.columns.drop('target')
X=df[X_cols]
y=df['target']
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test= train_test_split(X, y, test_size=0.33, random_state=42)

## 機器學習模型大亂鬥-不完美版
# 載入所有模組，並進行模型學習和預測
from sklearn.linear_model import LogisticRegression
from sklearn.svm import SVC
from sklearn.neighbors import KNeighborsClassifier
from sklearn.tree import DecisionTreeClassifier
from sklearn.pipeline import make_pipeline
from sklearn.preprocessing import StandardScaler
models=[LogisticRegression(), SVC(), KNeighborsClassifier(), 
        DecisionTreeClassifier(max_depth=5)]
scores={}
for model in models:
    model_pl=make_pipeline(StandardScaler(), model)
    model_pl.fit(X_train, y_train)
    score=model_pl.score(X_test, y_test)
    scores[model.__class__.__name__]=score
print(scores)                #檢視資料
# 將執行結果整理到DataFrame，並加以排序
print(pd.Series(scores).sort_values(ascending=False))

## 交叉驗證
# 測試隨機切割技術(隨機創數做測試)
import numpy as np
from sklearn.model_selection import KFold
data=np.arange(10,18)
kfold=KFold(n_splits=4)
for train_idx, test_idx in kfold.split(data):
    print(f'訓練集資料有: {data[train_idx]}，測試集資料有{data[test_idx]}')
    
# 羅吉斯迴歸的五折交叉驗證
from sklearn.model_selection import KFold
kfold=KFold(n_splits=5)
model_pl_lr=make_pipeline(StandardScaler(), LogisticRegression())
scores=[]
for train_idx, test_idx in kfold.split(X_train, y_train):
    model_pl_lr.fit(X_train.iloc[train_idx], y_train.iloc[train_idx])
    scores.append(model_pl_lr.score(X_train.iloc[test_idx], y_train.iloc[test_idx]))
print(f'5折交叉驗證的結果{np.mean(scores)}')     #檢視資料

# 五折交叉驗證的簡單方法
from sklearn.model_selection import cross_val_score
model_pl_lr=make_pipeline(StandardScaler(), LogisticRegression())
scores=cross_val_score(model_pl_lr, X_train, y_train, scoring='accuracy', cv=5)
print(f'五折交叉驗證的每次結果{scores}')
print(f'五折交叉驗證的平均結果{np.mean(scores)}')

# 五折交叉驗證(輸出為召回率)
scores=cross_val_score(model_pl_lr, X_train, y_train, scoring='recall', cv=5)
print(f'五折交叉驗證的每次結果{scores}')
print(f'五折交叉驗證的平均結果{np.mean(scores)}')

## 機器學習模型大亂鬥-正確版
# 機器學習模型比較(正確版)
models=[LogisticRegression(), SVC(), KNeighborsClassifier(), 
        DecisionTreeClassifier(max_depth=10)]
scores={}
for model in models:
    model_pl=make_pipeline(StandardScaler(), model)
    score=cross_val_score(model_pl, X_train, y_train, scoring='accuracy', cv=10)
    scores[model.__class__.__name__]=score.mean()
print(pd.Series(scores).sort_values(ascending=False))
# 檢視羅吉斯迴歸的各項數據報表
from sklearn.metrics import accuracy_score, confusion_matrix, classification_report
model_pl_lr=make_pipeline(StandardScaler(), LogisticRegression())
model_pl_lr.fit(X_train, y_train)
y_pred=model_pl_lr.predict(X_test)
print('正確率:', accuracy_score(y_test, y_pred).round(3))
print('混淆矩陣:')
print(confusion_matrix(y_test, y_pred))
print('綜合報告:')
print(classification_report(y_test, y_pred))

## 決策樹圖檢視重要變數
# 用決策樹檢測前五重要的變數和係數
model_tree=DecisionTreeClassifier(max_depth=10)
model_tree.fit(X_train, y_train)
print(pd.Series(model_tree.feature_importances_, index=X.columns).sort_values(ascending=False).head())
# 將決策樹結果繪圖
from sklearn.tree import export_graphviz
import pydot
from IPython.display import Image
features=X.columns
class_names=['Malignant','Benign']
dot_data=export_graphviz(model_tree, out_file=None, feature_names=features, class_names=class_names, proportion=False, 
max_depth=3, filled=True, rounded=True)
graph=pydot.graph_from_dot_data(dot_data)
graph[0].write_png('tumor.png')
Image(graph[0].create_png(), width=800)
