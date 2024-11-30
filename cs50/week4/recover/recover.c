#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void filename(char *str, int i);

bool is_jpg(uint8_t buffer[512]);

int last_jpg_index(uint8_t buffer[512]);

int main(int argc, char *argv[])
{
    // Accept a single command-line argument
    if (argc != 2)
    {
        printf("Usage: ./recover FILE\n");
        return 1;
    }

    // Open the memory card
    FILE *card = fopen(argv[1], "r");
    if (card == NULL)
    {
        printf("Could not open file.\n");
        return 1;
    }

    // Create a buffer for a block of data
    uint8_t buffer[512];

    int totalImages = 0;
    int totalBlocks = 0;

    int read = fread(&buffer, 1, 512, card);

    FILE *output = NULL;
    bool is_open = false;
    while (read > 0)
    {
        if (is_jpg(buffer))
        {
            // fecha o arquivo anterior
            if (is_open)
            {
                fclose(output);
                is_open = false;
                output = NULL;
                totalImages++;
            }

            // cria novo arquivo.
            if (output != NULL)
            {
                is_open = false;
                output = NULL;
            }
            else
            {
                // abre o arquivo
                char fileName[8];
                filename(fileName, totalImages);
                output = fopen(fileName, "a");
                is_open = true;
            }
        }

        if (is_open)
        {
            // Escrevendo x bytes no arquivo
            fwrite(&buffer, sizeof(int8_t), read, output);
        }
        read = fread(&buffer, 1, 512, card);
        totalBlocks++;
    }

    // fecha o arquivo anterior
    if (output != NULL)
    {
        fclose(output);
    }

    fclose(card);
    return 0;
}

/*
 * Create the file name from the index.
 */
void filename(char *str, int i)
{
    sprintf(str, "%03i.jpg", i);
}

/*
 * Verifica se o block é inicio de uma jpg.
 */
bool is_jpg(uint8_t buffer[512])
{
    int last_byte = buffer[3] >> 4;
    return buffer[0] == 0xff && buffer[1] == 0xd8 && buffer[2] == 0xff && last_byte == 14;
}
