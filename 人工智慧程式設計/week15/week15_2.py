import pandas as pd

dic0={"Apple":25, "Orange":36, "Pinapple":62}
s=pd.Series(dic0)

# 提取價格大於50的品項
print("question1: \n",s[s>50])

# 算s中，Apple和Orange價錢的和
print("\nquestion2: \n",s[["Apple","Orange"]].sum())