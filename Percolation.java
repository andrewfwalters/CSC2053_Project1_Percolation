/*
 *  Compilation:  javac Percolation.java
 *  Execution:    This class does not contain a main method
 *  Dependancies: WeightedQuickUnionUF.java
 *  
 *  This class creates an N by N grid of closed sites
 *  that can be opened by the function open(row,col).
 *  The function isFull(row,col) will determine whether
 *  water has percolated to that site and percolates() will
 *  determine if water has percolated to through the grid.
 * 
 *  grid indicies begin at 1 instead of zero for all function in this class
 */


public class Percolation {
    //constants
    private final int CLOSED = 0;
    private final int OPEN = 1;
    //variables
    private int size;
    private int grid[][];
    private WeightedQuickUnionUF perc;
    private WeightedQuickUnionUF full;
    
    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        
        perc = new WeightedQuickUnionUF(N*N+2);
        full = new WeightedQuickUnionUF(N*N+1);
        
        grid = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                grid[i][j] = CLOSED;
            }//end for
        }//end for
        
        size = N;
        
    }//end constructor
    
    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        if(isOpen(i,j)) {
            return;
        }
        else {
            grid[(i-1)][(j-1)] = OPEN;
            
            //join left
            if(j!=1)
                con(i,j,i,(j-1));
            
            //join right
            if(j!=size)
                con(i,j,i,(j+1));
            
            //join above
            if(i!=1) {
                con(i,j,(i-1),j);
            }
            else {
                perc.union(g2p(i,j),0);
                full.union(g2p(i,j),0);
            }
            
            //join below
            if(i!=size)
                con(i,j,(i+1),j);
            else
                perc.union(g2p(i,j),(size*size+1));
            
        }//end else
            
        
    }//end open
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        if(grid[(i-1)][(j-1)] == OPEN)
            return true;
        else
            return false;
    }//end isOpen
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        return full.connected(0,g2p(i,j));
    }//end isFull
    
    // does the system percolate?
    public boolean percolates() {
        return perc.connected(0,size*size+1);  
    }//end percolates
    
    //converts grid coordinates to index of UF struct
    private int g2p(int i, int j){
        return (i-1)*size + (j-1) + 1;
    }//end mod
    
    //union two sites
    private void con(int pi, int pj, int qi, int qj) {       
        //ensure both sites are open
        if( isOpen(pi,pj) && isOpen(qi,qj) ) {
            perc.union(g2p(pi,pj),g2p(qi,qj));
            full.union(g2p(pi,pj),g2p(qi,qj));
        }//end if       
    }//end con
}//end class