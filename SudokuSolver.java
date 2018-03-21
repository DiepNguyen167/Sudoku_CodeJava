/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.*;
import java.text.*;
/**
 *
 * @author rubynam
 */

public class SudokuSolver {
    private ArrayList<Block> broadS;
    private final int blockSize =3;
    private final int blockNumber =9;
    private final int broadNumber =81;
    public  int[][] grid = new int[9][9];
    
    public boolean readSudoku(String input){
        readFile(input);
        broadS = new ArrayList<Block>();
        return true;
    }
    
    public void readFile(String input){
        try{
        BufferedReader br = new BufferedReader(new FileReader(new File(input)));
        Scanner scan = new Scanner(br);
        Grid_Input(scan);
        scan.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
   private void Grid_Input(Scanner scan){
       for(int i=0;i<blockNumber;i++){
           for(int j=0;j<blockNumber;j++){
               grid[i][j] = scan.nextInt();
           }
       } 
   } 
   public boolean solve(int level){
       if(solve(level,0,grid)){
            output(0,0);
            return true;
        }
        return false;
   }
   
    private boolean solve(int i, int j,int[][] cells){
        if(i==blockNumber){//9
            i=0;
            if(++j==blockNumber){//9
                return true;
            }
        }
            if(cells[i][j]!=0){//default
                return solve(i+1,j,cells);
            }
            for(int val=1;val<=blockNumber;val++){
                if(legal(i,j,val,cells)){
                    cells[i][j] = val;
                    if(solve(i+1,j,cells))
                            return true;
                }
            }
        
        cells[i][j]=0;
        return false;
    }
    public  boolean legal(int i, int j, int val, int[][] cells){
        for(int k =0;k<blockNumber;k++){
            if(val == cells[k][j])
                return false;
        }//cheats cot
        for(int k=0;k<blockNumber;k++){
            if(cells[i][k]==val)
                return false;
        }//cheat dong
        int Row = (i/blockSize)*3;
        int Col = (j/blockSize)*3;
        //cheat block
        for(int k=0;k<blockSize;k++){
            for(int m=0;m<blockSize;m++){
                if(val == cells[Row+k][Col + m])
                    return false;
            }
        }
        return true;
    }
    public void output(int i, int j){
       if(i>=9)
           return;
       Block myblock = new Block();
       for(int a=0;a<3;a++){
           for(int b=0;b<3;b++){
               myblock.setElementBlock(a, b, grid[a+i][b+j]);
           }
       }
       broadS.add(myblock); 
       j+=3;
       if(j==9){
           i+=3;
           j=0;
       }
       output(i,j);   
    }
    public void SudokuSolver(String filename){
        SudokuGenerator.writePuzzle(broadS, filename);
        
    }
    public static void main(String[] args){
         SudokuSolver sol = new SudokuSolver();
         sol.readSudoku(args[0]);
         //sol.readSudoku("puzzle.txt");
         sol.solve(0);
         sol.SudokuSolver(args[1]);
         //sol.SudokuSolver("solution.txt");
    }
}


