# Course: 1120
# Assignment 4 Q2
# Dobie, Mikaela
# 300164161


def two_length_run(l):
    '''
    list of numbers -> boolean

    returns true if there is a least one occurance of
    a run of numbers at least 2 numbers long, else returns false
    ''' 
    count = 1
    i = 0
    flag = True
    while flag == True:
        if l[i] == l[i+1]:
            count+=1
        else:
            count = 1
        i+=1
        if count == 2 or i == len(l)-1:
            flag = False
    return count == 2

l1 = input("Please enter a list of numbers separated by spaces: ").strip().split()
l2 = []
for i in l1:
    l2.append(float(i))

print(two_length_run(l2))
