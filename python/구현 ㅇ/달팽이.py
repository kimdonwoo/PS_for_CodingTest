N = int(input()) # 홀수
b = int(input())

n = N

ground = [[0]*n for _ in range(n)]

trans = 1
cnt = n*n
row, col = -1,0


while n > 0:
    for i in range(n):  # 아래, 위 (row)
        row += trans
        ground[row][col] = cnt
        cnt -= 1
    n -= 1		# 첫 번째 외 2번씩 같은 칸 수 이동
    
    for i in range(n):  # ->, <- (col)
        col += trans
        ground[row][col] = cnt
        cnt -= 1

    trans *= -1	  # 증가/감소 방향 변경

# 오른 아래 왼 위 -> 아래 오른 위 왼으로 변경

for x in ground :
    print(' '.join(map(str,x)))

for i in range(N):
    for j in range(N):
        if ground[i][j] == b :
            print(i+1,j+1)


