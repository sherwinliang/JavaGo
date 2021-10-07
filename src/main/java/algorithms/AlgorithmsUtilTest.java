package algorithms;

public class AlgorithmsUtilTest {
    public static void main(String... args){
        Integer[] arr = {(int)(Math.random()*10),(int)(Math.random()*10),(int)(Math.random()*10),(int)(Math.random()*10),(int)(Math.random()*10),(int)(Math.random()*10),(int)(Math.random()*10)};
        AlgorithmsUtil.bubbleSort(arr);
        //AlgorithmsUtil.quickSort(arr,0,arr.length-1);
        //AlgorithmsUtil.selectionSort(arr);
        //AlgorithmsUtil.insertSort(arr);
        //boolean isExisted = AlgorithmsUtil.shiftSearch("Sherwin is coding.","is");
        //System.out.println(isExisted);
        boolean isExisted = AlgorithmsUtil.binarySearch(arr,3);
        System.out.println(isExisted);
        //System.out.println("After sorted: "+ Arrays.toString(AlgorithmsUtil.generateFiboArr(10)));
    }
}
