import sys
def z(n):
    r = 0
    while n>=5:
        n /= 5
        r += n
    return r
def main():
    n = sys.stdin.readline()
    for t in sys.stdin:
        print z(int(t))
main() 