import hashlib
from random import randint
from sympy import randprime, mod_inverse, primitive_root
from cryptography.hazmat.primitives.asymmetric import dsa

# Parameters for DSA (1024-bit prime p, 160-bit prime q, and generator g)

'''
	TO sign msg m: 
		Alice chooses k <- Zq* (value is unique for this signature only)
		Alice computes: r = (g^k mod P) mod q
		Alice computes: s = [(H(m) + xr)*k^(-1)] mod q
		(k^-1) multiplicative inverse in reference to mod q
		The signature on m is the tuple (r,s) 
		
		To verify:
		Bob computes: u1 = [H(m)*s^(-1)] mod q
		Bob computes: u2 = [r*s^(-1)] mod q
		Output is valid iff [(g^(u1)*g*(u2)) mod p] mod q == r


'''
def randomPrime(n):
    # Generate a n-bit prime number
    lower_bound = 2**(n-1)
    upper_bound = 2**n - 1
    prime = randprime(lower_bound, upper_bound)
    
    
    return prime
DSAparam = dsa.generate_private_key(key_size=1024).parameters().parameter_numbers()
p = DSAparam.p
q = DSAparam.q

# Find a generator for the prime p
g = DSAparam.g


# Step 1: Generate keys
x = randint(1, q - 1)  # Private key
y = pow(g, x, p)  # Public key

# Step 2: Message to sign
m = 582346829057612
message = str(m).encode()


# Step 3: Choose a random k
k = randint(1, q - 1)


# Step 4: Calculate r and s for the signature
r = pow(g, k, p) % q
k_inv = mod_inverse(k, q)
h_m = int(hashlib.sha1(message).hexdigest(), 16)
s = (k_inv * (h_m + x * r)) % q

# Step 5: Verify the signature
w = mod_inverse(s, q)
u1 = (h_m * w) % q
u2 = (r * w) % q
v = ((pow(g, u1, p) * pow(y, u2, p)) % p) % q

# Results

print("m: ", m)
print("k: ", k)
print("r: ",r)
print("s: ",s)
print("calculated r from signature: ",v)
print("calculated r = r:", v == r)
