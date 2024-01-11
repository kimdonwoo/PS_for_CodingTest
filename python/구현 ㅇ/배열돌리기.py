import sys
input= sys.stdin.readline

def change(n,d,shortest_distance):
    # arr을 d만큼 돌린다!

    if d < 0 :
        d = (d+360)//45
    else :
        d = d//45
    
    for _ in range(d):
        prev_list = list()

        for i in range(n + 1):  # 주대각선
            prev_list.append(shortest_distance[i][i])

        for i in range(n + 1):  # 주대각선 -> 가운데열
            prev_temp = shortest_distance[i][(n + 1) // 2]
            shortest_distance[i][(n + 1) // 2] = prev_list[i]
            prev_list[i] = prev_temp
            
        for i in range(n + 1):  # 가운대열 -> 부대각선
            prev_temp = shortest_distance[i][n - i]
            shortest_distance[i][n - i] = prev_list[i]
            prev_list[i] = prev_temp

        for i in range(n + 1):  # 부대각선 -> 가운데행
            prev_temp = shortest_distance[(n + 1) // 2][n - i]
            shortest_distance[(n + 1) // 2][n - i] = prev_list[i]
            prev_list[i] = prev_temp

        for i in range(n + 1):  # 가운데행 -> 주대각선
            shortest_distance[n - i][n - i] = prev_list[i]
    



T = int(input())

for _ in range(T):
    n,d = map(int,input().split())
    arr=[list(map(int,input().split())) for _ in range(n)]
    
    change(n-1,d,arr)
    
    for i in range(n):
        for j in range(n):
            print(arr[i][j],end=' ')
        print()
    
    
    
    