"""
    <pass,continue,break 정리>
    
    1. pass : 실행할 코드가 없는 것으로 다음 행동을 계속해서 진행합니다.
    
    2. continue : 바로 다음 순번의 loop를 수행합니다.
    
    3. break : 반복문을 멈추고 loop 밖으로 나가도록합니다.
                반복문 단 하나만 빠져 나감. 여러개 x
                flag 하나 사용해서 빠져나가기 가능할듯
    
"""

"""
    
    <파이썬 자료구조>
    
    1. List : 다른 파일에 정리
    
    2. tuple : 리스트와 같지만, 데이터 변경 x
            tuple()
    
    3. set : 데이터 중복 허용x , 수학의 집합 연산 지원
            set([1,2,3,1,2,3]) -> {1,2,3} // list를 set으로 바꿈
            set('abbaaab') -> {'a','b'} // 이런것도 가능
            add(n), remove(n), update([4,5]), clear() 등 가능
            
            합집합 : | , 교집합 : & , 차집합 : - , 대칭 차집합 : ^ 연산도 가능
            
    4. dictionary : key-value 형식, 읽기 빠름
            사용 예시 : dic = {number2[i] : i for i in range(len(number2))}
                numbers['kk'] = 1002 이런식으로 추가도 가능
                number.keys() 로 모든 키값을 리스트로 저장 가능 (number.values()도 가능)
    
    5. stack : 나중에 들어온 값이 먼저 나감
            list의 append(i)와 pop()으로 구현 가능
    
    6. queue : 먼저 들어온 값이 먼저 나감
            from collections import deque
            
            q = deque([1,2,3])
            q.append(4)
            q.popleft()
            
            이런 식으로 구현 (BFS)
            deque에서 쓸수 있는 함수들
                appendleft(),append(),popleft(),pop(),remove()
                clear(),insert(),reverse(),count() 등등...

    
    
    
"""