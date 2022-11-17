package week05;

// 陣列複製練習
public class week05_6 {
    public static void main(String args[]){
        int[] arr1={11,21,32,44,35,66,17,38};
        int[] arr2=new int[8];
        System.arraycopy(arr1,0,arr2,0,arr1.length);

        for(int i=0;i<arr2.length;i++){
            System.out.print(arr2[i]+" ");
        }
    }
}
