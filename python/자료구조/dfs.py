

# dfs 접근
def dfs(root):
    if root is None:
        return
    dfs(root.left)
    dfs(root.right)
    
dfs(root)

# 전위 순회 방문
def preorder(cur_node):
    if cur_node is None:
        return
    
    print(cur_node.value)
    preorder(cur_node.left)
    preorder(cur_node.right)

preorder(root)

# 중위 순회 방문
def inorder(cur_node):
    if cur_node is None:
        return
    
    inorder(cur_node.left)
    print(cur_node.value)
    inorder(cur_node.right)

inorder(root)

# 후위 순회 방문
def postorder(cur_node):
    if cur_node is None:
        return
    
    postorder(cur_node.left)
    postorder(cur_node.right)
    print(cur_node.value)

postorder(root)