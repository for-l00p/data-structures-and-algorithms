
from sys import stdin,stdout
def io():
    n,k = stdin.readline().split()
    n,k = int(n), int(k)
    count = len([x for x in stdin if not int(x) % k])
    print count
io()
