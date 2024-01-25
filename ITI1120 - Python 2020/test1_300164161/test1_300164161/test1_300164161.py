# Course: 1120
# Test 1
# Dobie, Mikaela
# 300164161


###################################################
# Question 1
###################################################

def atlantic(sn):
    '''
    (string) -> string

    Preconditions: String input must be in quotations
    
    Returns 'new' if sn >= 9 characters in length, else returns 'old'
    '''

    length = len(sn)

    if length < 9:
        return 'old'
    else:
        return 'new'


###################################################
# Question 2
###################################################

def southern(n):
    '''
    (int) -> nothing
    
    Preconditions: n = 1 or 2
    
    If n = 1, function asks for a weight in pounds and ounces and then prints the weight in kilograms
    If n = 2, function asks for name and students number and prints a statement on whether the number is new or old
    '''

    if n==1:
        pounds = float(input("Enter a number of the weight in pounds: "))
        ounces = float(input("Enter a number of the weight in ounces: "))
        kilograms = (16*pounds + ounces)*0.02835
        print(pounds, "pounds and", ounces, "is (approximately)", kilograms,"kilograms.")
    else:
        name = input("What is your name? ")
        number = input("What is your student number? ")
        age = atlantic(number)
        print(name, "you have a", age, "student number.")


###################################################
# Question 3
###################################################

def pacific(g1,g2,g3):
    '''
    (number, number, number) -> boolean
    
    Preconditions: numbers will be between 0 and 100 inclusively
    
    Function takes three grades and returns true if all three are passsing ans at least one is an A, else returns false
    '''

    return (g1>=50 and g2>=50 and g3>= 50 and (g1>=80 or g2>=80 or g3>=80))


###################################################
# Question 4
###################################################

def arctic(n):
    '''
    (int) -> boolean
    
    Preconditions: number must be a 4 or 6 digit integer

    Function takes a number and determines whether it is palindromic
    '''

    if n <10000:
        a = n//1000
        b = (n%1000)//100
        c = (n%100)//10
        d = n%10

        if a==d and b==c:
            return True
        else:
            return False
    else:
        a = n//100000
        b = (n%100000)//10000
        c = (n%10000)//1000
        d = (n%1000)//100
        e = (n%100)//10
        f = n%10

        if a==f and b==e and c==d:
            return True
        else:
            return False








