import pandas as pd
import seaborn as sns
from sklearn.metrics import accuracy_score
from sklearn.datasets import load_iris
from sklearn.discriminant_analysis import StandardScaler
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import classification_report, confusion_matrix
from sklearn.model_selection import train_test_split
from sklearn.pipeline import make_pipeline

# 匯入 iris 資料集
iris = load_iris()
iris.keys()
print('\n'.join(iris['DESCR'].split('\n')[:18]))

# 彙整資料
df = pd.DataFrame(iris['data'],columns=iris['feature_names'])
df['target']=iris['target']
print(df.head())

f=df[['sepal width (cm)','petal length (cm)', 'target']]
df=df.iloc[-100:]
print(df.head()) #檢視前五筆資料

# 資料型態
df['target'].value_counts()
iris['target_names']
df.info()

# 資料探索
sns.pairplot(df, hue='target', vars=['sepal width (cm)','petal length (cm)'],size=2)
ax=sns.pairplot(df, hue='target', vars=['petal length (cm)'],size=3)

df.corr().round(2)['target']
df.plot(kind='scatter', x='sepal width (cm)', y='petal length (cm)', c='target', cmap='coolwarm', alpha=0.6, figsize=(6,4))

X_cols=['sepal width (cm)','petal length (cm)']
y_col='target'
X=df[X_cols]
y=df[y_col]

X_train, X_test, y_train, y_test= train_test_split(X, y, test_size=0.33, random_state=42)
print('訓練集的筆數:', len(X_train))
print('測試集的筆數:', len(X_test))
print('測試集所佔全部資料的百分比:', len(X_test)/len(X))

# 羅吉斯迴歸
model=LogisticRegression(solver='liblinear')

model.fit(X_train, y_train) #訓練羅吉斯模型
model.coef_ #取得學習參數

y_pred=model.predict(X_test)
y_pred[:5]

pd.DataFrame(zip(y_test, y_pred), columns=['實際','預測']).head()

# 評估
cm=confusion_matrix(y_test, y_pred) #混淆矩陣
print(pd.DataFrame(cm, index=['實際1','實際2'],columns=['預測1','預測2']))
print()
print('整體正確率:', accuracy_score(y_test, y_pred).round(2)) #正確率
print('另一個得到正確率的方法',model.score(X_test, y_test).round(2)) #正確率

model_pl=make_pipeline(StandardScaler(),LogisticRegression(solver='liblinear'))
model_pl.fit(X_train, y_train)
y_pred=model_pl.predict(X_test)
cm=confusion_matrix(y_test, y_pred)
print(pd.DataFrame(cm, index=['實際1','實際2'],columns=['預測1','預測2']))
print()
print('整體正確率:', accuracy_score(y_test, y_pred).round(2)) #正確率

print(classification_report(y_test, y_pred))