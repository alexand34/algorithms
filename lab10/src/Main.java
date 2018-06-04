public class Main {
    static void InsertionSort(int arr[])
    {
        int n = arr.length;
        for (int i=1; i<n; ++i)
        {
            int key = arr[i];
            int j = i-1;

            while (j>=0 && arr[j] > key)
            {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
        }
    }

    public static void OddEvenSort(int array[]){
        int n = array.length;
        for (int i = 0; i < n/2; i++ ) {
            for (int j = 0; j+1 < n; j += 2)
                if (array[j] > array[j+1]) {
                    int T = array[j];
                    array[j] = array[j+1];
                    array[j+1] = T;
                }
            for (int j = 1; j+1 < array.length; j += 2)
                if (array[j] > array[j+1]) {
                    int T = array[j];
                    array[j] = array[j+1];
                    array[j+1] = T;
                }
        }
    }

    static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }

    static int partition(int arr[], int low, int high)
    {
        int pivot = arr[high];
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            if (arr[j] <= pivot)
            {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;

        return i+1;
    }

    static int ShellSort(int arr[])
    {
        int n = arr.length;

        for (int gap = n/2; gap > 0; gap /= 2)
        {
            for (int i = gap; i < n; i += 1)
            {
                int temp = arr[i];

                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
                    arr[j] = arr[j - gap];

                arr[j] = temp;
            }
        }
        return 0;
    }

    static void QuickSort(int arr[], int low, int high)
    {
        if (low < high)
        {
            int pi = partition(arr, low, high);

            QuickSort(arr, low, pi-1);
            QuickSort(arr, pi+1, high);
        }
    }

    public static void main(String args[])
    {
        int insertionSortArray[] = {5, 7, 4, 8, 1, 6, 2 };
        int[] quickSortArray =  insertionSortArray.clone();
        int[] shellSortArray = insertionSortArray.clone();
        int[] oddEvenSortArray = insertionSortArray.clone();
        System.out.println("Basic Array");
        printArray(insertionSortArray);

        System.out.println("Insertion sort:");
        InsertionSort(insertionSortArray);
        printArray(insertionSortArray);

        System.out.println("Quick sort:");

        QuickSort(quickSortArray, 0, quickSortArray.length-1);
        printArray(quickSortArray);

        System.out.println("Shell sort:");
        ShellSort(shellSortArray);
        printArray(shellSortArray);

        System.out.println("Odd Even Sort Array:");
        OddEvenSort(oddEvenSortArray);
        printArray(oddEvenSortArray);
    }
}
