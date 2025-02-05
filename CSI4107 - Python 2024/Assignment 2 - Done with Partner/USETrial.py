import tensorflow_hub as hub
import numpy as np
import InvertedIndex
import retreiveRank

# Load Universal Sentence Encoder
embed = hub.load("https://tfhub.dev/google/universal-sentence-encoder/4")

rr = retreiveRank.retreiveRank()
ii = InvertedIndex.InvertedIndex('test')
with open('topics1-50.txt', 'r') as file:
    text = file.read()
queries = retreiveRank.parse_queries(text)
documents = ii.parse_directory()
ranked_documents = []
num = 0
total_queries = len(queries)
total_map = 0.0

def calculate_precision_at_k(rankings, k):
    relevant_count = 0
    precision_sum = 0.0

    for rank in rankings[:k]:
        relevant_count += 1
        precision_sum += relevant_count / (rankings.index(rank) + 1)  # Precision@k

    return precision_sum / k if k > 0 else 0.0

precision_at_10_per_query = {}

for query in queries:
    query_embedding = embed([query])[0]  # Get USE embedding for query

    num1 = 1
    for document in documents:
        print(num, num1)
        num1 += 1
        document_embedding = embed([document])[0]  # Get USE embedding for document

        cosine_sim = np.dot(query_embedding, document_embedding) / (np.linalg.norm(query_embedding) * np.linalg.norm(document_embedding))
        ranked_documents.append((num1, cosine_sim))

    ranked_documents.sort(key=lambda x: x[1], reverse=True)
    precision_at_10 = calculate_precision_at_k([rank[0] for rank in ranked_documents], 10)
    precision_at_10_per_query[query] = precision_at_10
    file = open('ResultsUse.txt', 'a')
    file.write(str(num))
    file.write(str(ranked_documents[:1000]))
    file.close()
    num += 1
    ranked_documents = []

# Calculate average Precision@10 across all queries
average_precision_at_10 = sum(precision_at_10_per_query.values()) / len(precision_at_10_per_query)
print(f"Average Precision@10: {average_precision_at_10}")
