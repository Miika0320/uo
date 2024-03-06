Readme for CSI4107 Assignment 1

Matthew Pearce 300199565
Mikaela Dobie 300164161

Tasks:
Tokenizer -> Mikaela
Decided on using Python based on the NLTK stopwords package being available. It also has the PorterStemmer() function within the NLTK package.
Preprocess: Takes a document and returns list of tokens, the list of tokens gets filtered of all stopwords, and lastly the filtered list gets stemmed and returned.

InvertedIndex -> Mikaela and Matthew

Parses a list of documents and builds an index of the tokens within each. Uses BeautifulSoup to read the files.
Mikaela did a basic frame of the file where it would take one file, use tokenizer to preprocess and put it in an index.
Matthew added the compatibility with the files, weighted vectors, idf/ tf weighting

RetrieveRank -> Matthew and Mikaela

Asks for query and then uses the index and tokenizer to search documents and prints a list containing the 1000 best matches. Uses math collection for dotproduct and other calculations.

Matthew did everything but the cosine comparison and rank list creation
Mikaela did the cosine comparison and rank list creation

readme -> Mikaela and Matthew

Run the code by compiling and running RetreiveRank on with tokenizer and invertedindex and the query text file in the same directory along with a folder of documents there as well.

Change the txt file name on line 85 to the query text file
Change the main argument line to the folder name of the documents on line 93 to run


