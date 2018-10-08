import sys
T = int(raw_input())
def factorial(n):
    r = 0
    while n > 4:
        n /= 5
        r += n
        pass
    print r

def main():
    for n in sys.stdin:
       factorial(int(n))
main()