import re

N = int(input())
regexp = re.compile ("-?[0-9]+")
def gcd (a,b):
		if (b == 0):
			return a
		else:
			return gcd(b,a%b)
for i in range(N):
	line = input()
	tokens = regexp.findall(line)
	a = int(tokens[0])
	b = int(tokens[1])
	c = gcd(a,b)
	print (c)
