# -*- coding: utf-8 -*-
"""
Created on Wed Jun 19 21:53:56 2024

@author: KageRyo
"""

### KNN

# 匯入燕尾花資料
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
plt.rcParams['font.sans-serif']=['DFKai-sb']
plt.rcParams['axes.unicode_minus']=False
import warnings
warnings.filterwarnings('ignore')
from sklearn.datasets import load_iris
iris=load_iris()
df=pd.DataFrame(iris['data'],columns=iris['feature_names'])
df['target']=iris['target']
df=df[['sepal width (cm)','petal length (cm)','target']]
df=df.iloc[50:]
df.head()

# 將資料整理出X和y
X=df.drop('target',axis=1)
y=df['target']
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test=train_test_split(X, y, test_size=0.33, random_state=42)

# 建立KNN模型的步驟
from sklearn.neighbors import KNeighborsClassifier
model=KNeighborsClassifier()     #初始物件
model.fit(X_train, y_train)      #機器學習
model.score(X_test, y_test)      #正確率的預測(model.score提供簡便的正確率的輸出方式)

# 標準化資料
from sklearn.preprocessing import StandardScaler
from sklearn.pipeline import make_pipeline
model_pl=make_pipeline(StandardScaler(),KNeighborsClassifier())
model_pl.fit(X_train, y_train)
model_pl.score(X_test, y_test)
print(model_pl.score(X_test, y_test))

# 預測結果分析
from sklearn.metrics import confusion_matrix, accuracy_score, classification_report
y_pred=model_pl.predict(X_test)
print('正確率: ', accuracy_score(y_test, y_pred).round(2))
print('混亂矩陣')
print(confusion_matrix(y_test, y_pred))
print('綜合報告')
print(classification_report(y_test, y_pred))


## 判斷邊界繪製
# 繪製未標準化結果的預測邊界
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
            
plot_decision_boundary(X_test, y_test, model, True)
plot_decision_boundary(X_test, y_test, model_pl, True)

## 鄰居數目的選擇
# K參數的選擇
accs=[]
for n in range(3,8):
    model_pl=make_pipeline(StandardScaler(),KNeighborsClassifier(n_neighbors=n))
    model_pl.fit(X_train, y_train)
    print(f'鄰居數{n}，整體正確率:{model_pl.score(X_test, y_test).round(2)}')

# 使用全部特徵值來分析
iris=load_iris()
df=pd.DataFrame(iris['data'],columns=iris['feature_names'])
df['target']=iris['target']
df=df.iloc[50:]
X=df.drop('target',axis=1)
y=df['target']
X_train, X_test, y_train, y_test=train_test_split(X, y, test_size=0.33, random_state=42)
from sklearn.linear_model import LogisticRegression
model_pl_lr=make_pipeline(StandardScaler(), LogisticRegression(solver='liblinear'))
model_pl_lr.fit(X_train, y_train)
print(f'羅吉斯迴歸正確率{model_pl_lr.score(X_test, y_test).round(3)}')
model_pl_knn= make_pipeline(StandardScaler(), KNeighborsClassifier())
model_pl_knn.fit(X_train, y_train)
print(f'KNN正確率{model_pl_knn.score(X_test, y_test).round(3)}')

## 主成分分析PCA
# 創建PCA用的資料
np.random.seed(1)
x=np.linspace(-10, 10,100)
y=2*x+4*np.random.randn(100)
df_pca=pd.DataFrame(zip(x,y),columns=['x0','x1'])
plt.scatter(x, y)
plt.show()
# 選擇能最大化代表資料的軸
from sklearn.decomposition import PCA
pca=PCA(n_components=1)
X_pca=pca.fit_transform(df_pca)
# 以PCA分析結果作一維直線軸
from sklearn.decomposition import PCA
pca=PCA(n_components=1)
X_pca=pca.fit_transform(df_pca)
plt.scatter(x, y)
X_new=pca.inverse_transform(X_pca)
plt.scatter(X_new[:,0], X_new[:,1], c='r', alpha=0.3)
plt.show()
# 主成分分析的轉換係數
print(f'PCA的轉換係數:{pca.components_}')
xy_0=np.array([x[0],y[0]])
print(f'第一筆原始資料:{xy_0}')
print(f'自行運算的內積結果:{np.sum(pca.components_*xy_0)}')
print(f'主成分的第一筆資料:{X_pca[0]}')

# 將PCA導入至鳶尾花資料分析
model_pl= make_pipeline(StandardScaler(), PCA(n_components=2),KNeighborsClassifier())
model_pl.fit(X_train, y_train)
y_pred=model_pl.predict(X_test)
print('整體正確率:', accuracy_score(y_test, y_pred).round(2))

## 管道器連結標準化、SelectKBest和KNN
from sklearn.feature_selection import SelectKBest, f_classif
selector=SelectKBest(f_classif, k=2)
selector.fit(X_train, y_train)
selector.get_support()
X_test.columns[selector.get_support()]  # 取出欄位名稱
# 創建管道器:連結標準化、SelectKBest、KNN
model_pl=make_pipeline(StandardScaler(), SelectKBest(f_classif, k=2), 
KNeighborsClassifier())
model_pl.fit(X_train, y_train)
y_pred=model_pl.predict(X_test)
print(confusion_matrix(y_test, y_pred))
print('整體正確率:', accuracy_score(y_test, y_pred).round(2))