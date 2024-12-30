#include <stdio.h>
#include <stdlib.h>

void print_array(int array[], int size);
void quick_sort(int array[], int low, int high);
int partition(int array[], int low, int high);

int main(void)
{
    int size = 13;
    int array[] = {10, 2, 4, 11, 1, 12, 7, 5, 9, 0, 3, 8 ,6 };
    
    print_array(array, size);

    quick_sort(array, 0, size - 1);

    print_array(array, size);

    return 0;
}

void print_array(int array[], int size)
{
    for (int i = 0; i < size; i++)
    {
        printf("%i ", array[i]);
    }
    printf("\n");
}

void quick_sort(int array[], int low, int high)
{
    if (low < high)
    {
        int pi = partition(array, low, high);

        quick_sort(array, low, pi - 1);
        quick_sort(array, pi + 1, high);
    }
}

int partition(int array[], int low, int high)
{
    int pivot = array[high];
    int i = (low - 1);

    for (int j = low; j < high; j++)
    {
        if (array[j] < pivot)
        {
            i++;
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    int temp = array[i + 1];
    array[i + 1] = array[high];
    array[high] = temp;

    return (i + 1);
}
