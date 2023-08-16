# N = int(input())

# # 먼저 8 -> 10
# temp = 0
# base = 1
# while N:
#     temp +=(N%10)*base
#     base*=8
#     N = N//10
# N = temp

# # 다음 10 -> 2
# res = list()
# while N:
#     res.append(str(N%2))
#     N = N//2

# res.reverse()
# print(''.join(res))


print(bin(int(input(), 8))[2:])