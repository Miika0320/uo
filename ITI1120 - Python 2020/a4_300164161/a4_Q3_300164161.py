# Course: 1120
# Assignment 4 Part 2 Q3
# Dobie, Mikaela
# 300164161

def longest_run(l):
    '''
    list of numbers -> int
    returns length of longest run of the same value of numbers
    '''

    count = 1
    temp = 0
    for i in range(len(l)-1):
        if l[i] == l[i+1]:
            count+=1
        else:
            count = 1
        if count > temp:
            temp = count
    return temp


l1 = input("Please enter a list of numbers separated by spaces: ").strip().split()
l2 = []
for i in l1:
    l2.append(float(i))

print(longest_run(l2))
