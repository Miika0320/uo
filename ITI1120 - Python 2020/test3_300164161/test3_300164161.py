'''
I understand the importance of professional integrity in my education and future career
in engineering or computer science. I hereby certify that I have done and will do all work
on this examination entirely by myself, without outside assistance or the use of unauthorized
information sources. Furthermore, I will not provide assistance to others.
'''

# READ THE ABOVE STATEMENT
#
# PUT YOUR NAME AND STUDENT NUMBER HERE
# Mikaela Dobie 300164161
# By putting your name here you are signing the statement above and
# agreeing to the TEST 3 (integrity rules) listed on the first page of the test


#########################
# QUESTION 1:
#########################
# QUESTION 1A)
#########################
# Imagine this program is running and it is about to execute
# the line: print("bye dragonfly").
# Which of the follow is true about variables x, y and z at that moment
# of the program execution?
# A) all three variables x, y and z refer to a same object 
# B) x and y refer to a same object and z to a different object
# C) x and z refer to a same object and y to a different object
# D) y and z refer to a same object and x to a different object
# E) the 3 variables refer to three distinct objects

# WRITE A, B, C, D or E for your answer in the comment below this line:
# E
#

#########################
# QUESTION 1B)
#########################
# Imagine this program is running and it is about to execute
# the line: print("bye dragonfly").
# Which of the follow is true about variables a and b at that moment
# of the program execution?
# A) both a and b refer to a same object 
# B) a and b refer to two distinct objects
#
# WRITE A or B for your answer in the comment below this line:
#
# B

#########################
# QUESTION 2:
#########################
# QUESTION 2A)
#########################
# What is the running-time (i.e. a rough number of operations) of
# function mantis on a list with n elements
# A) O(1)
# B) O(log n)
# C) O(n)
# D) O(n*log n)
# E) O(n^2)

# WRITE A, B, C, D or E for your answer in the comment below this line:
#
# E

#########################
# QUESTION 2B)
#########################

# Imagine you replaced n in the second range function with number 10.
# What is the running-time of function mantis with that change?
# A) O(1)
# B) O(log n)
# C) O(n)
# D) O(n*log n)
# E) O(n^2)

# WRITE A, B, C, D or E for your answer in the comment below this line:
#
# D

#########################
# QUESTION 3
#########################
def diff_start_end(l, q):
    '''(list of int, list of int) -> (int, int)

    l and q are two parallel lists i.e. len(l)==len(q)
    The function returns the following two integers (i.e. a tuple with
    the following two integers):
      - the index where l and q first differ, and
      - the index where l and q last differ. This index should be NEGATIVE

    Preconditions: len(l)==len(q)>=1, and l != q
    That is, you may assume that lists l and q are not the same,
    meaning that there is at least one position i such that
    l[i] and q[i] are not the same.

    >>> diff_start_end([0,1,6,0,7,2,3,4], [0,1,8,0,9,2,3,4])
    (2, -4)
    >>> diff_start_end([6],[8])
    (0, -1)
    >>> diff_start_end([0,0,0,1,0],[0,0,0,22,0])
    (3, -2)
    >>> diff_start_end([0,1,1],[1,1,1])
    (0, -3)
    >>> diff_start_end([0,1,1,0],[0,0,0,0])
    (1, -2)
    '''

    #YOUR CODE GOES HERE
    temp = []
    final = ()
    for i in range(len(l)):
        if l[i]!= q[i]:
            temp.append(i)
    if len(temp) != 1:
        final = (temp[0], -(temp[-1]))
    else:
        final = (temp[0], -(len(q)-temp[0]))

    return final



#########################
# QUESTION 4
#########################
def make_teams(players, num_teams):
    '''(list of str, int)->2D list 
    Make num_teams teams out of the players in list players by counting off.
    Players is a list of players' names and num_teams is the desired number of teams
    Return a 2D list where each sublist is representing a team.
    Preconditions: num_teams>= 1

    >>> make_teams(["pele", "maradona", "serena", "venus", "fed", "rafa", "lionel"], 3)
    [['pele', 'venus', 'lionel'], ['maradona', 'fed'], ['serena', 'rafa']]
    >>> make_teams(["pele", "maradona", "serena", "venus", "fed", "rafa", "lionel"], 2)
    [['pele', 'serena', 'fed', 'lionel'], ['maradona', 'venus', 'rafa']]
    >>> make_teams(["1", "2", "3", "4", "5", "6", "7", "8", "9"], 3)
    [['1', '4', '7'], ['2', '5', '8'], ['3', '6', '9']]
    >>> make_teams(["1", "2", "3", "4", "5", "6", "7", "8", "9"], 1)
    [['1', '2', '3', '4', '5', '6', '7', '8', '9']]
    >>> make_teams(["1", "2", "3", "4", "5", "6", "7", "8", "9"], 4)
    [['1', '5', '9'], ['2', '6'], ['3', '7'], ['4', '8']]
    >>> make_teams(["1", "2", "3", "4", "5", "6", "7", "8", "9"], 7)
    [['1', '8'], ['2', '9'], ['3'], ['4'], ['5'], ['6'], ['7']]
    >>> make_teams(["1", "2", "3", "4", "5", "6", "7", "8", "9"], 11)
    [['1'], ['2'], ['3'], ['4'], ['5'], ['6'], ['7'], ['8'], ['9'], [], []]
    >>> make_teams( [] , 3)
    [[], [], []]
    '''

    #YOUR CODE GOES HERE
    teams = []
    count = 0
    
    if len(players) == 0:
        for j in range(num_teams):
            teams.append([j])
        for k in range(len(teams)):
            teams[k].remove(teams[k][0])
    else:
        for i in range(num_teams):
            teams.append([i])
        while len(players)!= 0:
            if count == (num_teams):
                count = 0
            teams[count].append(players[0])
            players.remove(players[0])
            count+=1
        for k in range(len(teams)):
            teams[k].remove(teams[k][0])
    return teams
       




#########################
# QUESTION 5
#########################
def draw_w_stars(n):
    '''(int)->None
    Preconditions: n is positive odd integer
    Draws a figure as depicted in Question 5 with
    n stars in the top and the bottom raws
    '''

    #YOUR CODE GOES HERE
    m = 0
    l=n
    for i in range(l//2+1):
            
        print(m*' '+n*'*'+m*' ')
        n-=2
        m+=1
    n+=2
    m-=1
    for i in range(l//2):
        n+=2
        m-=1
        print(m*' '+n*'*'+m*' ')
        

        

