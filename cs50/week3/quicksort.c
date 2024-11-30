#include <stdio.h>
#include <stdlib.h>

void print_array(int array[], int size);

int* quick_sort(int array[], int size);

int* fill_array(int array[], int start, int end);

int* menores(int array[], int size, int pivot, int* new_size);

int* maiores(int array[], int size, int pivot, int* new_size);

int* merge(int menores[], int menores_size, int pivot, int maiores[], int maiores_size);

int main(void)
{
    int size = 13;
    int array[] = {10, 2, 4, 11, 1, 12, 7, 5, 9, 0, 3, 8 ,6 };
    
    print_array(array, size);

    int* sorted = quick_sort(array, size);

    print_array(sorted, size);
}

void print_array(int array[], int size)
{
    for (int i = 0; i < size; i++)
    {
        printf("%i ", array[i]);
    }
    printf("\n");
}

int* quick_sort(int array[], int size)
{
    if (size < 2)
    {
        return array;
    }
    int middle = size / 2;
    int pivot = array[middle];

    int new_size = size - middle;

    int size_menores = 0;
    int* array_menores = menores(array, size, pivot, &size_menores);

    int size_maiores = 0;
    int* array_maiores = maiores(array, size, pivot, &size_maiores);

    int* sorted_menores = quick_sort(array_menores, size_menores);
    int* sorted_maiores = quick_sort(array_maiores, size_maiores);

    return merge(sorted_menores, size_menores, pivot, sorted_maiores, size_maiores);
}

int* menores(int array[], int size, int pivot, int* new_size)
{
    int new_array[size];
    *new_size = 0;
    for (int i = 0; i < size; i++)
    {
        if (array[i] < pivot)
        {
            new_array[*new_size] = array[i];
            *new_size = *new_size + 1;
        }
    }
    return fill_array(new_array, 0, *new_size);
}

int* maiores(int array[], int size, int pivot, int* new_size)
{
    int new_array[size];
    *new_size = 0;
    for (int i = 0; i < size; i++)
    {
        if (array[i] > pivot)
        {
            new_array[*new_size] = array[i];
            *new_size = *new_size + 1;
        }
    }
    return fill_array(new_array, 0, *new_size);
}

int* fill_array(int array[], int start, int end)
{
    const int new_size = end - start;
    int* new_array = malloc(new_size * sizeof(int));
    for (int i = start; i < end; i++)
    {
        new_array[i] = array[i];
    }
    return new_array;
}

int* merge(int menores[], int menores_size, int pivot, int maiores[], int maiores_size) {

    int new_size = menores_size + 1 + maiores_size;
    int* new_array = malloc(new_size * sizeof(int));

    int real_index = 0;
    for(int i = 0; i < menores_size; i++)
    {
        new_array[real_index++] = menores[i];
    }

    new_array[real_index++] = pivot;

    for(int i = 0; i < maiores_size; i++)
    {
        new_array[real_index++] = maiores[i];
    }

    return new_array;
}
