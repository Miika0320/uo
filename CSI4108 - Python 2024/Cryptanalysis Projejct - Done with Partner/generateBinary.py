import round_key as key
import s_box as sb
import random

def getDeltas(DDT, threshold):


    # Loop through each row and column to find high-probability ΔX values
    high_probability_deltas = []
    for delta_x, row in enumerate(DDT):
        for delta_y, count in enumerate(row):
            if count >= threshold:
                high_probability_deltas.append((delta_x, delta_y, count))

   
    return high_probability_deltas


def encryptFromList(num, round_keys, s_box):
    binary_data = format(num, 'b')
    # Performing 5 rounds of encryption
    for i in range(len(round_keys)-1):
        # XORing each 16 bits with the round key
        
        binary_data = f"{int(binary_data, 2) ^ int(round_keys[i], 2):016b}"
        # Performing s-box substitution
        
        w, x, y, z = s_box[int(binary_data[0:4],2)], s_box[int(binary_data[4:8],2)], s_box[int(binary_data[8:12],2)], s_box[int(binary_data[12:16],2)]
        binary_data = f"{w:04b}{x:04b}{y:04b}{z:04b}"
        #permutations
        f = binary_data
        a = f[0]+f[4]+f[8]+f[12]
        b = f[1]+f[5]+f[9]+f[13]
        c = f[2]+f[6]+f[10]+f[14]
        d = f[3]+f[7]+f[11]+f[15]
        binary_data = a+b+c+d
    f"{int(binary_data, 2) ^ int(round_keys[-1], 2):016b}"
    return int(binary_data,2)

def getPTCTDeltaX(delta_X, delta_Y):
    num_plaintexts = 10000
    plaintexts = []
    for _ in range(num_plaintexts):
        P = random.randint(0, 0xFFFF)
        P_prime = P ^ delta_X
        plaintexts.append((P, P_prime))
    with open('C:/Users/mikae/Desktop/GitHub/CSI-4108-Cryptanalysis/mikaela/randomPlaintextspaired.txt', 'w', encoding='utf-8') as file:
            for text, pair in plaintexts:
                file.write(f"{text}\n{pair}\n")
    
    ciphertexts = [(encryptFromList(P, key.get_round_keys(1234), sb.get_s_box(4108)), encryptFromList(P_prime, key.get_round_keys(1234), sb.get_s_box(4108))) for P, P_prime in plaintexts]
    with open('C:/Users/mikae/Desktop/GitHub/CSI-4108-Cryptanalysis/mikaela/randomCiphertextspaired.txt', 'w', encoding='utf-8') as file:
            for text, pair in ciphertexts:
                file.write(f"{text}\n{pair}\n")
    
    matching_pairs = []

    # Filter pairs based on expected delta_Y in ciphertext
    for C, C_prime in ciphertexts:
        if (C ^ C_prime) == delta_Y:
            matching_pairs.append((C, C_prime))
    return matching_pairs

def keyByteGuess(matchingPairs, delta_X):
    # Dictionary to store frequency of key byte matches
    key_byte_counts = [0] * 256  # Assume key byte is 8-bit

    # Test each possible value for one byte of the final round key
    for key_byte_guess in range(256):
        match_count = 0

        for C, C_prime in matchingPairs:
            # Reverse last round using the guessed key byte
            partial_decrypted_C = C ^ key_byte_guess
            partial_decrypted_C_prime = C_prime ^ key_byte_guess

            # Check if the decrypted difference matches expected ΔX after partial decryption
            if (partial_decrypted_C ^ partial_decrypted_C_prime) == delta_X:
                match_count += 1

        # Record the number of matches for this key byte guess
        key_byte_counts[key_byte_guess] = match_count

    # Best guess for the key byte based on highest match count
    best_key_byte = key_byte_counts.index(max(key_byte_counts))
    print(f"Best guess for key byte when deltaX = {delta_X}:", hex(best_key_byte))




#Step 1: get delta x, delta y matches

#create a DDT table

DDT = [
    [16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],  # Row for ΔX = 0
    [0, 0, 2, 2, 0, 2, 2, 0, 0, 2, 2, 0, 0, 0, 2, 2],    # Row for ΔX = 1
    [0, 0, 0, 4, 0, 2, 0, 2, 0, 0, 0, 4, 0, 2, 0, 2],    # Row for ΔX = 2
    [0, 0, 0, 0, 0, 4, 0, 0, 2, 0, 2, 0, 2, 4, 2, 0],
    [0, 0, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2],
    [0, 0, 0, 0, 2, 0, 2, 0, 0, 2, 0, 2, 4, 4, 0, 0],
    [0, 6, 0, 2, 2, 0, 2, 0, 4, 0, 0, 0, 0, 0, 0, 0],
    [0, 2, 2, 0, 0, 0, 2, 2, 2, 0, 0, 2, 0, 0, 2, 2],
    [0, 2, 2, 0, 2, 0, 0, 2, 0, 0, 2, 2, 0, 0, 2, 2],
    [0, 6, 0, 2, 4, 0, 0, 0, 2, 0, 2, 0, 0, 0, 0, 0],
    [0, 0, 0, 0, 4, 0, 0, 0, 0, 4, 0, 0, 2, 2, 2, 2],
    [0, 0, 0, 0, 2, 0, 2, 4, 0, 2, 4, 2, 0, 0, 0, 0],
    [0, 0, 0, 0, 0, 2, 0, 2, 4, 0, 0, 0, 4, 2, 0, 2],
    [0, 0, 4, 0, 0, 0, 4, 0, 2, 0, 2, 0, 2, 0, 2, 0],
    [0, 0, 2, 2, 0, 0, 2, 2, 0, 0, 2, 2, 0, 0, 2, 2],
    [0, 0, 0, 0, 0, 6, 0, 2, 0, 6, 0, 2, 0, 0, 0, 0]
]

threshold = 6 # even >4

high_probability_deltas = getDeltas(DDT, threshold)
deltaXs = []
deltaYs = []

for deltaX, deltaY, count in high_probability_deltas:
    deltaXs.append(deltaX)
    deltaYs.append(deltaY)

# in this specific case they are all six for count so I am not favouring one over another in terms of which delta to use

# for 5000 randomly generated plaintexts, get the plaintext pairs where p1 xor p2 = delta x
# encrypt each pair
# get the list of pairs of ciphertexts where c1 xor c2 = delta y
# for each matched pair find the key of all possible keys that satisfy k xor c1 xor k xor c2 = delta x
# print the index that satifies this the most


matches = []
for i in range(1,len(deltaXs)):
    #mostProbable.append(getKey(deltaXs[1], deltaYs[1], bytePT, bytePT, inverse_S_box))
    matches = (getPTCTDeltaX(deltaXs[i], deltaYs[i]))
    print(matches)
    keyByteGuess(matches, deltaXs[i])
