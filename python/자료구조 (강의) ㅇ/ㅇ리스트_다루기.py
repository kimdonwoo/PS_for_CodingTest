import sys
input = sys.stdin.readline

# 전부다 0 이거나 false 인 shortest_distance 생성
'''
    [[False,False,False,False],
     [False,False,False,False],
     [False,False,False,False]]
    
    이렇게 되자나 그럼 graph1[0][0] ~ graph1[2][3]
    즉, n*m 일때, shortest_distance[n][m]인 거!!

    [[False]*m for _ in range(n)]
'''
graph1= [[False]*4 for _ in range(3)]
print(graph1)

# dfs나 bfs 관련문제에서 1 2 3 4 이런식일때, shortest_distance list로 만들때
ground = [list(map(int,input().split())) for _ in range(3)]
print(ground)

# 숫자말고 .#...# 이런식으로 주어질때
graph3 = [list(input()) for _ in range(3)]
print(graph3)

# 리스트 크기 : len 
my_list = ['do','nu']
print(len(my_list))

# 리스트 추가 : append
my_list = ['do','nu']
my_list.append('d')
print(my_list)

# 리스트 합치기 : extend
my_list = ['do','nu']
my_list2 = ['o','n']
my_list.extend(my_list2)
print(my_list,my_list2)

# 리스트에 특정 값이 있는지 체크 if x in list (dict도 마찬가지임)

if item in list: 
    print('리스트에 값이 있습니다.')
else:
    print('리스트에 값이 없습니다.')
    
# 리스트 출력할 때 원소하나씩 출력 
print(*my_list)

# 리스트에 튜플데이터로 입력받아서 넣기
person=[tuple(map(int,input().split())) for _ in range(n)]

'''
    insert() : 원하는 위치에 값 추가
    pop() : 원하는 위치의 값 삭제
    clear() : 모든 값 삭제
    sort() : 값 순서대로 정렬
    reverse() : 순서 뒤집기
    copy() : 리스트 복사
    count() : 어떤 값이 몇 개 있을지
    index() : 어떤 값이 어디에 있는지
'''

# 출력하기
arr = [1,2,3,4,5]

print(arr) # [1, 2, 3, 4, 5]
print(*arr) # 1 2 3 4 5
print(' '.join(map(str, arr))) # 1 2 3 4 5

print(arr[::-1]) # [5, 4, 3, 2, 1]
print(*arr[::-1]) # 5 4 3 2 1
print(' '.join(map(str, arr[::-1]))) # 5 4 3 2 1
