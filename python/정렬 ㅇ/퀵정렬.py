array = [5,7,9,0,3,1,6,2,4,8]

def quick_sort(array,start,end):
    if start >= end: return # 원소가 한개일 때 종료
    pivot = start # 피벗은 첫번째원소로 설정
    left = start+1
    right = end
    while(left <= right):
        # 피벗보다 큰 데이터를 찾을 때 까지 반복
        while(left <= end and array[left] <= array[pivot]):
            left+=1
        # 피벗보다 작은 데이터를 찾을 때 까지 반복
        while(right > start and array[right] >= array[pivot]):
            right-=1
        # 엇갈렸다면 작은 데이터랑 피벗 교체
        if(left > right):
            array[right], array[pivot] = array[pivot], array[right]
        # 엇갈리지 않았다면 작은데이터랑 큰 데이터 교체
        else:
            array[left], array[right] = array[right], array[left]
    # 해당 배열에서 분할 끝났으면 왼쪽 오른쪽 나눠서 다시 퀵정렬 수행
    quick_sort(array, start, right - 1)
    quick_sort(array, right+1, end)
    
quick_sort(array,0,len(array)-1)
print(array)        
