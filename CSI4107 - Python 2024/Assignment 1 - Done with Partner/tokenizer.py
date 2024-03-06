import nltk  #uses nltk to get general stopword dictionary
nltk.download('stopwords')

import re
from nltk.corpus import stopwords
from nltk.stem import PorterStemmer

#returns document as a list of tokens
def tokenize(document):
    tokens = re.findall(r'\b\w+\b', document.lower())
    return tokens

#removes stopword tokens from token list and returns the filtered version
def remove_stopwords(tokens):
    stop_words = set(stopwords.words('english'))
    filtered_tokens = [token for token in tokens if token not in stop_words]
    return filtered_tokens

#returns stemmed tokens
def stem_tokens(tokens):
    porter = PorterStemmer()
    stemmed_tokens = [porter.stem(token) for token in tokens]
    return stemmed_tokens

#preprocesses a document by tokenizing it, removing stopwords, and stemming the list.
def preprocess(document):
    tokens = tokenize(document)
    tokens = remove_stopwords(tokens)
    tokens = stem_tokens(tokens)
    return tokens
