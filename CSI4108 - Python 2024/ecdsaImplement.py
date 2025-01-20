import time
from ecdsa import SigningKey, SECP160r1
from sympy import randprime, mod_inverse, isprime, factorint


#returns first primitive root to use as generator in DH
def find_primitive_roots(p):
    if not isprime(p):
        raise ValueError(f"{p} is not a prime number.")
    
    phi = p - 1  # Euler's totient function for prime p
    prime_factors = list(factorint(phi).keys())  # Prime factors of phi(p)

    for g in range(2, p):  # Test candidates g from 2 to p-1
        is_primitive = True
        for q in prime_factors:
            if pow(g, phi // q, p) == 1:  # Check g^(phi/q) mod p != 1
                is_primitive = False
                break
        if is_primitive:
            return g
    


# ECDH implementation
def ecdh():
    # Select the curve (SECP160r1)
    curve = SECP160r1

    # Alice's private and public key
    alice_private_key = SigningKey.generate(curve=curve)
    alice_public_key = alice_private_key.verifying_key

    # Bob's private and public key
    bob_private_key = SigningKey.generate(curve=curve)
    bob_public_key = bob_private_key.verifying_key

    # Alice computes Aprivate B public st they're equal
    alice = alice_private_key.privkey.secret_multiplier * bob_public_key.pubkey.point

    # Bob computes Bprivate A public st they're equal
    bob = bob_private_key.privkey.secret_multiplier * alice_public_key.pubkey.point

    # Verify that the generated keys match
    assert alice == bob, "Shared key don't match!"

    return alice

# Ordinary Diffie-Hellman implementation
def dh(bits=1024):
    # Generate a large prime p and a generator g
    p = randprime(2**(bits - 1), 2**bits)
    g = find_primitive_roots(p)

    #p =1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567892347
    #g= 2

    # Alice's private and public key
    alice_private = randprime(2, p - 1)
    alice_public = pow(g, alice_private, p)

    # Bob's private and public key
    bob_private = randprime(2, p - 1)
    bob_public = pow(g, bob_private, p)

    # Shared secret computed by Alice
    alice = pow(bob_public, alice_private, p)

    # Shared secret computed by Bob
    bob = pow(alice_public, bob_private, p)

    # Verify that the shared secret is the same
    assert alice == bob, "Shared secrets do not match!"

    return alice

# Compare performance
def compare_performance():
    # Measure time for ECDH
    start_time = time.time()
    ecdh_secret = ecdh()
    ecdh_time = time.time() - start_time

    # Measure time for ordinary Diffie-Hellman
    dhstart_time = time.time()
    dh_secret = dh()
    dh_time = time.time() - dhstart_time

    print(f"ECDH shared secret: {ecdh_secret}")
    print(f"ECDH time: {ecdh_time:.6f} seconds")

    print(f"DH shared secret: {dh_secret}")
    print(f"DH time: {dh_time:.6f} seconds")

# Run the comparison
compare_performance()
