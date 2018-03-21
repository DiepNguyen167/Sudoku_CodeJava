

import java.io.*;
import java.util.*;

public class Block {
   int [][] block;
   
   public static final int n=3;
   
   ArrayList<Block> p;
   
   public Block(){
       block = new int[n][n];
       for(int i=0;i<n;i++){
           for(int j=0;j<n;j++){
               block[i][j]=7;
           }
       }
       
   }
   
   public void setElementBlock(int i, int j, int val){
       this.block[i][j] = val;
   }
   public int getElementBlock(int i, int j){
       return this.block[i][j];
   }
   
   
    public static ArrayList<Block> generateBlock(ArrayList<int[]> a)
    {  
        ArrayList<Block> p = new ArrayList<>();
        Block[] myblock = new Block[10];
        for(int i=0;i<10;i++){
            myblock[i] = new Block();
            p.add(generationBlock(a,myblock[i],new Random().nextInt(5)));
        }
       
        return p;
    }
    private static Block generationBlock(ArrayList<int[]> a, Block block,int rand){
        int[] tmp = a.get(rand);
        block.block[0] = tmp;
        block.block[1] = recline2(a,block,new Random().nextInt(5));
        block.block[2] = recline3(a,block,0);
        return block;
    }
    private static int[] recline2(ArrayList<int[]> a,Block block, int rand){
        int[] tmp = a.get(rand);
        for(int i =0;i<3;i++){
            if(!block.checkCol(tmp[i], i))
                return recline2(a,block, new Random().nextInt(5));
        }
        return tmp;
    }
    private static int[] recline3(ArrayList<int[]> a,Block block,int i){
        int[] tmp =a.get(i);
        for(int j=0;j<3;j++){
            if(!block.checkCol(tmp[j], j))
                return recline3(a,block,i+1);
        }
        return tmp;
    }
    public boolean checkRow(int id,int i){
       for(int j=0;j<3;j++){
           if(block[i][j]==id)
               return false;
       }
       return true;   
   }
    public boolean checkCol(int id,int j){
       for(int i=0;i<3;i++){
           if(block[i][j]==id)
               return false;
       }
       return true;
   }
   
}
