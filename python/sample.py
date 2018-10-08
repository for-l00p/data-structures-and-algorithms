x,y=raw_input().split()
x,y=int(x),float(y)
if(x%5!=0):
    print "%.2f"%y
elif((float(x)+0.5)>y):
    print "%.2f"%y
else:
    print "%.2f"%(y-float(x)-0.5)