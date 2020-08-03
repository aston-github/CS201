import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast {
    public PercolationBFS(int size){
        super(size);
    }

    @Override
    protected void dfs(int row, int col) {
        if (inBounds(row,col) == false){
            return;
        }
        if (isFull(row,col) || !(isOpen(row,col))){
            return;
        }
        Queue<Integer> qi = new LinkedList<Integer>();
        myGrid[row][col] = FULL;
        int size = myGrid.length;
        qi.add(row*size + col);

        //dequeue repeat thing
        while (qi.size() != 0){
            int value = qi.remove();
            int rowRef = value/size;
            int colRef = value%size;
//above full
            if (inBounds(rowRef-1,colRef) && !isFull(rowRef-1,colRef) && (isOpen(rowRef-1,colRef))) {
                myGrid[rowRef - 1][colRef] = FULL;
                qi.add((rowRef - 1) * size + colRef);
            }
            //below full
            if (inBounds(rowRef+1,colRef) && !isFull(rowRef+1,colRef) && (isOpen(rowRef+1,colRef))) {
                myGrid[rowRef + 1][colRef] = FULL;
                qi.add((rowRef + 1) * size + colRef);
            }
            //left full
            if (inBounds(rowRef,colRef-1) && !isFull(rowRef,colRef-1) && (isOpen(rowRef,colRef-1))) {
                myGrid[rowRef][colRef - 1] = FULL;
                qi.add(rowRef * size + (colRef - 1));
            }
            //right full
            if (inBounds(rowRef,colRef+1) && !isFull(rowRef,colRef+1) && (isOpen(rowRef,colRef+1))) {
                myGrid[rowRef][colRef + 1] = FULL;
                qi.add(rowRef * size + (colRef + 1));
            }
        }
    }
}
