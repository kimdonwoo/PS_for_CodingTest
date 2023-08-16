# 노드 구현
class ListNode(object):
    def __init__(self, val=0, next=None, prev=None):
        self.val = val
        self.next = next
        self.prev = prev

# 이중 연결 리스트 구현 + Head,Current 구현
class BrowserHistory(object):
    def __init__(self,homepage):
        self.head = self.current=ListNode(val = homepage) # next,prev 둘다 none
    def visit(self,url):
        self.current.next = ListNode(val = url,prev=self.current)
        self.current = self.current.next
        return None
    def back(self,steps): # 얘가 앞으로임!
        while steps > 0 and self.current.prev != None:
            steps -=1
            self.current = self.current.prev
        return self.current.val
        
    def forward(self,steps):
        while steps > 0 and self.current.next != None:
            steps -=1
            self.current = self.current.next
        return self.current.val

bh = BrowserHistory("homePage")
bh.visit("nextPage")
bh.visit("nextnextPage")
print(bh.back(1))
print(bh.back(1))
print(bh.forward(100))




