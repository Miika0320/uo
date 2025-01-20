# Function to compute modular exponentiation
def mod_exp(base, exp, mod):
    return base ** exp % mod

# Function to compute the modular inverse using Extended Euclidean Algorithm
def mod_inverse(a, mod):
    
    return pow(a, -1, mod)

# Key Generation
def key_generation(q, alpha):
    x = 17  # private key (randomly selected)
    y = mod_exp(alpha, x, q)  # public key
    return x, y

# Encryption
def encrypt(m, q, alpha, y, k):
    c1 = mod_exp(alpha, k, q)
    c2 = (m * mod_exp(y, k, q)) % q
    return c1, c2

# Decryption
def decrypt(c1, c2, x, q):
    s = mod_exp(c1, x, q)
    s_inv = mod_inverse(s, q)
    m = (c2 * s_inv) % q
    return m

# Compute m2 given m1
def compute_m2(m1, c2, c22, q):
    c2_inv = mod_inverse(c2, q)  # (c1)^-1 mod q
    print(c2_inv)
    m2 = (m1*c22 * c2_inv) % q
    return m2

# Parameters
q = 89
alpha = 13
k = 41
m1 = 72  # known message
m2 = 16

# Generate keys
x, y = key_generation(q, alpha)

# Encrypt m1
c1, c2 = encrypt(m1, q, alpha, y, k)

c21, c22 = encrypt(m2, q, alpha, y, k)
# Decrypt the ciphertext
decrypted_m1 = decrypt(c1, c2, x, q)
decrypted_m2 = decrypt(c21, c22, x, q)

# Compute m2 from m1, c1, c22
m2f = compute_m2(m1, c2, c22, q)

# Results
print(f"Original message m1: {m1}")
print(f"Encrypted ciphertext (c1, c2): ({c1}, {c2})")
print(f"Original message m2: {m2}")
print(f"Encrypted ciphertext (c1, c2): ({c21}, {c22})")
print(f"Decrypted message1: {decrypted_m1}")
print(f"Decrypted message2: {decrypted_m2}")
print(f"Computed m2: {m2f}")
