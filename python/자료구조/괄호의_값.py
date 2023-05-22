arr = list(input())

stack = []
res = 0
tmp = 1

for i in range(len(arr)):
    
    if arr[i] == "(" :
        stack.append(arr[i])
        tmp*=2
    
    elif arr[i] == "[" :
        stack.append(arr[i])
        tmp*=3
    
    elif arr[i] == ")" :
        if not stack or stack[-1] == "[":
            res = 0
            break
        if arr[i-1] == "(": # ()일때
            res += tmp
        # ([])
        stack.pop()
        tmp //= 2
        
    else :  # ] 일때
        if not stack or stack[-1] == "(":
            res = 0
            break
        if arr[i-1] == "[": # []일때
            res += tmp
        
        stack.pop()
        tmp //= 3
        
if stack :
    print(0)
else:
    print(res)        