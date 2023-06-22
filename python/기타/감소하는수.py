from itertools import combinations

N = int(input())
result = []

for i in range(1, 11):
    for j in combinations([9,8,7,6,5,4,3,2,1,0], i):
        num = ''.join(map(str, j))
        result.append(int(num))

result.sort()
if N >= len(result):
	print(-1)
else:
	print(result[N])
