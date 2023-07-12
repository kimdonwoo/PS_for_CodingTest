class Node():
    def __init__(self, val=0, prev = None, next = None):
        self.val = val
        self.prev = prev
        self.next = next
        
class BrowserHistory():
    def __init__(self,homepage):
        self.head = self.current = Node(val = homepage)
    
    def visit(self,url):
        self.current.next=Node(val = url, prev = self.current)
        self.current = self.current.next
        return None
    def back(self,steps):
        while steps > 0 and self.current.prev !=None:
            steps -=1
            self.current =self.current.prev
        return self.current.val
    def forward(self,steps):
        while steps > 0 and self.current.next !=None:
            steps -=1
            self.current =self.current.next
        return self.current.val

bh = BrowserHistory("homePage")
bh.visit("nextPage")
bh.visit("nextnextPage")
print(bh.back(1))
print(bh.back(1))
print(bh.forward(100))