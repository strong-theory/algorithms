#include <stdio.h>
#include <stdlib.h>

void print_array(int array[], int size);

int binary_search(int array[], int size, int num);

int* sub_array(int array[], int start, int end);

int main(void)
{
    int size = 10;
    int array[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    print_array(array, size);

    int num;
    scanf("%i", &num);
    int index = binary_search(array, size, num);

    printf("Index of %i is %i\n", num, index);
}

int binary_search(int array[], int size, int num)
{
    int start = 0;
    int end = size;
    while (start <= end) {
        int middle = (end + start) / 2;
        int pivot = array[middle];
        if (pivot == num)
        {
            return middle;
        }

        if (num < pivot)
        {
            end = middle - 1;
        }
        else
        {
            start = middle + 1;
        }

    }
    return -1;
}

void print_array(int array[], int size)
{
    for (int i = 0; i < size; i++)
    {
        printf("%i ", array[i]);
    }
    printf("\n");
}
