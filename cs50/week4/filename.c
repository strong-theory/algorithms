#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

void filename(char * str, int i)
{
    sprintf(str, "%03i.jpg", i);
}

int main()
{
    char str2[8];
    filename(str2, 1);
    printf("%s\n", str2);
}



