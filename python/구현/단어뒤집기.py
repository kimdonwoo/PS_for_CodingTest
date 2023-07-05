str = input()

tag = [0]


for i in range(len(str)):
    if str[i:i+1] == '<' :
        tag.append(i)
    elif str[i:i+1] == '>':
        tag.append(i)

tag.append(len(str))
temp = any

if len(tag) == 2:
        for i in range(len(tag)-1):
            if i%2 == 1:
                print(str[tag[i]:tag[i+1]+1], end='')
        else:
            temp = str[tag[i]:tag[i+1]].split(' ')
            for t in temp:
                print(t[::-1], end='')
                if t != temp[len(temp)-1]:
                    print(end=' ') 
    
else :
    for i in range(len(tag)-1):
        if i%2 == 1:
            print(str[tag[i]:tag[i+1]+1], end='')
        else:
            if i == 0 :
                temp = str[tag[i]:tag[i+1]].split(' ')
            else : temp = str[tag[i]+1:tag[i+1]].split(' ')
            for i in range(len(temp)):
                print(temp[i][::-1], end='')
                if i != len(temp)-1:
                    print(end=' ') 
