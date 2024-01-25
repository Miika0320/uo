# Course: 1120
# Assignment 4 Part 2 Q1
# Dobie, Mikaela
# 300164161


def number_divisible(l, n):
    '''
    list of int, int -> int

    returns number of element in list l that are divisible by n
    '''
    count = 0
    for i in l:
        if i%n == 0:
            count +=1
    return count


l1 = input("Please enter a list of integers separated by spaces: ").strip().split()
l2 = []
for i in l1:
    l2.append(int(i))
n = int(input("Please input a non negative integer: "))

output = number_divisible(l2,n)

print("The number of elements divisible by",n, "is", output)
