package org.vinci.dsa;

public class QuickSort {

    public static void main(String[] args){
        int[] arr = {5, 2, 8, 3, 9, 1};
        printArray(arr);
        quickSort(arr,0,arr.length-1);
        printArray(arr);
    }


    private static void printArray(int[] arr){
        for (int n:arr){
            System.out.println(n);
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void quickSort(int[] arr,int low,int high){
        if(low<high){
            int p1 = partition(arr,low,high);
            quickSort(arr,low,p1-1);
            quickSort(arr,p1+1,high);
        }

    }

    private static int partition(int[] arr,int low,int high){
        int pivot = arr[high];
        int i = low;

        for(int j = low;j<high;j++){
            if(arr[j]<pivot){
                swap(arr,i,j);
                i++;
            }
        }
        swap(arr,i,high);
        return i;

    }



}
