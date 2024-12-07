// Implements a dictionary's functionality
#include <ctype.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <strings.h>

#include "dictionary.h"

// Represents a node in a hash table
typedef struct node
{
    char word[LENGTH + 1];
    struct node *next;
} node;

// TODO: Choose number of buckets in hash table
const unsigned int N = 26;

// Hash table
node *table[N];

int total_words = 0;

// Returns true if word is in dictionary, else false
bool check(const char *word)
{
    unsigned int index = hash(word);

    node *n = table[index];

    while (n != NULL)
    {
        if (strcasecmp(n->word, word) == 0)
        {
            return true;
        }
        n = n->next;
    }
    return false;
}

// Hashes word to a number
unsigned int hash(const char *word)
{
    // TODO: Improve this hash function
    return toupper(word[0]) - 'A';
}

// Loads dictionary into memory, returning true if successful, else false
bool load(const char *dictionary)
{
    // Open the dictionary file
    FILE *source = fopen(dictionary, "r");
    if (source == NULL)
    {
        return false;
    }

    char new_word[LENGTH + 1];
    while (fscanf(source, "%s", new_word) != -1)
    {
        int length = strlen(new_word);

        node *new_node = malloc(sizeof(node));

        strcpy(new_node->word, new_word);
        new_node->next = NULL;

        unsigned int index = hash(new_word);

        node *current = table[index];
        if (current == NULL)
        {
            table[index] = new_node;
            total_words++;
        }
        else
        {
            bool exists = check(new_word);
            if (!exists)
            {
                while (strcmp(new_word, current->word) != 0 && current->next != NULL)
                {
                    current = current->next;
                }
                current->next = new_node;
                total_words++;
            }
            else
            {
                free(new_node);
            }
        }
    }

    fclose(source);

    printf("total_words %i\n", total_words);

    return true;
}

// Returns number of words in dictionary if loaded, else 0 if not yet loaded
unsigned int size(void)
{
    return total_words;
}

void unload_node(node *n)
{
    if (n->next != NULL)
    {
        unload_node(n->next);
    }
    free(n);
}

// Unloads dictionary from memory, returning true if successful, else false
bool unload(void)
{
    for (int i = 0; i < N; i++)
    {
        if (table[i] != NULL)
        {
            unload_node(table[i]);
        }
    }
    return true;
}
