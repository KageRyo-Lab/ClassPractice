# -*- coding: utf-8 -*-
"""
Created on Thu Jun 20 09:28:04 2024 
for Machine Learning Final-Exam

@author: Chien-Hsun, Chang 張健勳(1411068014)
"""
import pandas as pd 
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns

# 1. (10%)載入heart.csv資料，並顯示基本資料如下。
df = pd.read_csv("./heart.csv")
print("=== heart.csv基本資料 ===")
df.info()

# 2. (5%)可以發現欄位資料如下，請從資料中找出位心臟病的資料各為多少（target:0和1分別有多少），並輸出相對應資料。
## 了解目標值的分布
# target裡的1和2各50個，資料分布很平均
print("\n=== target資料比例 ===")
targetCount=pd.concat([df['target'].value_counts(),df['target'].value_counts(normalize=True)],axis=1, keys=['個數','百分比'])
print(targetCount)

# 3. (5%)請匯出一相關係數圖，描述heart.csv資料中所有參數的相關性。
# 繪製所有變數的相關係數
plt.figure(figsize=(20,10))
sns.heatmap(df.corr(),annot=True)
plt.show()
# 各變數與目標值的關係性
print("\n=== 相關性 ===")
print(df.corr()['target'])

# 4. (8%)試匯出達到最高心率VS心臟病、年齡VS心臟病圖
# 最大心率VS心臟病
df.groupby('target')['max_hr'].plot(kind='hist', alpha=0.6, bins=30, legend=True)
plt.show()
# 年齡VS心臟病
df.groupby('target')['age'].plot(kind='hist', alpha=0.6, bins=30, legend=True)
plt.show()

# 5. (12%)資料預處理:請將'age','resting_bp','max_hr','fasting_blood_sugar'欄位取出作為X欄位,
#    'target'取出作為Y欄位。並將所有資料，以2/3比例作為訓練集，並以1/3比例作為測試集。
X=df[['age','resting_bp','max_hr','fasting_blood_sugar']]
y=df['target']
from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test=train_test_split(X, y, test_size=0.33, random_state=42)

# 6. (20%)請使用管道器將資料與4個演算法(羅吉斯迴歸, K最近鄰, 支援向量機, 決策樹(max_depth=4))結合，
#    並輸出四種演算法建立之預測模型的分數score(請由大排到小輸出)。注意：數值資料須經過標準化
# 標準化資料
from sklearn.preprocessing import StandardScaler
from sklearn.pipeline import make_pipeline
from sklearn.neighbors import KNeighborsClassifier
from sklearn.linear_model import LogisticRegression
from sklearn.svm import SVC
from sklearn.tree import DecisionTreeClassifier
from sklearn.pipeline import make_pipeline
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import cross_val_score
model_pl=make_pipeline(StandardScaler(),KNeighborsClassifier())
model_pl.fit(X_train, y_train)
model_pl.score(X_test, y_test)
print(model_pl.score(X_test, y_test))
# 計算
models=[LogisticRegression(), SVC(), KNeighborsClassifier(), DecisionTreeClassifier(max_depth=4)]
scores={}
for model in models:
    model_pl=make_pipeline(StandardScaler(), model)
    score=cross_val_score(model_pl, X_train, y_train, scoring='accuracy', cv=10).round(1)    # 輸出為正確率
    scores[model.__class__.__name__]=score.mean()
print("\n=== 演算法大亂鬥(scores = accuracy) ===")
print(pd.Series(scores).sort_values(ascending=False))

# 7. (15%)呈上，請使用五折交叉驗證(cv=5)，並將輸出設定為召回率(recall)，重新計算。
models=[LogisticRegression(), SVC(), KNeighborsClassifier(), DecisionTreeClassifier(max_depth=4)]
scores={}
for model in models:
    model_pl=make_pipeline(StandardScaler(), model)
    score=cross_val_score(model_pl, X_train, y_train, scoring='recall', cv=5)    # 輸出為召回率
    scores[model.__class__.__name__]=score.mean()
print("\n=== 五折交叉驗證(cv=5 ,scores=recall) ===")
print(pd.Series(scores).sort_values(ascending=False))

# 8. (5%)請使用SelectKBest從4個特徵值'age','resting_bp','max_hr','fasting_blood_sugar'中
#    選取兩個影響力最大的特徵值，並輸出之。
from sklearn.feature_selection import SelectKBest, f_classif
selector=SelectKBest(f_classif, k=2)
selector.fit(X_train, y_train)
selector.get_support()
skb = X_test.columns[selector.get_support()]
print("\n===  SelectKBest選2影響力最大特徵===")
print(skb)

# 9. (10%)利用SelectKBest(f_classif, k=2)、SVC演算法計算再取出兩個影響力最大之特徵值後，其正確率、混淆矩陣和綜合報告之結果。
from sklearn.metrics import accuracy_score, confusion_matrix, classification_report
model_pl=make_pipeline(StandardScaler(), SelectKBest(f_classif, k=2), 
KNeighborsClassifier())
model_pl.fit(X_train, y_train)
y_pred=model_pl.predict(X_test)
print("\n=== 結果報告(正確率、混淆矩陣和綜合報告) ===")
print("正確率：", accuracy_score(y_test, y_pred).round(2))
print("混淆矩陣：")
print(confusion_matrix(y_test, y_pred))
print("綜合報告：")
print(classification_report(y_test, y_pred))