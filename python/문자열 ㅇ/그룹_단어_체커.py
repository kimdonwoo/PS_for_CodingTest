import sys
input = sys.stdin.readline

n = int(input())
res=0

for _ in range(n):
    str = list(input())
    check=[]
    success=True
    #print(str)
    
    for i in str:
        #print(i)

        if len(check) == 0: # 제일 처음
            check.append(i)
            continue
        
        if check[-1] == i: # 이어질 때는 그냥 패스
            continue
        
        if i in check : # 이어질때말고 이전에 존재했다면 해당 문자열 포함 x
            success = False
            break
        else:   # 성공
            check.append(i)
    if success :
        res+=1
    else:
        success = True
    
print(res)