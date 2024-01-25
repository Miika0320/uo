# Course: 1120
# Assignment 3 Part 2
# Dobie, Mikaela
# 300164161

#####################################################
# Part 2 Question 1
#####################################################

def sum_odd_divisors(n):
    '''
    int -> int or nothing if n=0

    Preconditions: n is an integer not a float
    
    Returns sum of all positive odd divisors of inputted number
    '''
    add = 0
    if n == 0:
        return
    else:
        for i in range(1, abs(n)+1,2):
            if n%i == 0:
                add = add+i
    return add
                
#####################################################
# Part 2 Question 2
#####################################################

def series_sum():
    '''
    nothing -> float or nothing if user input < 0

    returns sum of series of: 1000 + 1/1^2 ....1/n^2 where n is the user input
    '''
    counter = 1000
    num = int(input("Please enter a non-negative integer: "))

    if num < 0:
        return
    elif num > 0:
        for i in range(1,num+1):
            counter = counter + (1/((i)**2))
            
    return counter

#####################################################
# Part 2 Question 3
#####################################################

def pell(n):
    '''
    int -> int

    Preconditions: n>= 0 or else returns none

    Returns pell number n, where Pn = 2*P(n-1) + P(n-2) with the exceptions of P0 = 0 and P1 = 1
    '''

    t1 = 0
    t2 = 1
    temp = 0

    for i in range(n-1):
        temp = t2
        t2 = 2*t2 + t1
        t1 = temp
        
    if n == 0:
        return 0
    else:
        return t2


#####################################################
# Part 2 Question 4
#####################################################
def countMembers(s):
    '''
    str -> int
    
    preconditions: to count backslash, only a space or period may follow it
    
    returns number of times each character in str s exists in (efghijFGHIJKLMNOPQRSTUVWX23456\!,)
    '''
    
    counter = 0
    for i in range(len(s)):
        if s[i] in 'efghijFGHIJKLMNOPQRSTUVWX23456\!,' :
            counter = counter + 1
            
    return counter

#####################################################
# Part 2 Question 5
#####################################################

def casual_number(s):
    '''
    str -> int or none when string is not a number

    Preconditions: str must be in quotations

    returns number in int form ready for calculations, given number in anyform of string
    '''
    s = s.replace(",", "")

    
    if s.isnumeric():
        return int(s)
    elif s[0] == "-":
        if s[1:].isnumeric():
            return int(s)
    else:
        return
        
#####################################################
# Part 2 Question 6
#####################################################

def  alienNumbers(s):
    '''
    string -> int

    Preconditions: str must be in quotations

    returns int value of characters where ['T','y','!','a','N','U'] = [1024,598,121,42,6,1] respectively
    '''
    return (s.count('T')*1024)+(s.count('y')*598)+(s.count('!')*121)+(s.count('a')*42)+(s.count('N')*6)+(s.count('U')*1)

#####################################################
# Part 2 Question 7
#####################################################

def alienNumbersAgain(s):
    '''
    string -> int

    Preconditions: str must be in quotations

    returns int value of characters where ['T','y','!','a','N','U'] = [1024,598,121,42,6,1] respectively
    '''
    count = 0
    for i in range(len(s)):
        if s[i] == 'T':
            count = count + 1024
        elif s[i] == 'y':
            count = count + 598
        elif s[i] == '!':
            count = count + 121
        elif s[i] == 'a':
            count = count + 42
        elif s[i] == 'N':
            count = count + 6
        elif s[i] == 'U':
            count = count + 1
    return count

#####################################################
# Part 2 Question 8
#####################################################

def encrypt(s):
    '''
    string -> string

    Preconditions: str must be in quotations

    returns encrypted version of string given; reversed and pairs last character with first, second with second last, etc.
    '''
        
    m = 0
    string=''
    string1=''
    for i in range(len(s)-1,-1,-1):
        string = string + s[i]

    for j in range(len(s)//2):
        string1 = string1 + string[j]+string[-(j+1)]
        
    if len(s)%2 == 0:
        return string1
    else:
        return string1+s[len(s)//2]

#####################################################
# Part 2 Question 9
#####################################################

def weaveop(s):
    '''
    str -> str

    Preconditions: str must be in quotations

    returns given string with op in between every pair of alpha characters,
    where the capitalization of o is the same as the first char in the pair and
    where the capitalization of p is the same as the second char in the pair 
    '''
    s1 = s
    m = 0
    for i in range(len(s)-1):
        
        if (s[i] + s[i+1]).isalpha() == True:
            if s.upper()[i] == s[i]:
                s1 = s1[0:i+1+m]+"O"+s1[i+1+m:len(s1)]

            else:
                s1 = s1[0:i+1+m]+"o"+s1[i+1+m:len(s1)]
                
            if s.upper()[i+1] == s[i+1]:
                s1 = s1[0:i+2+m]+"P"+s1[i+2+m:len(s1)]

            else:
                s1 = s1[0:i+2+m]+"p"+s1[i+2+m:len(s1)]

            m=m+2
    return s1

#####################################################
# Part 2 Question 10
#####################################################

def squarefree(s):
    '''
    str -> bool

    Preconditions: str must be in quotations
    
    returns true if no substring in string s repeats itself twice in a row
    '''
    
    count = 0
    for i in range(len(s)): 
        for j in range(len(s)):
            for m in range(1,len(s)):
                if s[i:j+i] == s[j+i:j+i+m]:
                    count = count+1
            

    return count == 0
