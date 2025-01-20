import random


def get_s_box(seed):
    s_box = list(range(16))
    random.Random(seed).shuffle(s_box)
    return s_box


def display_s_box(s_box):
    print("S-box:")
    for i, val in enumerate(s_box):
        print(f"{hex(i)} -> {hex(val)}")
