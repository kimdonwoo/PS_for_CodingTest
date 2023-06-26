"""
    30명 중에 2명 x

    map에 다 넣어놓고 입력 읽으면서 map 삭제?

"""

import sys
input = sys.stdin.readline

check = dict()

# for i in range(1,31):
#     check[i] = 0
check = {i: 0 for i in range(1, 31)}

for _ in range(28):
    check[int(input())] = 1;

for i in range(1,31):
    if check[i] == 0:
        print(i)

