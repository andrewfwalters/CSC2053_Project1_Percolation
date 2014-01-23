Percolation Project
===================

Percolation
-----------

Given a composite system comprised of randomly distributed insulating and metallic materials: what fraction of the materials need to be metallic so that the composite system is an electrical conductor? Given a porous landscape with water on the surface (or oil below), under what conditions will the water be able to drain through to the bottom (or the oil to gush through to the surface)? Scientists have defined an abstract process known as percolation to model such situations.

The Model
---------

We model a percolation system using an N-by-N grid of sites. Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. We say the system percolates if there is a full site in the bottom row. In other words, a system percolates if we fill all open sites connected to the top row and that process fills some open site on the bottom row. (For the insulating/metallic materials example, the open sites correspond to metallic materials, so that a system that percolates has a metallic path from top to bottom, with full sites conducting. For the porous substance example, the open sites correspond to empty space through which water might flow, so that a system that percolates lets water fill open sites, flowing from top to bottom.)

![perc_one](http://www.csc.villanova.edu/~map/2053/s14/percolation/percolates.png)
![perc_two](http://www.csc.villanova.edu/~map/2053/s14/percolation/percolates.png)

The Problem
-----------

In a famous scientific problem, researchers are interested in the following question: if sites are independently set to be open with probability p (and therefore blocked with probability 1 − p), what is the probability that the system percolates? When p equals 0, the system does not percolate; when p equals 1, the system percolates. The plots below show the site vacancy probability p versus the percolation probability for 20-by-20 random grid (left) and 100-by-100 random grid (right).

![graph_one](http://www.csc.villanova.edu/~map/2053/s14/percolation/percolation-threshold20.png)
![graph_two](http://www.csc.villanova.edu/~map/2053/s14/percolation/percolation-threshold100.png)

When N is sufficiently large, there is a threshold value p* such that when p < p* a random N-by-N grid almost never percolates, and when p > p*, a random N-by-N grid almost always percolates. No mathematical solution for determining the percolation threshold p* has yet been derived. Your task is to write a computer program to estimate p*.

Percolation data type
---------------------

To model a percolation system, create a data type Percolation with the following API:

public class Percolation {
   public Percolation(int N)              // create N-by-N grid, with all sites blocked
   public void open(int i, int j)         // open site (row i, column j) if it is not already
   public boolean isOpen(int i, int j)    // is site (row i, column j) open?
   public boolean isFull(int i, int j)    // is site (row i, column j) full?
   public boolean percolates()            // does the system percolate?
}

Use the union-find data structure (as described and implemented in lecture) to efficiently implement the Percolation data type. 
By convention, the indices i and j are integers between 1 and N, where (1, 1) is the upper-left site. Throw a java.lang.IndexOutOfBoundsException if either i or j is outside this range.

Monte Carlo Simulation
----------------------

 To estimate the percolation threshold, consider the following computational experiment:

Initialize all sites to be blocked.
Repeat the following until the system percolates:
Choose a site (row i, column j) uniformly at random among all blocked sites.
Open the site (row i, column j).
The fraction of sites that are opened when the system percolates provides an estimate of the percolation threshold.

Deliverables
------------

Submit the following files through the CSC Department website:

Percolation.java (using the weighted quick-union algorithm as implemented in the WeightedQuickUnionUF class)
PercolationStats.java
a readme.txt file, answering all questions. 
PercolationStats.doc A project report, including listings of the above files and sample runs and screenshots from your testing. 