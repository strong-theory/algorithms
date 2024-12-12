import csv
import sys


def main():

    if (len(sys.argv) != 3):
        print("Error. Inform the the .csv for the database and the .txt for the sequence.")
        exit()

    # Read database file into a variable
    database_name = sys.argv[1]
    database = read_database(database_name)

    # Read DNA sequence file into a variable
    sequence_name = sys.argv[2]
    sequence = read_sequence(sequence_name)

    # Find longest match of each STR in DNA sequence

    possible_str = load_strs(database)

    for s in possible_str:
        total = longest_match(sequence, s)
        possible_str[s] = total

    # Check database for matching profiles
    for p in database:
        if (match(p, possible_str)):
            print(p['name'])
            break
    else:
        print("No match")
    return


def match(person, possible_strs):
    for s in possible_strs:
        if int(person[s]) != int(possible_strs[s]):
            return False

    return True


def load_strs(database):
    possible_str = {}
    for k in database[0].keys():
        if (k != 'name'):
            possible_str[k] = 0
    return possible_str


def read_database(file_name):
    rows = []
    with open(file_name) as file:
        reader = csv.DictReader(file)
        for row in reader:
            rows.append(row)
    return rows


def read_sequence(file_name):
    with open(file_name) as file:
        return file.read()


def longest_match(sequence, subsequence):
    """Returns length of longest run of subsequence in sequence."""

    # Initialize variables
    longest_run = 0
    subsequence_length = len(subsequence)
    sequence_length = len(sequence)

    # Check each character in sequence for most consecutive runs of subsequence
    for i in range(sequence_length):

        # Initialize count of consecutive runs
        count = 0

        # Check for a subsequence match in a "substring" (a subset of characters) within sequence
        # If a match, move substring to next potential match in sequence
        # Continue moving substring and checking for matches until out of consecutive matches
        while True:

            # Adjust substring start and end
            start = i + count * subsequence_length
            end = start + subsequence_length

            # If there is a match in the substring
            if sequence[start:end] == subsequence:
                count += 1

            # If there is no match in the substring
            else:
                break

        # Update most consecutive matches found
        longest_run = max(longest_run, count)

    # After checking for runs at each character in seqeuence, return longest run found
    return longest_run


main()
