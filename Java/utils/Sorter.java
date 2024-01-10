package utils;

public class Sorter {
    
    public static void insertion (int[] array) {
        for(int i=1; i<array.length; i++){
            for(int j=i; j>0 && array[j]<array[j-1]; j--){
                swap(array, j, j-1);
            }
        }
    }

    public static void insertion (double[] array) {
        for(int i=1; i<array.length; i++){
            for(int j=i; j>0 && array[j]<array[j-1]; j--){
                swap(array, j, j-1);
            }
        }
    }

    private static int[] swap(int[] set, int loc1, int loc2){
        int temp = set[loc1];
        set[loc1] = set[loc2];
        set[loc2] = temp;

        return set;
    }

    private static double[] swap(double[] set, int loc1, int loc2) {
        double temp = set[loc1];
        set[loc1] = set[loc2];
        set[loc2] = temp;

        return set;
    }
}
