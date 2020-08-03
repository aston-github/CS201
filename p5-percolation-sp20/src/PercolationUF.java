public class PercolationUF implements IPercolate{

    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;

    public PercolationUF(IUnionFind finder, int size){
        myFinder = finder;
        myFinder.initialize(size*size + 2);
        myOpenCount = 0;
        myGrid = new boolean[size][size];
        VTOP = size * size;
        VBOTTOM = size*size+1;

    }

    protected boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length) return false;
        if (col < 0 || col >= myGrid[0].length) return false;
        return true;
    }

    public int getIndex(int row, int col, int size) {
        return row*size + col;
    }


    @Override
    public boolean isOpen(int row, int col) {
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        return myGrid[row][col];
    }

    @Override
    public boolean isFull(int row, int col) {
        if(!inBounds(row, col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        return myFinder.connected(getIndex(row, col, myGrid.length), VTOP);
    }

    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }

    @Override
    public void open(int row, int col) {
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }

        int len = myGrid.length;
        int index = getIndex(row, col, len);
        if(myGrid[row][col]) return;

        myGrid[row][col] = true;
        myOpenCount++;

        if(inBounds(row - 1, col) && isOpen(row - 1, col)){
            myFinder.union(index, getIndex(row - 1, col, len));
        }

        if(inBounds(row + 1, col) && isOpen(row + 1, col)){
            myFinder.union(index, getIndex(row + 1, col, len));
        }

        if(inBounds(row, col - 1) && isOpen(row, col - 1)){
            myFinder.union(index, getIndex(row, col - 1, len));
        }

        if(inBounds(row, col + 1) && isOpen(row, col + 1)){
            myFinder.union(index, getIndex(row, col + 1, len));
        }


        if(row == 0) {
            myFinder.union(index, VTOP);
        }

        if(row == len - 1) {
            myFinder.union(index, VBOTTOM);
        }
    }
}
