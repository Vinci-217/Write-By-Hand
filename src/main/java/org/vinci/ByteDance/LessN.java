package org.vinci.ByteDance;

import java.util.*;
// 数组A中给定可以使用的1~9的数，
// 返回由A数组中的元素组成的小于n的最大数。
// 例如A={1, 2, 4, 9}，n=2533，返回2499
public class LessN {

    static int[] nums = new int[]{1,2,4,9};
    static int n = 2533;
    static List<Integer> ans = new ArrayList<>();
    static StringBuilder path = new StringBuilder();
    static Set<Integer> set = new HashSet<>();


    public static void main(String[] args){
        int res = 0;
        for(int i = 0;i<nums.length;i++){
            dfs(i);
        }
        for(Integer num : ans){
            if(num<n){
                res = Math.max(res,num);
            }
        }
        System.out.println(ans.size());
        System.out.println(res);
    }

    private static void dfs(int length){
        if(length<0) return;
        for(int i = 0;i<nums.length;i++){

            path.append(nums[i]);
            Integer num = Integer.parseInt(path.toString());
            ans.add(num);

            dfs(length-1);

            path.deleteCharAt(path.length()-1);

        }

    }
}
