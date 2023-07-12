def solution(s, N):
    
    stri = list(map(int,s))
    answer = 0

    for i in range(len(stri)+1-N):
        num = 0
        for j in range(N):
            if stri[i+j] <= N :
                num = num*10 + stri[i+j]
                    
        if len(list(map(int,str(num)))) != len(set(list(map(int,str(num))))) :
            continue
        if num//(10**(N-1)) != 0: 
            answer = max(answer,num)


    if answer == 0 : answer = -1

    
    return answer