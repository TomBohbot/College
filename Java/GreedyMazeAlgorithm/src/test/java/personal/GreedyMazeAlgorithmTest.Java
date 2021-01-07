/**
 * Testing Graphs And Mazes.
 * @author  Tom Bohbot
 * @version December 2, 2020
 */

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.*;

import edu.yu.introtoalgs.GraphsAndMazes;
import edu.yu.introtoalgs.GraphsAndMazes.Coordinate;

import java.util.*;


public class GraphsAndMazesTest {

    @Test (expected = IllegalArgumentException.class)
	public void startEqualsWall() {
        final int[][] maze = {
            {0, 0, 0},
            {0, 1, 1},
            {1, 1, 0}
        };
        final Coordinate start = new Coordinate(2, 0);
        final Coordinate end = new Coordinate(0, 2);
        GraphsAndMazes.searchMaze(maze, start, end);
    }

    @Test (expected = IllegalArgumentException.class)
	public void EndEqualsWall() {
        final int[][] maze = {
            {0, 0, 1},
            {0, 1, 1},
            {0, 1, 0}
        };
        final Coordinate start = new Coordinate(2, 0);
        final Coordinate end = new Coordinate(0, 2);
        GraphsAndMazes.searchMaze(maze, start, end);
    }

    @Test (expected = IllegalArgumentException.class)
	public void illegalStartXIndex() {
        final int[][] maze = {
            {0, 0, 0},
            {0, 1, 1},
            {0, 1, 0}
        };
        final Coordinate start = new Coordinate(-1, 0);
        final Coordinate end = new Coordinate(0, 2);
        GraphsAndMazes.searchMaze(maze, start, end);
    }

    @Test (expected = IllegalArgumentException.class)
	public void illegalStartXIndexBig() {
        final int[][] maze = {
            {0, 0, 0},
            {0, 1, 1},
            {0, 1, 0}
        };
        final Coordinate start = new Coordinate(3, 0);
        final Coordinate end = new Coordinate(0, 2);
        GraphsAndMazes.searchMaze(maze, start, end);
    }

    @Test (expected = IllegalArgumentException.class)
    public void illegalStartYIndex() {
        final int[][] maze = {
            {0, 0, 0},
            {0, 1, 1},
            {0, 1, 0}
        };
        final Coordinate start = new Coordinate(0, -1);
        final Coordinate end = new Coordinate(0, 2);
        GraphsAndMazes.searchMaze(maze, start, end);
    }

    @Test (expected = IllegalArgumentException.class)
	public void illegalStartYIndexBig() {
        final int[][] maze = {
            {0, 0, 0},
            {0, 1, 1},
            {0, 1, 0}
        };
        final Coordinate start = new Coordinate(0, 3);
        final Coordinate end = new Coordinate(0, 2);
        GraphsAndMazes.searchMaze(maze, start, end);
    }

    @Test (expected = IllegalArgumentException.class)
    public void illegalEndXIndex() {
        final int[][] maze = {
            {0, 0, 0},
            {0, 1, 1},
            {0, 1, 0}
        };
        final Coordinate start = new Coordinate(0, 0);
        final Coordinate end = new Coordinate(-1, 2);
        GraphsAndMazes.searchMaze(maze, start, end);
    }

    @Test (expected = IllegalArgumentException.class)
	public void illegalEndXIndexBig() {
        final int[][] maze = {
            {0, 0, 0},
            {0, 1, 1},
            {0, 1, 0}
        };
        final Coordinate start = new Coordinate(0, 0);
        final Coordinate end = new Coordinate(3, 2);
        GraphsAndMazes.searchMaze(maze, start, end);
    }

    @Test (expected = IllegalArgumentException.class)
    public void illegalEndYIndex() {
        final int[][] maze = {
            {0, 0, 0},
            {0, 1, 1},
            {0, 1, 0}
        };
        final Coordinate start = new Coordinate(0, 0);
        final Coordinate end = new Coordinate(0, -1);
        GraphsAndMazes.searchMaze(maze, start, end);
    }

    @Test (expected = IllegalArgumentException.class)
	public void illegalEndXIndeYBig() {
        final int[][] maze = {
            {0, 0, 0},
            {0, 1, 1},
            {0, 1, 0}
        };
        final Coordinate start = new Coordinate(0, 0);
        final Coordinate end = new Coordinate(0, 3);
        GraphsAndMazes.searchMaze(maze, start, end);
    }

    @Test 
	public void startEqualsEnd () {
        final int[][] maze = {
            {0, 0, 0},
            {0, 1, 1},
            {0, 1, 0}
        };
        final Coordinate start = new Coordinate(2, 2);
        final Coordinate end = new Coordinate(2, 2);
        List <Coordinate> list = Arrays.asList(start);
        assertEquals(list , GraphsAndMazes.searchMaze(maze, start, end) );
    }

