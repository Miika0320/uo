#test
'''
def create_network(file_name):
    
    friends = open(file_name).read().splitlines()
    network=[]

    # YOUR CODE GOES HERE
    user0 = ()
    a = [0]
    line = 1
    for i in range(int(friends[0])):
        user1 = []
        count = i        
        while(i == count):
            if(line+1) >= len(friends):
                count+=1
            else:
                a = friends[line].split()
                for k in range(len(a)):
                    a[k] = int(a[k])
                user1.append(a[1]) 
                nextline = int(friends[line+1].split()[0])
                print("next:",nextline)
                if nextline > count:
                    count+=1
                line+=1
        user0 = (i,user1)
        network.append(user0)
    return network
'''
def create_network(file_name):
   
    friends = open(file_name).read().splitlines()
    network=[]

    # YOUR CODE GOES HERE
    user0 = ()
    user1 = []
    user2 = []
    for i in range(int(friends[0])):
        network.append([i])
    for j in range(1, len(friends)):
        a = friends[j].split()
        for k in range(len(a)):
            a[k] = int(a[k])
        network[a[0]].append(a[1])
        network[a[1]].append(a[0])
    for i in range(int(friends[0])):
        network[i] = (i,network[i][1:])
    
    return network

def getCommonFriends(user1, user2, network):
    '''(int, int, 2D list) ->list
    Precondition: user1 and user2 IDs in the network. 2D list sorted by the IDs, 
    and friends of user 1 and user 2 sorted 
    Given a 2D-list for friendship network, returns the sorted list of common friends of user1 and user2
    '''
    common=[]
    # YOUR CODE GOES HERE


    for i in network[user1][1]:
        if i in network[user2][1] and i != user1:
            common.append(i)

    return common

    
def recommend(user, network):
    '''(int, 2Dlist)->int or None
    Given a 2D-list for friendship network, returns None if there is no other person
    who has at least one neighbour in common with the given user and who the user does
    not know already.
    
    Otherwise it returns the ID of the recommended friend. A recommended friend is a person
    you are not already friends with and with whom you have the most friends in common in the whole network.
    If there is more than one person with whom you have the maximum number of friends in common
    return the one with the smallest ID. '''

    # YOUR CODE GOES HERE
    i = 0
    flag = False
    rec = None
    while i <= len(network)-1 or flag == True:
        if i != user:
            common = getCommonFriends(i, user, network)
        if len(common) != 0:
            for k in network[i][1]:
                if k != user and k not in common:
                    rec = k
                    flag = True
        i+=1
    return rec
