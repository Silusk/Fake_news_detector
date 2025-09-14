#!/usr/bin/env python
# coding: utf-8

# In[1]:


import sys
import joblib
model=joblib.load("model.pkl")
vectorizer=joblib.load("vectorizer.pkl")
input_text=sys.argv[1]
vec=vectorizer.transform([input_text])
answer=model.predict(vec)
if(answer==1):
 print("it is true")
else:
 print("it may be a fake")


# In[ ]:




