# -*- coding: utf-8 -*-
"""
Created on Wed Jun 19 21:16:12 2024

@author: KageRyo
"""

### 羅吉斯迴歸

## Sigmoid函數
# 1. 函數的輸出值在0~1之間
# 2. 函數是對稱的
# 3. 判斷切割點在x=0或y=0.5
# 4. 當x>0或y>0，可判斷為1，否則為0
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
plt.rcParams['font.sans-serif']=['DFKai-sb']
plt.rcParams['axes.unicode_minus']=False
import warnings
warnings.filterwarnings('ignore')
x=np.linspace(-10,10,1000)
y=1/(1+np.exp(-x))
plt.plot(x,y)
plt.axhline(0.5, c='k', ls='--')
plt.axvline(0, c='k', ls='--')
plt.annotate('切割點(0,0.5)', xy=(0,0.5))

## 載入燕尾花資料集
from sklearn.datasets import load_iris
iris=load_iris()
iris.keys() # 列出索引鍵
# 使用DESCR了解資料的來龍去脈
print('\n'.join(iris['DESCR'].split('\n')[:18]))
# 資料整合到DataFrame
df=pd.DataFrame(iris['data'],columns=iris['feature_names'])
df['target']=iris['target']
print(df.head()) #檢視前五筆資料
# 為了能繪製預測的邊界圖，目前僅取用兩個欄位做練習，資料也先取第50筆後的資料
# 取用欄位:sepal width(cm)、petal length(cm)
df=df[['sepal width (cm)','petal length (cm)', 'target']]
df=df.iloc[50:]
print(df.head()) #檢視前五筆資料

## 了解目標值的分布
# target裡的1和2各50個，資料分布很平均
df['target'].value_counts()
# 了解1和2所表示的類別
iris['target_names']
# 觀察資料有無遺漏值
df.info()

## 資料探索
import seaborn as sns
sns.pairplot(df, hue='target', vars=['sepal width (cm)','petal length (cm)'],size=2)
# 判斷邊界
ax=sns.pairplot(df, hue='target', vars=['petal length (cm)'],size=3)
# 檢視變數間的相關係數
df.corr().round(2)['target']
# 繪製散布圖，並用類別值來著色
df.plot(kind='scatter', x='sepal width (cm)', y='petal length (cm)', c='target', cmap='coolwarm', alpha=0.6, figsize=(6,4))
# 將資料整理出X和y
X_cols=['sepal width (cm)','petal length (cm)']
y_col='target'
X=df[X_cols]
y=df[y_col]
# 資料拆分
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.33, random_state=42)
print('訓練集的筆數:', len(X_train))
print('測試集的筆數:', len(X_test))
print('測試集所佔全部資料的百分比:', len(X_test)/len(X))

## 羅吉斯迴歸模型建構
# 初始化物件
from sklearn.linear_model import LogisticRegression
model=LogisticRegression(solver='liblinear')
# 建立羅吉斯模型的步驟-機器學習
model.fit(X_train, y_train)    #訓練羅吉斯模型
model.coef_     #取得學習參數
# 建立羅吉斯模型的步驟-預測模型
y_pred=model.predict(X_test)
y_pred[:5]  # 前五筆
pd.DataFrame(zip(y_test, y_pred), columns=['實際','預測']).head()

## 評估預測結果
# 混淆矩陣
from sklearn.metrics import confusion_matrix, accuracy_score, classification_report
cm=confusion_matrix(y_test, y_pred)     #混淆矩陣
print(pd.DataFrame(cm, index=['實際1','實際2'],columns=['預測1','預測2']))
print()
print('整體正確率:', accuracy_score(y_test, y_pred).round(2))      #正確率
print('另一個得到正確率的方法',model.score(X_test, y_test).round(2))  #正確率
# 精確率與召回率
from sklearn.preprocessing import StandardScaler
from sklearn.pipeline import make_pipeline
model_pl=make_pipeline(StandardScaler(),LogisticRegression(solver='liblinear'))
model_pl.fit(X_train, y_train)
y_pred=model_pl.predict(X_test)
cm=confusion_matrix(y_test, y_pred)
print(pd.DataFrame(cm, index=['實際1','實際2'],columns=['預測1','預測2']))
print()
print('整體正確率:', accuracy_score(y_test, y_pred).round(2))      #正確率
# 綜合報告
print(classification_report(y_test, y_pred))

## 預測結果的機率值
y_test_proba=model_pl.predict_proba(X_test.iloc[:5])
pd.DataFrame(y_test_proba,columns=['預測1的機率','預測2的機率'])
# 判斷門檻與預測機率
y_pred_proba=model_pl.predict_proba(X_test)[:,1]
y_pred_8=np.where(y_pred_proba>=0.8, 2, 1)
y_pred_8[:5]
# 計算不同判斷門檻的精確率和召回率
from sklearn.metrics import precision_score, recall_score
scores=[]
y_pred_proba=model_pl.predict_proba(X_test)[:,1]     #使用[:,1]取得類別的預測機率
for threshold in np.arange(0,1,0.1):       
    #設定判斷門檻從0, 0.1, 0.2,...,1
    y_pred=np.where(y_pred_proba>=threshold,2,1)     #透過np.where取得不同門檻的預測結果
    prec=precision_score(y_test, y_pred, pos_label=2)
    recall=recall_score(y_test, y_pred, pos_label=2)
    scores.append([threshold, prec, recall])       
