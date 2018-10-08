import sys

T = int(raw_input())
def factorial(n):

    if n == 0 :
        return 1
    temp = 1
    for i in xrange(1,n+1):
        temp *= i
        pass
    return temp
  
def main() :
    for n in sys.stdin :
        print factorial(int(n))
main()