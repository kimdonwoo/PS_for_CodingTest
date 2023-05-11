
# 재귀 풀이

# def fibo(num):
#     if num <= 1: return num
#     if num == 1 : return 1
#     if num == 2 : return 2
#     return fibo(num-2) + fibo(num-1)

# n = int(input())
# print(fibo(n))

# DP 풀이

n = int(input())

fibo =[0,1]
for i in range(2,n+1):
    fibo.append(fibo[i-1] + fibo[i-2])

print(fibo[n])

