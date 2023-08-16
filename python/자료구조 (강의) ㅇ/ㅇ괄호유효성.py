def isValid(s):
    stack_list = []
    for p in s :
        if p == "(":
            stack_list.append(")")
        elif p == "{":
            stack_list.append("}")
        elif p == "[":
            stack_list.append("]")
        elif not stack_list or stack_list.pop() != p: # 비었거나 pop값이랑 다를때 false
            return False
    return not stack_list # 다 돌고나서 stack 비었으면 true

input = "{(([]))[]}"
print(isValid(input))

'''
    } ) ) ]



'''