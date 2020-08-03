//Aston Yong
//Samaya Pattim
public class PercolationDFSFast extends PercolationDFS{
   public PercolationDFSFast (int size){
       super(size);
   }
    @Override
    protected void updateOnOpen(int row, int col) {
        super.updateOnOpen(row, col);
        boolean full = false;
        if (row == 0)
            full = true;
        //above full
        if (row != 0 && myGrid[row - 1][col] == FULL)
            full = true;
        //below full
        if (row != myGrid[row].length - 1 && myGrid[row+1][col] == FULL)
            full = true;
        //left full
        if (col != 0 && myGrid[row][col - 1] == FULL)
            full = true;
        //right full
        if (col != myGrid[row].length - 1 && myGrid[row][col + 1] == FULL)
            full = true;
        if (full) dfs(row, col);
    }
}
