# Mikaela Dobie 300164161
# Matthew Pearce 300199565
import math
import re
import tokenizer
from collections import Counter
from bs4 import BeautifulSoup
import os
import typing

class InvertedIndex:
    def __init__(self, search_directory=None):
        self.documents = None
        # df indx
        self.index = {} # token:[df, [(doc_id, tf),...]]
        # inverse doc frequency index
        self.IDF_index = {} #token:idf
        # document weight vectors
        self.tf_idf = {} # doc_id:[weight1,weight2,...]
        #directory to pull corpus from
        self.search_directory = search_directory
        #self.all_tokens = []


       # self.invertedIndex = InvertedIndex()
        #self.invertedIndex.documents = parse_directory(search_directory)
       # self.invertedIndex.build_index()

    # build IDF_index using df in index
    def build_idf(self):
        for token, (df, postings) in self.index.items():
            idf = math.log( len(self.documents)  / df )
            self.IDF_index[token] = idf

    # construct tf-idf weight vectors 

    def build_tf_idf(self):
        for doc_id, document in self.documents.items():
            weights = {}
            #type weights = Literal [type(self.index.items()[0]):float]
            for token, (df, postings) in self.index.items():
                # get tf
                tf = self.get_tf(doc_id, token)
                
                # get idf
                idf = self.get_idf(token)
                weights[token] = tf*idf
            self.tf_idf[doc_id] = weights
        #  normalize
        for doc_id, weights in self.tf_idf.items():
            # calculate vector lengths
            vector_length = math.sqrt(sum(weight ** 2 for weight in weights.values()))
            # normalize the tf-idf vector
            for term, weight in weights.items():
                if vector_length != 0:
                    weights[term] = float(weight / vector_length)
                else:
                    weights[term] = float(0)

            self.tf_idf[doc_id] = weights

    # populate df index, idf index, and weights vector dictonaries
    def build_index(self):
        if self.documents == None:
            self.documents = self.parse_directory()
        for doc_id, document in self.documents.items():
            preprocessed_tokens = tokenizer.preprocess(document) #get tokens
            token_counts = Counter(preprocessed_tokens) #gather counts
            doc_length = len(preprocessed_tokens) #total number of tokens in document

            for token, count in token_counts.items():
                #self.all_tokens.append(token)
                tf = count / doc_length # calculate term frequency
                if token not in self.index:
                    # token not found; create a new entry in index
                    self.index[token] = [1, [(doc_id, tf)]] #set df = 1
                else: # token exists in index
                    # append new entry for given token and increment df
                    df, postings = self.index[token]
                    self.index[token] = (df+1, postings + [(doc_id, tf)])
        # finished calculating tf, now can calculate idf
        self.build_idf()
        # idf complete; calculate vectors
        self.build_tf_idf()

    # return the list of postings for a given token
    def get_postings(self, token):
        df, postings = self.index.get(token, (0, []))
        return postings
        
    # return the df of a given token
    def get_df(self, token):
        df, postings = self.index.get(token, (0, []))
        return df
    
    # return the tf of a given doc_id and token pair
    def get_tf(self, doc_id, token):
        postings = self.get_postings(token)
        for d_id, tf in postings:
            if d_id == doc_id:
                return tf
        return 0

    # get the idf of a given token
    def get_idf(self, token):
        return self.IDF_index.get(token, 0)

    # get the weights vector for a given document
    def get_vector(self, doc_id):
        return self.tf_idf.get(doc_id)
    
    # get the vector length of a given document
    def get_vector_length(self, doc_id):
        vector_length = 0
        for weight in self.get_vector(doc_id).values():
            vector_length += math.pow(weight,2)
        return math.sqrt(vector_length)

    # helper function that parses a file into individual documents
    # return parsed_documents: dictonary uses docno as index and stores contents of text tag
    def parse_file(self, file_object):
        parsed_documents = {}

        contents = file_object.read()

        # Fix XML formatting
        contents = re.sub(r'<1ST_LINE>', '<FIRST_LINE>', contents)
        contents = re.sub(r'</1ST_LINE>', '</FIRST_LINE>', contents)

        contents = re.sub(r'<2ND_LINE>', '<SECOND_LINE>', contents)
        contents = re.sub(r'</2ND_LINE>', '</SECOND_LINE>', contents)

        # Add root elem
        contents = '<root>' + contents + '</root>'

        soup = BeautifulSoup(contents, 'lxml-xml')

        # Extract individual docs
        documents = soup.find_all('DOC')
        for document in documents:
            doc_number = document.find('DOCNO').get_text(strip=True)
            if document.find('TEXT') != None:
                doc_text = document.find('TEXT').get_text(strip=True)
            else:
                doc_text = ""
            parsed_documents[doc_number] = doc_text
        return parsed_documents

    # parse all files in a given directory
    # returns a dictonary of docno's and text
    def parse_directory(self):
        directory = self.search_directory
        parsed_documents = {}
        for file in os.listdir(directory):
            with open((directory+'/'+file), 'r', encoding='utf-8') as file:
                parsed_documents.update(self.parse_file(file))    
        return parsed_documents
    

    def calculate_query_vector(self, query):
        query_vector = {}
        query_tokens = tokenizer.preprocess(query)
        # Calculate term frequency (TF) for each term in the query
        query_tf = {}
        for token in query_tokens:
            if token in query_tf:
                query_tf[token] += 1
            else:
                query_tf[token] = 1
        # Calculate inverse document frequency (IDF) for each term in the query
        query_idf = {}
        for token in query_tokens:
            if token in self.IDF_index:
                query_idf[token] = math.log( len(self.documents) / self.get_df(token))
            else:
                query_idf[token] = 0
        # Calculate the TF-IDF weight for each term in the query
        #type query_vector = dict[object:float]
        for token in query_tokens:
            if token in self.IDF_index:
                query_vector[token] = query_tf[token] * query_idf[token]

        # Normalize the query vector
        vector_length = float(math.sqrt(sum(value ** 2 for value in query_vector.values())))
        #query_vector = dict[object:float]
        query_vector = {term: float(value / vector_length) for term, value in query_vector.items()}

        return query_vector, query_tokens


def main(search_directory):
    invertedIndex = InvertedIndex()
    invertedIndex.search_directory = search_directory
    invertedIndex.documents = invertedIndex.parse_directory()
    #invertedIndex.build_index()





    #token = ''
    #print(invertedIndex.get_postings(token))
    #print(invertedIndex.get_idf(token))
    #print(invertedIndex.get_df(token))

    #print(invertedIndex.index)
   # print(invertedIndex.IDF_index)
    #print(invertedIndex.get_vector('AP880212-0171'))
    #print(invertedIndex.get_vector_length('AP880212-0171'))

#main('test')





