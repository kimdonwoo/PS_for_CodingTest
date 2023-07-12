
def lc(nums):
    longest = 0
    num_dict = {}
    # dict 초기화 과정
    for num in nums:
        num_dict[num] = True
    
    for num in num_dict:
        if num -1 not in num_dict:
            cnt = 1
            target = num +1
            while target in num_dict:
                target +=1
                cnt +=1
            longest = max(longest,cnt)
            
    return longest





lc([6,7,100,5,4,4])