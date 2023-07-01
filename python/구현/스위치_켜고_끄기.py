"""

남 : 숫자 배수의 상태 모두 바꾸기

여 : 그 숫자 중심으로 좌우 대칭 뻗어나가서 그 구간 모두 스위칭

"""
import sys
input = sys.stdin.readline


def man(n):
    
    i = 1
    while len(light) > n*i :
        if light[n*i] == 1:
            light[n*i] = 0
        else : 
            light[n*i] = 1 
        i+=1
    
def woman(n):


    i = 1
    while True :
        # 먼저 범위 확인
        if n+i <= 0 or n+i >N or n-i <= 0 or n-i > N:
            break
        if light[n+i] == light[n-i]:
            i+=1
        else:
            break    
 
    if light[n] == 1:
        light[n] = 0
    else: 
        light[n] = 1 
            
    for x in range(1,i):
        if light[n+x] == 1:
            light[n+x] = 0
            light[n-x] = 0
        else : 
            light[n+x] = 1
            light[n-x] = 1 


N = int(input())

light =[0] + list(map(int,input().split()))

num = int(input())

students = [tuple(map(int,input().split())) for _ in range(num)]

for stu in students:
    if stu[0] == 1:
        man(stu[1])
    else :
        woman(stu[1])
     

for i in range(N):
    if i != 0 and i % 20 == 0 : print()
    print(light[i+1],end=' ')
