import random

def get_round_keys(seed):
    random.seed(seed)
    round_keys = [f"{random.randint(0, 2 ** 16 - 1):016b}" for _ in range(5)]
    return round_keys

def display_round_keys(round_keys):
    print("Round Keys:")
    for i, key in enumerate(round_keys):
        print(f"Round Key {i+1}: {key}")

