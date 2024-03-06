import InvertedIndex
#import numpy
from bs4 import BeautifulSoup
import math

class retreiveRank:
    def __init__(self):
        None

    # retreive doc_ids that match atleast one query token
    def retreive(self, invertedIndex, query_tokens):
        doc_ids = set()
        for token in query_tokens:
            postings = invertedIndex.get_postings(token)
            for posting in postings:
                doc_id, tf = posting
                doc_ids.add(doc_id)
        return list(doc_ids)
            
    # get vectors to be compared
    def get_vectors(self,invertedIndex, doc_ids):
        vectors = {}
        for doc_id in doc_ids:
            if doc_id in invertedIndex.tf_idf:
                vectors[doc_id] = invertedIndex.tf_idf[doc_id]
        return vectors
                
    # rank doc_ids based on query vector
    def rank(self,doc_vectors, query_vector):
        ranked = []
        
        for doc, weight in doc_vectors.items():
            #print(weight)
            ranked.append((doc,self.cosSim(weight, query_vector)))
        ranked.sort(key=lambda i: i[1], reverse=True)  # Sorting the list

        if len(ranked) > 1000:
            return ranked[:1000]  # Returning the top 1000 ranked documents
        else:
            return ranked
    # compares two vectors by cosine similarity    
    def cosSim(self, doc_vector, query_vector):
        dot_product = sum(doc_vector[key]*query_vector.get(key, 0) for key in doc_vector)
        #print(dot_product)
        #dot_product = numpy.dot(doc_vector.values(), query_vector.values())
        magnitude_doc = math.sqrt(sum(value**2 for value in doc_vector.values()))
        magnitude_query = math.sqrt(sum(value**2 for value in query_vector.values()))
        cosine = dot_product / (magnitude_doc * magnitude_query)
        return cosine

    def query(self, invertedIndex, query, num):
        
        query_vector, query_tokens = invertedIndex.calculate_query_vector(query)

        doc_ids = self.retreive(invertedIndex, query_tokens)

        #print("query vector:",query_vector)
        #print("doc id's", doc_ids)


        doc_vectors = self.get_vectors(invertedIndex, doc_ids)
 
        ranking = self.rank(doc_vectors, query_vector)
        #print(ranking)
        file = open('Results.txt', 'a')
        file.write(str(num))
        file.write(str(ranking))
        file.close()


def parse_queries(text):
    soup = BeautifulSoup(text, "html.parser")
    queries = []
    for top_tag in soup.find_all("top"):
        qtext = top_tag.find("num").get_text().strip() + top_tag.find("title").get_text().strip() + top_tag.find("desc").get_text().strip() + top_tag.find("narr").get_text().strip()
        
        queries.append(qtext)
    return queries

def main(search_directory):
    corpus_index = InvertedIndex.InvertedIndex(search_directory)
    corpus_index.build_index()
    rr = retreiveRank()
    num = 0
    with open('topics1-50.txt', 'r') as file:
        text = file.read()
    queries = parse_queries(text)
    for query in queries:
       retreiveRank.query(rr,corpus_index,query, num)
       num = num+1
    

main('coll')

