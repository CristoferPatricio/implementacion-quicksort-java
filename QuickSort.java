import java.util.Arrays;
import java.util.Random;

/**
 * Alumno: Cristofer Patricio
 * Carnet: 202500553
 * Universidad: Universidad Da Vinci de Guatemala
 * Facultad: Ingeniería en Sistemas
 * * Implementación de QuickSort.
 * Utiliza el último elemento como pivote para la partición.
 */
public class QuickSort {

    // Variables globales para llevar el conteo durante la recursión
    private static int partitionsCount = 0;
    private static int comparisonsCount = 0;
    private static int swapsCount = 0;

    /**
     * Método principal que inicia el ordenamiento QuickSort.
     * @param arr Arreglo a ordenar
     * @return Estadísticas de ejecución
     */
    public static Stats sort(int[] arr) {
        // Reiniciar contadores para cada ejecución
        partitionsCount = 0;
        comparisonsCount = 0;
        swapsCount = 0;

        quickSort(arr, 0, arr.length - 1);

        return new Stats(partitionsCount, comparisonsCount, swapsCount);
    }

    /**
     * Método recursivo principal de QuickSort.
     */
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            partitionsCount++; // Contamos cada vez que se divide el arreglo
            
            // pi es el índice de partición
            int pi = partition(arr, low, high);

            // Ordenar los elementos antes y después de la partición
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    /**
     * Toma el último elemento como pivote, lo coloca en su posición correcta
     * y coloca los menores a la izquierda y los mayores a la derecha.
     */
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // Índice del elemento más pequeño

        for (int j = low; j < high; j++) {
            comparisonsCount++;
            // Si el elemento actual es menor o igual al pivote
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        // Intercambiar el elemento pivote a su posición correcta
        swap(arr, i + 1, high);
        return i + 1;
    }

    /**
     * Método auxiliar para intercambiar dos elementos en el arreglo.
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        swapsCount++;
    }

    /**
     * Clase simple para almacenar estadísticas de una ejecución.
     */
    public static class Stats {
        public int partitions;
        public int comparisons;
        public int swaps;

        public Stats(int partitions, int comparisons, int swaps) {
            this.partitions = partitions;
            this.comparisons = comparisons;
            this.swaps = swaps;
        }
    }

    /**
     * Método de prueba: genera arreglos aleatorios y ordena.
     * Imprime estadísticas en formato CSV.
     */
    public static void main(String[] args) {
        Random rand = new Random();
        System.out.println("tamaño,particiones,comparaciones,intercambios");

        for (int size = 10; size <= 200; size += 10) {
            int[] arr = new int[size];
            for (int i = 0; i < size; i++) {
                arr[i] = rand.nextInt(1000);
            }

            int[] copy = Arrays.copyOf(arr, arr.length);
            Stats stats = sort(copy);

            System.out.printf("%d,%d,%d,%d%n", size, stats.partitions, stats.comparisons, stats.swaps);
        }
    }
}