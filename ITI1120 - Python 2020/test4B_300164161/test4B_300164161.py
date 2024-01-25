'''
I understand the importance of professional integrity in my education and future career
in engineering or computer science. I hereby certify that I have done and will do all work
on this examination entirely by myself, without outside assistance or the use of unauthorized
information sources. Furthermore, I will not provide assistance to others.
'''

# READ THE ABOVE STATEMENT
#
# PUT YOUR NAME AND STUDENT NUMBER HERE
#
# Mikaela Dobie 300164161
#
# By putting your name here you are signing the statement above and
# agreeing to the TEST 3 (integrity rules) listed on the first page of the test

#########################
# QUESTION 1
#########################

def mekong(L, div):
    '''(list of int, list of int)-> list of tuples

     Preconditions:
    - There are no two elements in the list L that are the same,
      and list L is sorted from smallest to the largest number
    - List div is not empty and it does not contain zero

    This function should return a list containing the following
    pairs of numbers from L: 
    For every pair of distinct integers from L, you need to test
    if both of those numbers are divisible by at least one of
    the numbers in list div. If yes, that pair should be added to
    the resulting list, otherwise it should not be added.
    A pair should be added at most ONCE.

    For example if L=[5, 16, 24, 50, 72] and div=[3, 5, 8, 10] the function
    should return list of tuples [(5, 50), (16, 24), (16, 72), (24, 72)] because
    5 and 50 are both divisible by 5,
    16 and 24 are both divisible by 8
    16 and 72 are both divisible by 8
    24 and 72 are both divisible by 3 (and 8)
    The other pairs are not there since they do now have a common divisor in list div.
    For example pair (5,16) is not there since there is no number in div that
    divides both 5 and 16
    
   
    >>> mekong([5, 16, 24, 50, 72], [3, 5, 8, 10])
    [(5, 50), (16, 24), (16, 72), (24, 72)]
    >>> mekong([5, 12, 18, 21],[3, 8])
    [(12, 18), (12, 21), (18, 21)]
    >>> mekong([5, 14, 21, 70],[7, 5, 100])
    [(5, 70), (14, 21), (14, 70), (21, 70)]
    >>> mekong([5, 6, 12, 18, 21],[11])
    []
    >>> mekong([20,30,33],[10])
    [(20, 30)]
    '''
    
    # YOUR CODE GOES HERE
    LoT = []
    tup = ()
    temp = []
    for j in div:
        temp = []
        for i in range(len(L)):
            if L[i]%j == 0:
                temp.append(L[i])
        if len(temp)>1:
            for k in range(len(temp)-1):
                for l in range(1,len(temp)):
                    if k+l < len(temp):
                        tup = (temp[k], temp[k+l])
                        if tup not in LoT:
                            LoT.append(tup)
    LoT.sort()
        
    return LoT

#########################
# QUESTION2
#########################

def decode(secret_message, encoding):
    '''(str, dict of {str: str}) -> str

    Two friends like to send messages to each other, but they don't want
    anyone else to read the messages. To keep the messages private, they change
    each letter in the original message to a different letter of the alphabet.
    For every letter, they both know which letter will be substituted for it,
    which is called an encoding. The message will contain only lowercase letters.
    It will not contain whitespace, digits, or punctuation.

    Return a decoded version of secret_message using the mapping of secret
    character to actual character given in the dictionary encoding.
    Precondition: every character in secret_message is a key in dictionary encoding
    
    >>> encoding = {'x': 'a', 't': 'n', 'n': 'e', 'e': 's', 'r': 'w', 'i': 'r'}
    >>> decode('xternie', encoding)
    'answers'
    >>> decode('abba', {'a': 'o', 'b': 't'})
    'otto'
    '''
    
    # YOUR CODE GOES HERE

    answer = ''
    temp = []

    for i in range(len(secret_message)):
        temp.append(secret_message[i])

    for j in range(len(temp)):
        temp[j] = encoding[temp[j]]

    for k in temp:
        answer += k

    return answer

#########################
# QUESTION 3
#########################
def split_guesses(guess_list, answer):
    '''(list of number, number) -> dict of {str: list of number}
    Return a new dictionary where each item in guess_list appears as an item
    in one of the dictionary's values lists:
    - each item less than answer is in a list associated with key 'low',
    - each item equal to answer is in a list associated with key 'correct', and
    - each item greater than answer is in a list associated with key 'high'.
    If there are no items associated with one of 'low', 'correct', or 'high',
    then that string should not appear as a key in the dictionary.

    >>> split_guesses([], 5.5)
    {}
    >>> split_guesses([4.7, 1], 6.0)
    {'low': [4.7, 1]}
    >>> split_guesses([4.7, 1, 7.2, 5, 7.2, 8], 6.0)
    {'low': [4.7, 1, 5], 'high': [7.2, 7.2, 8]}
    >>> split_guesses([1.3, 1.2, 1.4, 1.4, 1.5, 1.6, 1.5, 1.6], 1.4)
    {'correct': [1.4, 1.4], 'low': [1.3, 1.2], 'high': [1.5, 1.6, 1.5, 1.6]}
    '''

    # YOUR CODE GOES HERE

    low = []
    high = []
    correct = []
    sets = {}
    dit = {}

    for i in range(len(guess_list)):
        if guess_list[i] < answer:
            low.append(guess_list[i])
        elif guess_list[i] > answer:
            high.append(guess_list[i])
        else:
            correct.append(guess_list[i])

    sets = {'correct': correct}
    dit.update(sets)
    
    sets = {'low': low}
    dit.update(sets)

    sets = {'high': high}
    dit.update(sets)



    return dit
    


#########################
# QUESTION 4
#########################

class Route:
    # YOUR CODE GOES HERE
    
    def __init__(self, start = 'start', end = 'end', num = 0):
        '''
        str, str, int -> none
        initializes route class object
        '''

        self.start = start
        self.end = end
        self.num = num

    def modify_num_daily_flights_by(self, change):
        '''
        int -> none
        adds change to number of flights
        '''
        self.num+= change

    def __repr__(self):
        '''
        none -> str
        returns formal representaion of Route object
        '''

        return "Route('"+self.start+"', '"+self.end+"', "+str(self.num)+")"

class Airline:
    # YOUR CODE GOES HERE

    def __init__(self, name = 'AirlineName', routes = []):
        '''
        str, list -> none
        initializes class Airline object
        '''
        self.name = name
        self.routes = routes

    def multiple_flights(self):
        '''
        none -> list
        Returns all routes in set airline with number of flights >1
        '''
        new = []
        for i in self.routes:
            if i.num > 1:
                new.append(i)
        return new

