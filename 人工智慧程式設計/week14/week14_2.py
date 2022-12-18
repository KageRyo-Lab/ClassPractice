import numpy as np
import numpy.linalg as la

a=np.array([[0,2,-2],[7,4,3],[8,-4,-5]])
b=np.array([[17],[12],[16]])

x=la.solve(a,b)
print(x)