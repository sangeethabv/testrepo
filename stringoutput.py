str='python and selenium'
s=str.split(' ')
print(s)
temp=s[0]
s[0]=s[1]
s[1]=s[2]
s[2]=temp

print(s)
