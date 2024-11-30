#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

bool is_jpg(uint8_t buffer[512], int i);

int main()
{
    uint8_t num = 0xef;

    printf("%i\n", num);

    uint8_t shift = num >> 4;

    printf("%i\n", shift);
}
