arr = [1,2,3,4,5]
print(arr)
print(arr[::-1])
print(' '.join(map(str, arr)))
print(' '.join(map(str, arr[::-1])))

print(*arr[::-1])