import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.metrics import accuracy_score
from sklearn.datasets import load_iris
from sklearn.discriminant_analysis import StandardScaler
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import classification_report, confusion_matrix
from sklearn.model_selection import train_test_split
from sklearn.pipeline import make_pipeline
from sklearn.metrics import precision_score, recall_score
from sklearn.metrics import precision_recall_curve
from sklearn.metrics import roc_curve

# 設定字體為標楷體
plt.rcParams['font.sans-serif'] = ['DFKai-SB']
plt.rcParams['axes.unicode_minus'] = False

# 匯入 iris 資料集
iris = load_iris()
iris.keys()
print('\n'.join(iris['DESCR'].split('\n')[:18]))

# 彙整資料
df = pd.DataFrame(iris['data'], columns=iris['feature_names'])
df['target'] = iris['target']
print(df.head())

f = df[['sepal width (cm)', 'petal length (cm)', 'target']]
df = df.iloc[-100:]
print(df.head())  # 檢視前五筆資料

# 資料型態
df['target'].value_counts()
iris['target_names']
df.info()

# 資料探索
sns.pairplot(df, hue='target', vars=['sepal width (cm)', 'petal length (cm)'], height=2)
ax = sns.pairplot(df, hue='target', vars=['petal length (cm)'], height=3)

df.corr().round(2)['target']
df.plot(kind='scatter', x='sepal width (cm)', y='petal length (cm)', c='target', cmap='coolwarm', alpha=0.6, figsize=(6, 4))

X_cols = ['sepal width (cm)', 'petal length (cm)']
y_col = 'target'
X = df[X_cols]
y = df[y_col]

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.33, random_state=42)
print('訓練集的筆數:', len(X_train))
print('測試集的筆數:', len(X_test))
print('測試集所佔全部資料的百分比:', len(X_test) / len(X))

# 羅吉斯迴歸
model = LogisticRegression(solver='liblinear')

model.fit(X_train, y_train)  # 訓練羅吉斯模型
model.coef_  # 取得學習參數

y_pred = model.predict(X_test)
y_pred[:5]

pd.DataFrame(zip(y_test, y_pred), columns=['實際', '預測']).head()

# 評估
cm = confusion_matrix(y_test, y_pred)  # 混淆矩陣
print(pd.DataFrame(cm, index=['實際1', '實際2'], columns=['預測1', '預測2']))
print()
print('整體正確率:', accuracy_score(y_test, y_pred).round(2))  # 正確率
print('另一個得到正確率的方法', model.score(X_test, y_test).round(2))  # 正確率

model_pl = make_pipeline(StandardScaler(), LogisticRegression(solver='liblinear'))
model_pl.fit(X_train, y_train)
y_pred = model_pl.predict(X_test)
cm = confusion_matrix(y_test, y_pred)
print(pd.DataFrame(cm, index=['實際1', '實際2'], columns=['預測1', '預測2']))
print()
print('整體正確率:', accuracy_score(y_test, y_pred).round(2))  # 正確率

# 綜合報告
print(classification_report(y_test, y_pred))

# 輸出訓練資料的前五筆預測資料
y_test_proba = model_pl.predict_proba(X_test.iloc[:5])
pd.DataFrame(y_test_proba, columns=['預測1的機率', '預測2的機率'])

# 判斷門檻與預測機率
y_pred_proba = model_pl.predict_proba(X_test)[:, 1]
y_pred_8 = np.where(y_pred_proba >= 0.8, 2, 1)
y_pred_8[:5]

# 計算不同門檻的精確率和召回率
scores = []
y_pred_proba = model_pl.predict_proba(X_test)[:, 1]  # 使用[:,1]取得類別的預測機率
for threshold in np.arange(0, 1, 0.1):  # 設定判斷門檻從0, 0.1, 0.2,...,1
    y_pred = np.where(y_pred_proba >= threshold, 2, 1)  # 透過np.where取得不同門檻的預測結果
    prec = precision_score(y_test, y_pred, pos_label=2)
    recall = recall_score(y_test, y_pred, pos_label=2)
    scores.append([threshold, prec, recall])  # 將所有結果存在scores串列
df_p_r = pd.DataFrame(scores, columns=['門檻', '精確率', '召回率'])
df_p_r.sort_values(by='門檻')

# PRC圖
df_p_r = pd.DataFrame(scores, columns=['門檻', '精確率', '召回率'])
df_p_r.sort_values(by='門檻')
ax = df_p_r.plot(x='召回率', y='精確率', marker='o')
ax.set_xlabel('類別2召回率')
ax.set_ylabel('類別2精確率')
for idx in df_p_r.index:
    ax.text(x=df_p_r.loc[idx, '召回率'], y=df_p_r.loc[idx, '精確率'] - 0.02, s=df_p_r.loc[idx, '門檻'].round(1))

prec, recall, thres = precision_recall_curve(y_test, y_pred_proba, pos_label=2)  # precision_recall_curve的輸入參數是機率值
df_p_r = pd.DataFrame(zip(thres, prec, recall), columns=['門檻', '精確率', '召回率'])
print(df_p_r.tail())  # 顯示判斷門檻最大的前五筆
ax = df_p_r.plot(x='召回率', y='精確率', marker='o')
for idx in df_p_r.index:
    ax.text(x=df_p_r.loc[idx, '召回率'], y=df_p_r.loc[idx, '精確率'] - 0.02, s=df_p_r.loc[idx, '門檻'].round(2))

# ROC圖
scores = []
y_pred_proba = model_pl.predict_proba(X_test)[:, 1]
for threshold in np.arange(0, 1, 0.1):
    y_pred = np.where(y_pred_proba >= threshold, 2, 1)
    tpr = recall_score(y_test, y_pred, pos_label=2)  # tpr為類別2的召回率
    fpr = 1 - recall_score(y_test, y_pred, pos_label=1)  # fpr為類別1的錯誤率
    scores.append([threshold, tpr, fpr])
df_roc = pd.DataFrame(scores, columns=['門檻', '敏感度', '1-特異度'])
df_roc.sort_values(by='門檻').head()

fpr, tpr, thres = roc_curve(y_test, y_pred_proba, pos_label=2)
df_roc = pd.DataFrame(zip(thres, fpr, tpr), columns=['門檻', '1-特異度', '敏感度'])
print(df_roc.head())
ax = df_roc.plot(x='1-特異度', y='敏感度', marker='o')
for idx in df_roc.index:
    ax.text(x=df_roc.loc[idx, '1-特異度'], y=df_roc.loc[idx, '敏感度'] - 0.05, s=df_roc.loc[idx, '門檻'].round(1))

plt.show()
