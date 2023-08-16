n, m = list(map(int, input().split(' ')))
array = list(map(int, input().split()))

# 시작점과 끝점 설정
start = 0
end=max(array)

# 이진 탐색 수행 (반복적)
result = 0

while(start <= end):
    total = 0
    mid = (start+end)//2
    for x in array:
        if x > mid:
            total += x - mid
    # 떡의 양이 부족할 때 더 많이 자르기
    if total < m :
        end = mid-1
        
    # 떡의 양이 충분할 때 덜 자르기
    else :
        result = mid
        start = mid + 1
    
print(result)
        