    @Test 
	public void testOne () {
        final int[][] maze = {
            {0, 0, 0},
            {0, 1, 1},
            {0, 1, 0}
        };
        final Coordinate start = new Coordinate(2, 0);
        final Coordinate end = new Coordinate(0, 2);
        Coordinate two = new Coordinate(1 , 0);
        Coordinate three = new Coordinate(0 , 0);
        Coordinate four = new Coordinate(0 , 1);
        List <Coordinate> list = Arrays.asList(start , two , three , four , end);
        assertEquals(list , GraphsAndMazes.searchMaze(maze, start, end) );
    }

    @Test 
	public void testTwo () {
        final int[][] maze = {
            {0, 0, 0},
            {1, 0, 1},
            {0, 0, 0}
        };
        final Coordinate start = new Coordinate(2, 0);
        final Coordinate end = new Coordinate(0, 2);
        Coordinate two = new Coordinate(2 , 1);
        Coordinate three = new Coordinate(1 , 1);
        Coordinate four = new Coordinate(0 , 1);
        List <Coordinate> list = Arrays.asList(start , two , three , four , end);
        assertEquals(list , GraphsAndMazes.searchMaze(maze, start, end) );
    }

    @Test 
	public void testThree () {
        final int[][] maze = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
        };
        final Coordinate start = new Coordinate(2, 0);
        final Coordinate end = new Coordinate(0, 2);
        Coordinate two = new Coordinate(1 , 0);
        Coordinate three = new Coordinate(0 , 0);
        Coordinate four = new Coordinate(0 , 1);
        List <Coordinate> list = Arrays.asList(start , two , three , four , end);
        assertEquals(list , GraphsAndMazes.searchMaze(maze, start, end) );
    }

    @Test 
	public void testFour () {
        final int[][] maze = {
            {0, 1, 0},
            {1, 0, 0},
            {0, 1, 0}
        };
        final Coordinate start = new Coordinate(2, 0);
        final Coordinate end = new Coordinate(0, 2);
        List <Coordinate> list = new ArrayList<>();
        assertEquals(list , GraphsAndMazes.searchMaze(maze, start, end) );
    }

    @Test 
	public void biggerMaze () {
        final int[][] maze = {
            {0, 0, 0 , 0 , 0},
            {0, 1, 0 , 1 , 0},
            {0, 1, 0 , 1 , 0},
            {0, 0, 0 , 0 , 0}
        };
        final Coordinate start = new Coordinate(2, 2);
        final Coordinate end = new Coordinate(0, 4);
        Coordinate two = new Coordinate(1 , 2);
        Coordinate three = new Coordinate(0 , 2);
        Coordinate four = new Coordinate(0 , 3);

        List <Coordinate> list = Arrays.asList(start , two , three , four , end);
        assertEquals(list , GraphsAndMazes.searchMaze(maze, start, end) );
    }

    @Test 
	public void jaggedMaze () {
        final int[][] maze = {
            {1, 0, 1 , 0 , 0},
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        final Coordinate start = new Coordinate(0, 1);
        final Coordinate end = new Coordinate(2, 0);
        Coordinate two = new Coordinate(1 , 1);
        Coordinate three = new Coordinate(1 , 2);
        Coordinate four = new Coordinate(2 , 2);
        Coordinate five = new Coordinate(3 , 2);
        Coordinate six = new Coordinate(3 , 1);
        Coordinate seven = new Coordinate(3 , 0);

        List <Coordinate> list = Arrays.asList(start , two , three , four , five , six , seven , end);
        assertEquals(list , GraphsAndMazes.searchMaze(maze, start, end) );
    }

    @Test 
	public void jaggedMazeImpossible () {
        final int[][] maze = {
            {1, 0, 1 , 0 , 0},
            {1, 0, 0},
            {0, 1},
            {0, 0, 0}
        };
        final Coordinate start = new Coordinate(0, 1);
        final Coordinate end = new Coordinate(2, 0);
        List <Coordinate> list = Arrays.asList();
        assertEquals(list , GraphsAndMazes.searchMaze(maze, start, end) );
        final int[][] mazeTwo = {
            {1, 0, 1 , 0 , 0},
            {1, 0, 0},
            {0, 1, 1},
            {0, 0, 0}
        };
        final Coordinate startTwo = new Coordinate(0, 1);
        final Coordinate endTwo = new Coordinate(2, 0);
        List <Coordinate> listTwo = Arrays.asList();
        assertEquals(listTwo , GraphsAndMazes.searchMaze(mazeTwo, startTwo, endTwo) );
    }

    @Test 
	public void doublingTest () {
        int x = 600;
        List <Coordinate> list = new ArrayList<>();
        final int [][]maze = new int [x][x];
        for (int i = 0; i < x; i ++) {
            for (int j = 0; j < x; j ++) {
                if (i == 0) {
                    maze[i][j] = 0;
                    list.add(new Coordinate(i, j) );
                }
                else {
                    maze[i][j] = 1;
                }
            }
        }
        final Coordinate start = new Coordinate(0 , 0);
        final Coordinate end = new Coordinate(0, x-1);
        assertEquals(list , GraphsAndMazes.searchMaze(maze, start, end) );
    }
    
}
