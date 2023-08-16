import sys
inp  = list(input())
duck  = list();


for s in inp:
    check = False
    
    if s == 'q':
        for i in range(len(duck)) :
            if len(duck[i]) % 5 == 0 :
                duck[i]=duck[i]+'q'
                check = True
                break
        if check == False :
            duck.append('q')
        
    elif s == 'u':
        for i in range(len(duck)):
            if len(duck[i]) % 5 == 1 :
                duck[i]=duck[i]+'u'
                check = True
                break
        if check == False :
            print(-1)
            exit()
                # 리턴 -1 하고 끝내기
                    
    elif s == 'a':
        for i in range(len(duck)):
            if len(duck[i]) % 5 == 2 :
                duck[i]=duck[i]+'a'
                check = True
                break
        if check == False :
            print(-1)
            exit()        
        
    elif s == 'c':
        for i in range(len(duck)):
            if len(duck[i]) % 5 == 3 :
                duck[i]=duck[i]+'c'
                check = True
                break
        if check == False :
            print(-1)
            exit()
                        
    elif s == 'k':
        for i in range(len(duck)):
            if len(duck[i]) % 5 == 4 :
                duck[i]=duck[i]+'k'
                check = True
                break
        if check == False :
            print(-1)
            exit()        

for i in duck :
    if len(i)%5 !=0 :
        print(-1)
        exit()

print(len(duck))
