#include <cs50.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int is_letter(char ch);

int only_digits(string s);

int is_digit(char ch);

string crypt(string plain, int key);

char next(char ch, int skip);

char previous(char ch, int skip);

int main(int argc, string argv[])
{
    if (argc != 2 || only_digits(argv[1]) == 0)
    {
        printf("Usage: ./caesar key\n");
        return 1;
    }

    int key = atoi(argv[1]);

    string plain = get_string("plaintext:  ");
    string cipher = crypt(plain, key);

    printf("ciphertext: %s\n", plain);
}

// verify if the string has only digits
int only_digits(string s)
{
    int len = strlen(s);
    for (int i = 0; i < len; i++)
    {
        if (is_digit(s[i]) == 0)
        {
            return 0;
        }
    }
    return 1;
}

// verfiy if the char is a digit
int is_digit(char ch)
{
    if ((ch >= '0' && ch <= '9') || ch == '-')
    {
        return 1;
    }
    return 0;
}

// verify if the char is a letter
int is_letter(char ch)
{
    if (ch >= 'a' && ch <= 'z')
    {
        return 1;
    }
    if (ch >= 'A' && ch <= 'Z')
    {
        return 1;
    }
    return 0;
}

// crypt the message
string crypt(string plain, int key)
{
    int skip = key % 26;
    int len = strlen(plain);

    int is_positive = 1;
    if (skip < 0)
    {
        is_positive = 0;
        skip *= -1;
    }

    for (int i = 0; i < len; i++)
    {
        char ch = plain[i];
        if (is_positive)
        {
            plain[i] = next(ch, skip);
        }
        else
        {
            plain[i] = previous(ch, skip);
        }
    }
    return plain;
}

// choose the next char
char next(char ch, int skip)
{
    if (is_letter(ch) == 0)
    {
        return ch;
    }

    if (skip == 0)
    {
        return ch;
    }

    skip--;
    if (ch == 'z')
    {
        return next('a', skip);
    }

    if (ch == 'Z')
    {
        return next('A', skip);
    }
    return next(ch + 1, skip);
}

// chose the previus char
char previous(char ch, int skip)
{
    if (is_letter(ch) == 0)
    {
        return ch;
    }

    skip--;
    if (skip == 0)
    {
        return ch;
    }

    if (ch == 'a')
    {
        return next('z', skip);
    }

    if (ch == 'A')
    {
        return next('Z', skip);
    }
    return next(ch - 1, skip);
}
