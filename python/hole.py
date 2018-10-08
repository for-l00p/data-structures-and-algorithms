import sys

T = int(raw_input())
def holenum(n):
    temp = 0
    if n in ['A','D','O','P','Q','R']:
        return 1
    if n == 'B':
        return 2
    for char in sys.stdin.readline() :
        print holenum(n)
main()