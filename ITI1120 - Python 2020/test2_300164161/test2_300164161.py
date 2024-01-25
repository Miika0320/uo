'''
I understand the importance of professional integrity in my education and future career
in engineering or computer science. I hereby certify that I have done and will do all work
on this examination entirely by myself, without outside assistance or the use of unauthorized
information sources. Furthermore, I will not provide assistance to others.
'''

# READ THE ABOVE STATEMENT
#
# PUT YOUR NAME AND STUDENT NUMBER HERE

# Mikaela Dobie
# 300164161

# By putting your name here you are signing the statement above and
# agreeing to the TEST 2 (integrity rules) listed on the first page of the test


def list_work(l): # 10 points
     '''(list of int)->list of int

     The function returns a new list where for every pair of consecutive elements in l
     if both are odd or both are even then the sum of the two elements is added to the new list
     otherwise zero is added to the new list. For example, in a list l=[1, 7, 5, 4, 12],
     1 and 7 are both odd, so we add their sum, 8, to the new list
     7 and 5 are both odd, so we add their sum, 12, to the list
     5 and 4 are neither both odd nor both even, so we add zero to the list
     4 and 12 are both even, so we add their sum, 16,  to the list.
     Thus the function returns the list [8, 12, 0, 16]
     
     If l has at most 1 element, then a copy of list l is returned

     >>> list_work([1, 7, 5, 4, 12, -33, 6])
     [8, 12, 0, 16, 0, 0]
     >>> list_work([1])
     [1]
     >>> list_work([])
     []
     >>> list_work([1,2,3,4,5])
     [0, 0, 0, 0]
     >>> list_work([100,20,30])
     [120, 50]
     '''

     
     # YOUR CODE GOES HERE

     new = []
     if len(l) == 0:
          return l
     elif len(l) == 1:
          return l
     else:
          
          for i in range(len(l)-1):
               if l[i].isdigit():
                    
                    if l[i] % 2 == 0:
                         if l[i+1] % 2 == 0:
                              new.append(l[i]+l[i+1])
                         elif l[i+1] % 2 != 0:
                              new.append(0)
                    else:
                         if l[i+1] % 2 != 0:
                              new.append(l[i]+l[i+1])
                         elif l[i+1] % 2 == 0:
                              new.append(0)
          return new

def pattern_to_stars(s, p): # 10 points
     '''(str, str)->str

     Precondition: p is not an empty string and all letters are lower-case

     Given a string s and another string p the function returns a new string where
     the first occurrence of p in s is replaced with one star, the second occurrence with two stars
     and so on. 
     
     You may assume that no two patterns p in s overlap in s. Example:
     p="aa" s="aaab" cannot happen since "aa" pattern in the first two positions
     overlaps with "aa" pattern in the next two positions in s

     You CANNOT use the replace method from str module.
     Using that method will result in receiving zero for this function.

     Hints:
     - slicing may be helpful in this question
     - it may be easier to solve this problem with a while loop
     
     >>> pattern_to_stars("trabsabtt", "ab")
     'tr*s**tt'
     >>> pattern_to_stars("today123is123nice123d", '123')
     'today*is**nice***d'
     >>> pattern_to_stars("1a2b3", '123')
     '1a2b3'
     >>> pattern_to_stars("chipchip", 'chip')
     '***'
     >>> pattern_to_stars('', 'chip')
     ''
     '''


     # YOUR CODE GOES HERE


     a = ''
     b = s
     count = 1
     flag = 0

     while flag == 0:
          if p in b:
               a = b[:b.find(p)]+count*'*'+b[b.find(p)+len(p):]
               b=a
               count+=1
          else:
               flag = 1
     return a
     
def get_list_ofint(s): # 5 points
     '''(str)->list of int
     Precondition: s string that looks like a sequence in integers where
     every pair of consecutive integers is separated by a comma followed by a space or a space

     More preconditions: s has at least one substring that looks like an integer
     
     Returns a list of integers from s
     
     >>> get_list_ofint("10 22, 7 0 -5, 1")
     [10, 22, 7, 0, -5, 1]
     >>> get_list_ofint("231, -5, 12")
     [231, -5, 12]
     >>> get_list_ofint("231 -5 -7")
     [231, -5, -7]
     >>> get_list_ofint("-7,")
     [-7]
     '''

     
     # YOUR CODE GOES HERE

     a = []
     for i in s.split(','):
        
          for j in i.split(' '):
               j.strip()
               if(j):
                    a.append(j)
     return a

# main
print("Enter A or B to choose one of the following two options:\n")        
print("A: Would work with numbers?")
print("B: Would like to replace a pattern with stars?\n") 

answer = input("Enter A or B (lower or upper case accepted): ")


# 5 points
# You should code here a part where your program keeps on asking
# the user for "A" or "B" until the user finally enters it. Your solution
# should accept lower case and upper case A or B and it should
# should remove any extra white space. See test2_Runs.txt for example runs


# YOUR CODE GOES HERE:
flag = 1
if answer.strip().lower() not in 'ab':
     flag = 0

while flag == 0:
     print("Invalid entry")
     answer = input("Enter A or B (lower or upper case accepted): ")
     if answer.strip().lower() not in 'ab':
          flag = 0
     else:
          flag = 1
if answer=="a":
     rawl=input("Enter a sequence of integers separated by either one space or a comma then one space.\n")
     print("You entered:", rawl)
     givenl=get_list_ofint(rawl)
     print("The given list of ints is", givenl)
     print("The list produced after prescribed work is", list_work(givenl))
     
else:
     s=input("Enter a string: ").lower()
     p=input("Enter a non-empty pattern string: ").lower()
     print("Here is a result of replacing pattern", p, "in", s, "with some stars:")
     print(pattern_to_stars(s,p))
