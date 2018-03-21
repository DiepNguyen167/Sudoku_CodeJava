/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author rubynam
 */
import java.io.*;
import java.util.*;


import java.io.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.text.*;
/**
*The SudokuGenerator class creates a random standard (9x9)
*Sudoku board through the use of permutation techniques.
*/
public class SudokuGenerator
{
    public static final int n = 3;
    public static Writer fileOut;	
    private static ArrayList<Block> boardS;
    private ArrayList<int[]> arr;
    private ArrayList<Block> tmp_board;
    
    public SudokuGenerator() {
        boardS = new ArrayList<>();
   	arr = new ArrayList<>();
        int[] tmp = {0,1,2};
        arr.add(tmp);
        int[] tmp1 = {0,2,1} ;
        arr.add(tmp1);
        int[]tmp2 = {1,0,2};
        arr.add(tmp2);
        int[] tmp3 = {1,2,0};
        arr.add(tmp3);
        int[] tmp4 = {2,0,1};
        arr.add(tmp4);
        int[] tmp5 = {2,1,0};
        arr.add(tmp5);
        tmp_board = Block.generateBlock(arr);
        int[] a = new int[9];
        a =   block9(tmp_board.get(9));
        create(tmp_board,a);
   }
    private int[] block9(Block block){
        int[] tmp = new int[9];
        int count =0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                tmp[count] =block.block[i][j];
                count++;
            }
        }
        return tmp;
    }
    public void create(ArrayList<Block> board,int[] demo){
        for(int start =0; start<9;start+=1){
            boardS.add(Base3toDec(board.get(start),start,demo));
        }
        
    }
    public Block Base3toDec(Block myblock, int a,int[] demo){
       int[][] term = new int[3][3]; 
       for(int i=0;i<3;i++){
            term[i][0]= demo[a]*n+myblock.block[i][0]+1;
            term[i][1]= demo[a]*n+myblock.block[i][1]+1;
            term[i][2]= demo[a]*n+myblock.block[i][2]+1;
       }
       Block _block = new Block();
       _block.block=term;
       return _block;
    }
    private int[] convertListtoArray(ArrayList<Block> arrblock){
        int[] gird = new int[81];
        for(int i=0;i<81;i++){
            //int x=i/9;
            //int y = i%9;
            int level =(((i/9)/3)*3+(i%9)/3);
            int col =(((i%9)%3+(i/9)*3))%9;
            int row = col/3;
            gird[i] = arrblock.get(level).getElementBlock(col%3,row);
        }
        return gird;
    }
    private void change(int[] grid,int start, int des){
        int[] tmp = new int[9];
        for(int i=0;i<9;i++){
            tmp[i]=grid[i+start*9];
            grid[i+start*9] = grid[i+des*9];
            grid[i+des*9] = tmp[i];
        }
    }
    public void generatingSudoku(String output1, int number_blanks)
    {
        LinkedList<Integer> randList = new LinkedList<>();
        int[] grid = new int[81];
        grid = convertListtoArray(boardS);
        change(grid,1,3);
        change(grid,2,6);
        change(grid,5,7);
        for(int i=0;i<81;i++){
            randList.add(i);
        }
        for(int i=0;i<number_blanks;i++){
            grid[randList.remove(new Random().nextInt(81-i))]=0;
        }
        
        printPuzzle(output1,grid);
    }
    private void printPuzzle(String file,int[] grid){
        try{
            FileWriter Output = new FileWriter(file);
            for(int i=0;i<81;i++){
                Output.write(String.valueOf(grid[i])+ " ");
                if(i!=0 && i%9==8)
                    Output.write(System.lineSeparator());
            }
            Output.close();
        }catch(IOException e){
        
        }
    }
	
    public static void writePuzzle(ArrayList<Block> boardS, String output_name)
    {
        try{
            fileOut = new FileWriter(output_name); 
            for(int i = 0;i<81;i++){
                int id =((i/9)/3)*3+(i%9)/3;
                int x =(((i%9)%3+(i/9)*3))%9;
                int y = (i%9)%3;
                fileOut.write(String.valueOf(boardS.get(id).getElementBlock(x/3,y))+" ");
                if(i!=0 && i%9==8)
                    fileOut.write(System.lineSeparator());
            
            }
            fileOut.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
                  
    }

    public static void main(String[] args)
    {
        SudokuGenerator sg = new SudokuGenerator();
        sg.generatingSudoku(args[0], Integer.parseInt(args[1]));
        //sg.writePuzzle(boardS, "test.txt");
        //sg.generatingSudoku("puzzle.txt", 40);
        
    }
}
//SudokuGenerator ex = new SudokuGenerator();
        //ex.readFle("puzzle.txt");
        //System.out.println(Integer.parseInt("50"));
        /*
       for(int i=0;i<81;i++){
           //int me =(((i/9)/3)*3+(i%9)/3);
           int me =(i%9)%3;
           System.out.print(me);
       }
        System.out.println();
       for(int i=0;i<81;i++){
           int id=(((i%9)%3+(i/9)*3))%9;
           System.out.print(id/3);
       }
       */
       