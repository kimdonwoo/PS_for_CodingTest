# 딕셔너리 문제 5번 풀기 !!!!

def lc(nums):
    longest = 0
    num_dict = {}
    # dict 초기화 과정 num_dict = {x:True for x in num}도 가능
    
    for num in nums:
        num_dict[num] = True
    print(num_dict)
    
    for num in num_dict:
        if num - 1 not in num_dict:
            cnt = 1
            target = num +1
            while target in num_dict:
                target +=1
                cnt +=1
            longest = max(longest,cnt)
            
    return longest


print(lc([6,7,100,5,4,4]))