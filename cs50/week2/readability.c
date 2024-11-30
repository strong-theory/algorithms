#include <cs50.h>
#include <math.h>
#include <stdio.h>
#include <string.h>

void print_result(float index);

float calculate_index(int letters, int words, int sentences);

int count_letters(string s);

int count_words(string s);

int count_sentences(string s);

int is_letter(char ch);

int is_end_sentence(char ch);

int main(void)
{
    string text = get_string("Text: ");

    int letters = count_letters(text);

    int words = count_words(text);

    int sentences = count_sentences(text);

    float index = calculate_index(letters, words, sentences);

    print_result(index);
}

// just prints the result
void print_result(float index)
{
    if (index < 1.0)
    {
        printf("Before Grade 1\n");
    }
    else if (index > 16.0)
    {
        printf("Grade 16+\n");
    }
    else
    {
        printf("Grade %i\n", (int) round(index));
    }
}

// calculate the index with Coleman-Liau formula
float calculate_index(int letters, int words, int sentences)
{
    float l = (letters / (float) words) * 100;

    float s = (sentences / (float) words) * 100;

    return (0.0588 * l) - (0.296 * s) - 15.8;
}

// total letters
int count_letters(string s)
{
    int sum = 0;
    int len = strlen(s);
    for (int i = 0; i < len; i++)
    {
        if (is_letter(s[i]) == 1)
        {
            sum++;
        }
    }
    return sum;
}

// total words
int count_words(string s)
{
    int count = 0;
    int len = strlen(s);
    for (int i = 0; i < len; i++)
    {
        if (s[i] == ' ')
        {
            count++;
        }
    }

    return count + 1;
}

// total sentences
int count_sentences(string s)
{
    int len = strlen(s);
    int sentences = 0;
    for (int i = 0; i < len; i++)
    {
        if (is_end_sentence(s[i]))
        {
            sentences++;
        }
    }
    return sentences;
}

// just check if is the end of a sentence
int is_end_sentence(char ch)
{
    if (ch == '.' || ch == '!' || ch == '?')
    {
        return 1;
    }
    return 0;
}

// just check if the char is a letter
int is_letter(char ch)
{
    if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))
    {
        return 1;
    }
    return 0;
}