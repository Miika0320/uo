def gcd(a, b):
    """Helper function to calculate the greatest common divisor (GCD) using Euclidean algorithm."""
    while b != 0:
        a, b = b, a % b
    return a

def euler_totient(n):
    """Function to calculate Euler's Totient function φ(n)."""
    setn = []
    for i in range(1, n + 1):
        if gcd(n, i) == 1:
            setn.append(i)
    return setn

def find_primitive_roots(numbers, n, s):
    """Find all primitive roots from a set of numbers mod n."""
    primitive_roots = []
    periods = []
    k = 0
    for j in numbers:
        periods = []
        for i in range(1,s+1):
            k = j**i % n
            periods.append(k)
            if k == 1:
                break

        primitive_roots.append(periods)
        
    
    return primitive_roots

# Example usage:
n=25
numbers = euler_totient(n)
prim = find_primitive_roots(numbers,n, len(numbers))
prims = []
for i in prim:
    if len(i)==len(numbers):
        prims.append(i[0])

print(prim)
print(prims)