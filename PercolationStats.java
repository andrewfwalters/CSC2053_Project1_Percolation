/*
 *  Compilation:  javac PercolationStats.java
 *  Execution:    java PercolationStats N T
 *  Dependancies: Percolation.java, Stopwatch.java,
 *                StdRandom.java, Math.java
 *  
 *  When executed, this class runs T expiriments on N-by-N grids
 *  of class percolation. Sites in the grid are opened at random
 *  until the system percolates. The fraction of open sites for each
 *  expiriment is recored and the mean and standard deviation
 *  for all experiments is found and printed.
 */

public class PercolationStats {
    
    // variables
    private double thresh[];
    private double av, sd;
    
    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        thresh = new double[T];
        for(int i = 0; i < T; i++) {
            thresh[i] = perc(N);
        }//end for
    }//end PercolationStats
    
    // perform a percolation of N by N grid and return fraction of open sites
    private double perc(int N) {
        Percolation p = new Percolation(N);
        int i, j;
        int count = 0;
        while(!p.percolates()) {
            i = StdRandom.uniform(N) + 1;
            j = StdRandom.uniform(N) + 1;
            if(!p.isOpen(i,j)){
                p.open(i,j);
                count++;
            }//end if
        }//end while
        return (double)count/(double)(N*N);
    }//end perc
    
    // sample mean of percolation threshold
    public double mean() {
        double count = 0;
        for(int i = 0; i < thresh.length; i++) {
            count+=thresh[i];
        }//end for
        av = count/thresh.length;
        return av;
    }//end mean
    
    // sample standard deviation of percolation threshold
    public double stddev() {
        double diff = 0;
        for(int i = 0; i < thresh.length; i++) {
            diff += Math.pow((thresh[i] - av),2);
        }//end for
        sd = Math.sqrt(diff / (thresh.length-1));
        return sd;
    }//end stdev
    
    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return av - ( (1.96 * sd) / Math.sqrt(thresh.length));
    }//end confidenceLo
    
    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return av + ( (1.96 * sd) / Math.sqrt(thresh.length));
    }//end confidenceHi
    
    // test client, described below
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        Stopwatch clock = new Stopwatch();
        PercolationStats ps = new PercolationStats(N,T);
        double time = clock.elapsedTime();
        
        System.out.println("mean:\t\t\t\t" + ps.mean());
        System.out.println("stddev:\t\t\t\t" + ps.stddev());
        System.out.println("95% confidence interval:\t\t" + ps.confidenceLo() + ", " + ps.confidenceHi());
        System.out.println("elapsed time:\t\t\t" + time + "s");
    }//end main
    
}//end class