package pl.edu.pja.s11531.psm.cellular

import javafx.util.Pair

boolean countDiagonal = true
List<List<Integer>> config = []
config << [10, 10]
config << [11, 10]
config << [12, 10]
config << [10, 11]
config << [11, 12]

def createRule = [3]
def dieRule = [0, 1, 4, 5, 6, 7, 8]
int size = 20
int[][] board = new int[size][size]
board.metaClass.getValue { x, y ->
    x %= size
    y %= size
    delegate[x][y] & 1 ? 1 : 0
}
board.metaClass.countNeighbours { x, y ->
    int sum = 0;
    sum += delegate.getValue(x, y - 1)
    sum += delegate.getValue(x + 1, y)
    sum += delegate.getValue(x, y + 1)
    sum += delegate.getValue(x - 1, y)
    if (countDiagonal) {
        sum += delegate.getValue(x - 1, y - 1)
        sum += delegate.getValue(x + 1, y - 1)
        sum += delegate.getValue(x - 1, y + 1)
        sum += delegate.getValue(x + 1, y + 1)
    }
    return sum
}

def drawBoard(int[][] b) {
    b.each {
        it.each {
            print it ? 'O' : ' '
        }
        println ''
    }
    println '======================='
}

def step(int[][] b, List<Integer> createRule, List<Integer> dieRule) {
    def size = b.length
    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            int n = b.countNeighbours(i, j)
//            print n ? n : ' '
            if (createRule.contains(n)) {
//                print 'C'
                b[i][j] += 2;
            } else if (dieRule.contains(n)) {
//                print ' '
//                do nothing
            } else {
//                print 'S'
                b[i][j] += b[i][j] ? 2 : 0;
            }
//            print ' '
        }
//        println ''
    }
}

def transfer(int[][] b) {
    def size = b.length

    for (int i = 0; i < size; i++) {
        for (int j = 0; j < size; j++) {
            b[i][j] = b[i][j] & 2 ? 1 : 0
        }
    }
}

config.each {
    board[it[1]][it[0]] = 1
}

drawBoard(board)
while (1) {
    step(board, createRule, dieRule)
    transfer(board)
    drawBoard(board)
    sleep(200)
}
