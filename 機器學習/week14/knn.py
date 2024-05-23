import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import warnings

from sklearn.datasets import load_iris
from sklearn.model_selection import train_test_split
from sklearn.linear_model import LogisticRegression
from sklearn.neighbors import KNeighborsClassifier
from sklearn.preprocessing import StandardScaler
from sklearn.pipeline import make_pipeline
from sklearn.metrics import confusion_matrix, accuracy_score, classification_report
from sklearn.decomposition import PCA
from sklearn.feature_selection import SelectKBest, f_classif

# 設定圖表
plt.rcParams['font.sans-serif'] = ['DFKai-sb']
plt.rcParams['axes.unicode_minus'] = False

# 載入燕尾花資料
warnings.filterwarnings('ignore')
iris = load_iris()

# 特徵
df = pd.DataFrame(iris['data'], columns=iris['feature_names'])
df['target'] = iris['target']

# 移除類別 0 的資料，只保留類別 1 和 2
df = df[df['target'] != 0]

# 整理 x 和 y
X = df.drop('target', axis=1)
y = df['target']

# 分割資料集
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.33, random_state=42)

# 建構 KNN 模型
model = KNeighborsClassifier()
model.fit(X_train, y_train)
print(f'KNN 模型未標準化正確率: {model.score(X_test, y_test)}')

# 標準化
model_pl = make_pipeline(StandardScaler(), KNeighborsClassifier())
model_pl.fit(X_train, y_train)
print(f'KNN 模型標準化後正確率: {model_pl.score(X_test, y_test)}')

# 預測結果分析
model_pl_lr = make_pipeline(StandardScaler(), LogisticRegression(solver='liblinear'))
model_pl_lr.fit(X_train, y_train)
print(f'羅吉斯迴歸正確率: {model_pl_lr.score(X_test, y_test).round(3)}')

model_pl_knn = make_pipeline(StandardScaler(), KNeighborsClassifier())
model_pl_knn.fit(X_train, y_train)
print(f'KNN 標準化後正確率: {model_pl_knn.score(X_test, y_test).round(3)}')

y_pred = model_pl.predict(X_test)
print('正確率: ', accuracy_score(y_test, y_pred).round(2))
print('混亂矩陣')
print(confusion_matrix(y_test, y_pred))
print('綜合報告')
print(classification_report(y_test, y_pred))

# 使用PCA將資料降維到2維
pca = PCA(n_components=2)
X_train_pca = pca.fit_transform(X_train)
X_test_pca = pca.transform(X_test)

# 重新訓練模型
model_pca = KNeighborsClassifier()
model_pca.fit(X_train_pca, y_train)

# 繪製結果的預測邊界
def plot_decision_boundary(X, y, model, debug=False):
    points = 500
    x1_max, x2_max = X[:, 0].max(), X[:, 1].max()
    x1_min, x2_min = X[:, 0].min(), X[:, 1].min()
    X1, X2 = np.meshgrid(np.linspace(x1_min-0.1, x1_max+0.1, points), np.linspace(x2_min-0.1, x2_max+0.1, points))
    fig, ax = plt.subplots()
    scatter = ax.scatter(X[:, 0], X[:, 1], c=y, cmap='coolwarm', edgecolor='k', s=20)
    ax.contourf(X1, X2, model.predict(np.c_[X1.ravel(), X2.ravel()]).reshape(X1.shape), alpha=0.3, cmap='coolwarm')
    if debug:
        misclassified = X[y != model.predict(X)]
        ax.scatter(misclassified[:, 0], misclassified[:, 1], edgecolor='y', facecolor='none', s=100)
    plt.colorbar(scatter)
    plt.show()

plot_decision_boundary(X_test_pca, y_test, model_pca, True)

# K參數選擇
for n in range(3, 8):
    model_pl = make_pipeline(StandardScaler(), KNeighborsClassifier(n_neighbors=n))
    model_pl.fit(X_train, y_train)
    print(f'鄰居數 {n}，整體正確率: {model_pl.score(X_test, y_test).round(2)}')

# 創建PCA資料
np.random.seed(1)
x=np.linspace(-10, 10,100)
y=2*x+4*np.random.randn(100)
df_pca=pd.DataFrame(zip(x,y),columns=['x0','x1'])
plt.scatter(x, y)

# 以PCA分析結果作一維直線軸
pca=PCA(n_components=1)
X_pca=pca.fit_transform(df_pca)
plt.scatter(x, y)
X_new=pca.inverse_transform(X_pca)
plt.scatter(X_new[:,0], X_new[:,1], c='r', alpha=0.3)

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

# SelectKBest
selector=SelectKBest(f_classif, k=2)
selector.fit(X_train, y_train)
selector.get_support()

# 取欄位名稱
X_test.columns[selector.get_support()]

# 創建管道器
model_pl=make_pipeline(StandardScaler(), SelectKBest(f_classif, k=2), 
KNeighborsClassifier())
model_pl.fit(X_train, y_train)
y_pred=model_pl.predict(X_test)
print(confusion_matrix(y_test, y_pred))
print('整體正確率:', accuracy_score(y_test, y_pred).round(2))