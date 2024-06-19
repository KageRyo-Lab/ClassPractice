import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns

plt.rcParams['font.sans-serif']=['DFKai-sb']
plt.rcParams['axes.unicode_minus']=False

import warnings
warnings.filterwarnings('ignore')

df=pd.read_csv('./train.csv')
