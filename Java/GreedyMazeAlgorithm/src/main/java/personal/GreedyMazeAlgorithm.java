package edu.yu.introtoalgs;
/**
 * @author Tom Bohbot
 * @version December 1, 2020
 * @assignment Graphs And Mazes
 */
import java.util.*;

public class GraphsAndMazes {

  /** A immutable coordinate in 2D space.
   *
   * Students must NOT modify the constructor (or its semantics) in any way,
   * but can ADD whatever they choose.
   */
  public static class Coordinate { 
    public final int x, y;
    
    /** Constructor, defines an immutable coordinate in 2D space.
     *
     * @param x specifies x coordinate
     * @param y specifies x coordinate
     */
    public Coordinate(final int x, final int y) {
      this.x = x;
      this.y = y;
    }

    @Override 
    public boolean equals (Object that) {
      if (this == that) {
        return true;
      }
      if (that == null) {
        return false;
      }
      if (this.getClass() != that.getClass() ) {
        return false;
      }
      Coordinate thatCoordinate = (Coordinate) that;
      if (thatCoordinate.x != this.x || thatCoordinate.y != this.y) {
        return false;
      }
      return true;
    }
    
    @Override 
    public int hashCode() {
      Integer obj = this.x + this.y;
      return obj.hashCode();
    }

    @Override
    public String toString() {
      return "(" + x + "," + y + ")";
    }
  } // Coordinate class

  /** Given a maze (specified by a 2D integer array, and start and end
   * Coordinate instances), return a path (beginning with the start
   * coordinate, and terminating wih the end coordinate), that legally
   * traverses the maze from the start to end coordinates.  If no such
   * path exists, returns an empty list.  The path need not be a
   * "shortest path".
   *
   * @param maze 2D int array whose "0" entries are interpreted as
   * "coordinates that can be navigated to in a maze traversal (can be
   * part of a maze path)" and "1" entries are interpreted as
   * "coordinates that cannot be navigated to (part of a maze wall)".
   * @param start maze navigation must begin here, must have a value
   * of "0"
   * @param end maze navigation must terminate here, must have a value
   * of "0"
   * @return a path, beginning with the start coordinate, terminating
   * with the end coordinate, and intervening elements represent a
   * legal navigation from maze start to maze end.  If no such path
   * exists, returns an empty list.  A legal navigation may only
   * traverse maze coordinates, may not contain coordinates whose
   * value is "1", may only traverse from a coordinate to one of its
   * immediate neighbors using one of the standard four compass
   * directions (no diagonal movement allowed).  A legal path may not
   * contain a cycle.  It is legal for a path to contain only the
   * start coordinate, if the start coordinate is equal to the end
   * coordinate.
   */
  public static List<Coordinate> searchMaze (final int[][] maze, final Coordinate start, final Coordinate end) {
    int xLength = maze.length;
    if (start.x < 0 || start.x >= xLength || end.x < 0 || end.x >= xLength) {
      throw new IllegalArgumentException("Illegal x index for either start or end.");
    }
    int yStartLength = maze[start.x].length;
    int yEndLength = maze[end.x].length;
    if (start.y < 0 || start.y >= yStartLength || end.y < 0 || end.y >= yEndLength) {
      throw new IllegalArgumentException("Illegal y index for either start or end.");
    }
    if (maze[start.x][start.y] != 0 || maze[end.x][end.y] != 0) {
      throw new IllegalArgumentException("Start and end must have values of 0.");
    }
    if (start.equals(end) ) {
      LinkedList <Coordinate> mazeCoordinates = new LinkedList <>();
      mazeCoordinates.add(start);
      return mazeCoordinates;
    }
    return bfs(maze, start, end);
  }

