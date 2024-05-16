import numpy as np
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