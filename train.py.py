#!/usr/bin/env python
# coding: utf-8

# In[46]:


import numpy as np
import pandas as pd
from sklearn.datasets import load_iris
from sklearn.model_selection import train_test_split
import matplotlib.pyplot as plt
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.naive_bayes import MultinomialNB
import joblib


# In[47]:


data=pd.read_csv("fake_news_dataset.csv",encoding="latin1",low_memory=False)
data.head()
#print(data.isnull())
#data.dropna(inplace=True)


# In[48]:


data.dropna(subset=["title","text","label"],inplace=True)
data["combined"]=data["title"]+" "+data["text"]
data.dropna(subset=["combined"],inplace=True)
vectorizer=TfidfVectorizer(stop_words="english",max_features=1000,max_df=1.0,min_df=1)
X=vectorizer.fit_transform(data["combined"])
Y=data["label"]
print(X.shape)
print(Y.shape)


# In[49]:


model=MultinomialNB()


# In[50]:


x_train,x_test,y_train,y_test=train_test_split(X,Y,random_state=42,test_size=0.2)
print(x_train,y_train)


# In[33]:


model.fit(x_train,y_train)


# In[51]:


joblib.dump(model,"model.pkl")
joblib.dump(vectorizer,"vectorizer.pkl")


# In[42]:





# In[ ]:




