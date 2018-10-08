N= int(input("Enter the value of N"))
for i in range(N):
	p=float(input("Enter the probability of A winning"))
	if p <= 0.5:
		print(10000+10000*(p)*(1-(2*p)))
	if p > 0.5:
		print(10000+10000*(1-p)*((2*p)-1))	