  private static List <Coordinate> bfs (int [][] maze , final Coordinate start, final Coordinate end) {
    LinkedList <Coordinate> mazeCoordinates = new LinkedList <>();
    Queue <Coordinate> q = new ArrayDeque<>();
    int yLength = largestRow(maze);  // In the case of a jagged array, must calculate largest row.
    boolean [][] marked = new boolean [maze.length][yLength];
    Coordinate [][] edgeTo = new Coordinate [maze.length][yLength];
    q.add(start);
    marked[start.x][start.y] = true;
    Coordinate foundEndCoord = null;
    while (!q.isEmpty() && foundEndCoord == null) {
      Coordinate v = q.remove();
      for (Coordinate w : adj(maze , v) ) {
        // Avoid AIOB exception:
        if(w.x >= maze.length || w.y >= maze[w.x].length) {
          continue;
        }
        if (marked[w.x][w.y] == false) {
          q.add(w);
          marked[w.x][w.y] = true;
          edgeTo[w.x][w.y] = v;
          if (w.equals(end) ) {
            foundEndCoord = w;
            break;
          }
        }
      }
    }
    if (foundEndCoord != null) {
      return pathToEnd(edgeTo , foundEndCoord , start);
    }
    return mazeCoordinates;
  }

  private static List <Coordinate> pathToEnd (Coordinate [][] edgeTo , Coordinate w , Coordinate start) {
    LinkedList <Coordinate> mazeCoordinates = new LinkedList <>();
    while (!w.equals(start)) {
      mazeCoordinates.addFirst(w);
      w = edgeTo[w.x][w.y];
    }
    mazeCoordinates.addFirst(start);
    return mazeCoordinates;            
  }

  private static Coordinate [] adj (int [][] maze , Coordinate v) {
    int x = v.x;
    int y = v.y;
    int availableCoordinates = 0;
    Coordinate first = null;
    Coordinate second = null;
    Coordinate third = null;
    Coordinate fourth = null;
    // find out how many adjacent elements I will have. Max of four since diagonals are not permitted:
    if (x - 1 >= 0 && y < maze[x - 1].length) {
      if (maze[x - 1][y] == 0) {
        availableCoordinates ++;
        first = new Coordinate (x - 1, y);
      }
    }
    if (x + 1 < maze.length && y < maze[x + 1].length) {
      if (maze[x + 1][y] == 0) {
        availableCoordinates ++;
        second = new Coordinate (x + 1, y);
      }
    }
    if (y - 1 >= 0) {
      if (maze[x][y - 1] == 0) {
        availableCoordinates ++;
        third = new Coordinate (x, y - 1);
      }
    }
    if (y + 1 < maze[x].length) {
      if (maze[x][y + 1] == 0) {
        availableCoordinates ++;
        fourth = new Coordinate (x, y + 1);
      }
    }
    Coordinate [] adjacentCoodinates = new Coordinate [availableCoordinates];
    // Fill my array of adjacent vertixes I found earlier since I now declared the size of my array:
    int index = 0;
    if (first != null) {
      adjacentCoodinates[index] = first;
      index ++;
    }
    if (second != null) {
      adjacentCoodinates[index] = second;
      index ++;
    }
    if (third != null) {
      adjacentCoodinates[index] = third;
      index ++;
    }
    if (fourth != null) {
      adjacentCoodinates[index] = fourth;
      index ++;
    }
    return adjacentCoodinates;
  }

  private static int largestRow (int [][]maze) {
    int yLength = 0;
    for (int i = 0; i < maze.length; i ++) {
      if (maze[i].length > yLength) {
        yLength = maze[i].length;
      }
    }
    return yLength;
  }

  /** minimal main() demonstrates use of APIs
   */
  public static void main (final String[] args) {
    final int[][] exampleMaze = {
      {0, 0, 0},
      {0, 1, 1},
      {0, 1, 0}
    };
    final Coordinate start = new Coordinate(2, 0);
    final Coordinate end = new Coordinate(0, 2);
    final List<Coordinate> path = searchMaze(exampleMaze, start, end);
    System.out.println("path="+path);
  }

}
