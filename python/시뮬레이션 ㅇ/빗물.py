import sys
input = sys.stdin.readline

res = 0
H,W = map(int,input().split())
block = list(map(int,input().split()))

for i in range(1,W-1):
    left_max = max(block[:i])
    right_max = max(block[i+1:])

    comp = min(left_max,right_max)
    
    if block[i] < comp :
        res += comp-block[i]
        
print(res)

"""

    max(), min()으로 리스트 또는 변수사이의 값 비교가 가능
    전체 리스트의 입장에서 문제 바라보기뿐만 아니라
        리스트의 원소 입장에서 문자 바라보기

"""




