
from itertools import combinations


prices = [10, 6, 5, 6, 7, 4, 3, 1, 10] # 9
k = 3

answer = 0

for i in range(len(prices)-k): # 0
    buy = prices[i]*k    
    for j in combinations(prices[i+1:], k):
        sell = sum(j)
            
        if sell > buy : # 수익 발생
            answer = max(answer,sell-buy)

print(answer)









