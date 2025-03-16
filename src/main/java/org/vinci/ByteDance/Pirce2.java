package org.vinci.ByteDance;

import java.util.Arrays;

public class Pirce2 {

    static int[] nums = new int[]{7,1,5,3,6,4};
    static int[][] memo;
    public static void main(String[] args){
        int n = nums.length;
        memo = new int[n][2];
        for(int[] row : memo) Arrays.fill(row,-1);
        System.out.println(dfs(n-1,0));
    }


    private static int dfs(int i,int j){
        if(i<0){
            return j == 0 ? 0:Integer.MIN_VALUE;
        }
        if(memo[i][j]!=-1) return memo[i][j];
        if(j==0){
            return memo[i][j] = Math.max(dfs(i-1,0),dfs(i-1,1)+nums[i]);
        }
        return memo[i][j] = Math.max(dfs(i-1,1),dfs(i-1,0)-nums[i]);
    }
}
