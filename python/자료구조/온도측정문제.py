def dailyTemperatures(temp):
    ans = [0]* len(temp)
    stack =[]
    
    for cur_day, cur_temp in enumerate(temp):
        while stack and stack[-1][1] < cur_temp :
            prev_day, _ = stack.pop()
            ans[prev_day] = cur_day- prev_day
        stack.append((cur_day,cur_temp))
        
    return ans


print(dailyTemperatures([73,74,75,71,69,72,76,73]))