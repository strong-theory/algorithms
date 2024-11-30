#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <inttypes.h>

#define BYTE uint8_t

int main(void)
{
    FILE* f = fopen("courtyard.bmp", "r");
    if (f == NULL)
    {
        printf("Could not open file.\n");
        return 1;
    }

    BYTE b;
    while (fread(&b, sizeof(BYTE), 1, f) != 0)
    {
        printf("%i", b);
    }

    fclose(f);
}