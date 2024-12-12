from cs50 import get_string
import re

text = get_string("Text: ")


def letters_count(text):
    total = 0
    for c in text:
        if (c.isalpha()):
            total += 1
    return total


def count_words(text):
    total = 0
    for c in text:
        if (c == ' '):
            total += 1
    return total + 1


def count_sentences(text):
    total = 0
    end_sentence = ['.', '?', '!']
    for c in text:
        if (c in end_sentence):
            total += 1
    return total


def calculate_index(total_letters, total_words, total_sentences):

    l = (total_letters / total_words) * 100
    s = (total_sentences / total_words) * 100

    return (0.0588 * l) - (0.296 * s) - 15.8


def print_result(index):
    if (index < 1.0):
        print("Before Grade 1")
    elif (index > 16.0):
        print("Grade 16+")
    else:
        print(f"Grade {round(index)}")


total_letters = letters_count(text)

total_words = count_words(text)

total_sentences = count_sentences(text)

index = calculate_index(total_letters, total_words, total_sentences)

print_result(index)
