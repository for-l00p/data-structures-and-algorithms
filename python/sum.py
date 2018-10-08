import re

N = int(input())
regexp = re.compile("-?[0-9]")
for i in range(N):
	line = input()
	tokens = regexp.findall(line)
	sum = 0
	for j in tokens:
		sum = sum + int(j)
	print("The sum of numbers for this line is " + str(sum))