#將所有結果存在scores串列
df_p_r=pd.DataFrame(scores, columns=['門檻','精確率','召回率'])
df_p_r.sort_values(by='門檻')

## PRC圖
df_p_r=pd.DataFrame(scores, columns=['門檻','精確率','召回率'])
df_p_r.sort_values(by='門檻')
ax=df_p_r.plot(x='召回率',y='精確率', marker='o')
ax.set_xlabel('類別2召回率')
ax.set_ylabel('類別2精確率')
for idx in df_p_r.index:
    ax.text(x=df_p_r.loc[idx,'召回率'], y=df_p_r.loc[idx,'精確率']-0.02, 
            s=df_p_r.loc[idx,'門檻'].round(1))
    
from sklearn.metrics import precision_recall_curve   
prec, recall, thres= precision_recall_curve(y_test, y_pred_proba, pos_label=2)  #precision_recall_curve的輸入參數是機率值
df_p_r=pd.DataFrame(zip(thres, prec, recall), columns=['門檻','精確率','召回率'])
print(df_p_r.tail())        #顯示判斷門檻最大的前五筆
ax=df_p_r.plot(x='召回率', y='精確率', marker='o')
for idx in df_p_r.index:
    ax.text(x=df_p_r.loc[idx,'召回率'], y=df_p_r.loc[idx, '精確率']-0.02, s=df_p_r.loc[idx, '門檻'].round(2))

# ROC圖
from sklearn.metrics import recall_score
scores = []
y_pred_proba = model_pl.predict_proba(X_test)[:, 1]
for threshold in np.arange(0, 1, 0.1):
    y_pred = np.where(y_pred_proba >= threshold, 2, 1)
    tpr = recall_score(y_test, y_pred, pos_label=2)  # tpr為類別2的召回率
    fpr = 1 - recall_score(y_test, y_pred, pos_label=1)  # fpr為類別1的錯誤率
    scores.append([threshold, tpr, fpr])
df_roc = pd.DataFrame(scores, columns=['門檻', '敏感度', '1-特異度'])
df_roc.sort_values(by='門檻').head()
ax = df_roc.plot(x='1-特異度', y='敏感度', marker='o')

# 正確的索引變數使用方法
for idx, row in df_roc.iterrows():
    ax.text(x=row['1-特異度'], y=row['敏感度']-0.03, s=row['門檻'].round(1))

from sklearn.metrics import roc_curve
fpr, tpr, thres = roc_curve(y_test, y_pred_proba, pos_label=2)
df_roc = pd.DataFrame(zip(thres, fpr, tpr), columns=['門檻', '1-特異度', '敏感度'])
print(df_roc.head())
ax = df_roc.plot(x='1-特異度', y='敏感度', marker='o')

# 正確的索引變數使用方法
for idx, row in df_roc.iterrows():
    ax.text(x=row['1-特異度'], y=row['敏感度']-0.05, s=row['門檻'].round(1))

from sklearn.metrics import roc_auc_score
roc_auc_score(y_test, y_pred_proba)

## 繪製預測邊界
def plot_decision_boundary(X_test, y_test, model, debug=False):
    points=500
    x1_max, x2_max= X_test.max()
    x1_min, x2_min= X_test.min()
    X1,X2= np.meshgrid(np.linspace(x1_min-0.1, x1_max+0.1, points),np.linspace(x2_min-0.1, x2_max+0.1, points))
    x1_label, x2_label= X_test.columns
    fig, ax= plt.subplots()
    X_test.plot(kind='scatter', x=x1_label, y=x2_label, c=y_test, cmap='coolwarm', colorbar= False, figsize=(6,4), s=30, ax=ax)
    grids=np.array(list(zip(X1.ravel(),X2.ravel())))
    ax.contourf(X1, X2, model.predict(grids).reshape(X1.shape),alpha=0.3, cmap='coolwarm')
    if debug:
        df_debug=X_test.copy()
        df_debug['y_test']=y_test
        y_pred=model.predict(X_test)
        df_debug['y_pred']=y_pred
        df_debug=df_debug[y_pred != y_test]
        df_debug.plot(kind='scatter', x=x1_label, y=x2_label, s=50, color='none', edgecolor='y', ax=ax)
        for i in df_debug.index:
            ax.text(s=df_debug.loc[i, 'y_test'], x=df_debug.loc[i, x1_label]+0.01, y=df_debug.loc[i, x2_label]-0.05)

plot_decision_boundary(X_test, y_test, model, debug=True)
plot_decision_boundary(X_test, y_test, model_pl, debug=True)
df[(df['sepal width (cm)']==2.8) & (df['petal length (cm)']==4.8)]
model_pl.predict([[3,3]])         

    