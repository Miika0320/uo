# Mikaela Dobie 300164161
# Matthew Pearce 300199565
from transformers import BertTokenizer, BertModel
import torch
import InvertedIndex
import retreiveRank

tokenizer = BertTokenizer.from_pretrained('bert-base-uncased')
model = BertModel.from_pretrained('bert-base-uncased')

rr = retreiveRank.retreiveRank()
ii = InvertedIndex.InvertedIndex('test')
with open('topics1-50.txt', 'r') as file:
    text = file.read()
queries = retreiveRank.parse_queries(text)
documents = ii.parse_directory()
ranked_documents = []
num =0
total_queries = len(queries)
total_map = 0.0

def calculate_precision_at_k(rankings, k):
    relevant_count = 0
    precision_sum = 0.0

    for rank in rankings[:k]:
        relevant_count += 1
        precision_sum += relevant_count / (rankings.index(rank) + 1)  # Precision@k

    return precision_sum / k if k > 0 else 0.0

percision10pq = [len(queries)]
#print(f'lenq {len(queries)}')

for query in queries:
    # process query values once outside of document for loop
    
    query_tokens = tokenizer.tokenize(query)
    query_inputs = tokenizer.encode_plus(query, add_special_tokens=True, return_tensors='pt')
    
    num1 = 1
    
    for doc_id, document in enumerate(documents):
        print(num,num1)
        num1+=1
        if num1>=len(documents):
            break
        # Tokenize document
        
        document_tokens = tokenizer.tokenize(document)

        # Generate embeddings for query and document
        query_inputs = tokenizer.encode_plus(query, add_special_tokens=True, return_tensors='pt')
        document_inputs = tokenizer.encode_plus(document, add_special_tokens=True, return_tensors='pt')

        
        try:
            with torch.no_grad():
                query_outputs = model(**query_inputs)
                document_outputs = model(**document_inputs)
        except Exception as e:
            continue
        # Get last layer hidden states (CLS token for single sentence)
        query_embedding = query_outputs.last_hidden_state[:, 0, :]
        document_embedding = document_outputs.last_hidden_state[:, 0, :]

        # Calculate cosine similarity
        
        cosine_sim = torch.nn.functional.cosine_similarity(query_embedding, document_embedding, dim=1)
        


        # Example re-ranking based on cosine similarity
        ranked_documents.append((doc_id, float(cosine_sim.item())))

    #ranked_documents.sort(key=lambda x: x[1], reverse=True)
    ranked_documents.sort(key=lambda x: x[1], reverse=True)
    file = open('Results.txt', 'a')
    file.write(str(num))
    file.write(str(ranked_documents[:1000]))
    file.close()
    #ap = calculate_average_precision([rank[0] for rank in ranked_documents], relevant[query])
    #total_map += ap
    precision_at_10 = calculate_precision_at_k([rank[0] for rank in ranked_documents], 10)
    
    percision10pq.append(precision_at_10)
    num = num + 1
    ranked_documents = []
# Calculate average Precision@10 across all queries
average_precision_at_10 = sum(percision10pq) / len(percision10pq)
print(f"Average Precision@10: {average_precision_at_10}")

#print(total_map / total_queries)


def calculate_average_precision(rankings, relevant_docs):
    precision_values = []
    relevant_count = 0

    for idx, rank in enumerate(rankings):
        if rank in relevant_docs:
            relevant_count += 1
            precision_values.append(relevant_count / (idx + 1))  # Precision@idx

    if len(precision_values) == 0:
        return 0.0

    return sum(precision_values) / len(relevant_docs)