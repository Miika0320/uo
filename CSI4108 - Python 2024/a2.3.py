from sympy import isprime
import random
from math import gcd

def find_primes_between(start, end):
    """Find all prime numbers between the given start and end (inclusive). amd for the sake of run time, all that are congruent to 3 mod 4"""
    primes = [num for num in range(start, end + 1) if isprime(num) and num%4 ==3]
    return primes

def generate_seed(n):
    """Generate a seed x_0 such that gcd(x_0, n) = 1."""
    while True:
        seed = random.randint(1, n - 1)
        if gcd(seed, n) == 1:
            return seed
        
def bbs_generate_bits(n, seed, num_bits=15):
    """Generate the first 'num_bits' bits using the Blum Blum Shub generator."""
    
    # Initialize the sequence with the given seed (x_0)
    x = (seed ** 2) % n  # First iteration

    #Generate the sequence and extract bits
    bits = []
    for _ in range(num_bits):
        x = (x ** 2) % n  # Compute the next x
        bits.append(x % 2)  # Extract the least significant bit (LSB)
    
    return bits





start = 2**13  # 8192
end = 2**14 - 1  # 16383

primes_14_bit = find_primes_between(start, end)
print(primes_14_bit)

### Choose two values p q from primes_14_bit
p = primes_14_bit[0]
q = primes_14_bit[-1]

print(f"Chosen p value: {p}")
print(f"Chosen q value: {q}")


n = p*q

print(f"n (modulous) value: {n}")
seed = generate_seed(n)
print(f"Generated Seed: {seed}")

# Generate the first 15 bits
first_15_bits = bbs_generate_bits(n, seed, num_bits=15)
print(first_15_bits)