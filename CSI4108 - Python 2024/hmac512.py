import hashlib
import hmac

# HMAC-SHA-512 implementation
"""
	K xor ipad (inner pad -> not the apple device like a Mac)
	Then concatenated with the msg and hash that
	And take k xor opad (outer pad) and concatenate with previous hash 
	And hash the whole thing
	H[(k xor opad) concat H[(k xor ipad) concat m]]
	If the b is the size of the block in bits then k is padded with 0's so that the result is b bits in length
	Ipad is 00110110 (36 in hex) repeated b/8 times
    Opad is 01011100 (56 n hex) repeated b/8 times
"""

def hmac_sha512(key, message):
    block_size = 128  # Block size for SHA-512
    # Normalize the key
    if len(key) > block_size:
        key = hashlib.sha512(key).digest()
    if len(key) < block_size:
        key = key.ljust(block_size, b'\x00')
    
    # Create inner and outer padding
    ipad = bytes((x ^ 0x36) for x in key)
    opad = bytes((x ^ 0x5c) for x in key)
    
    # Inner hash
    inner_hash = hashlib.sha512(ipad + message).digest()
    
    # Outer hash
    hmac_result = hashlib.sha512(opad + inner_hash).digest()
    return hmac_result

# Test data
key = b"mysteryNotLock"
message = b"I am using this input string to test my own implementation of HMAC-SHA-512."

# Compute HMAC using implementation
implemented = hmac_sha512(key, message)

# Validate using Python's hmac library
library = hmac.new(key, message, hashlib.sha512).digest()

# Compare results
print(implemented.hex())
print(library.hex())
print(implemented == library)
