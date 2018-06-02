public class Main {

    public static void main(String[] args){
        int arraysCounter = 0;
        //current stroke contains 6 arrays
        int[] array = {0, 1, 1 , 0, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0};

        if(array[0] != 0)
        {
            arraysCounter++;
        }

        for(int i=1;i<array.length; i++)
        {
            if(array[i] != 0 && array[i-1] == 0)
            {
                arraysCounter++;
            }
        }
        System.out.println("Arrays Count : " + arraysCounter);
    }
}
