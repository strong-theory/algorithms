#include <cs50.h>
#include <stdio.h>
#include <string.h>

// Max voters and candidates
#define MAX_VOTERS 100
#define MAX_CANDIDATES 9

// Candidates have name, vote count, eliminated status
typedef struct
{
    string name;
    int votes;
    bool eliminated;
} candidate;

// Array of candidates
candidate candidates[MAX_CANDIDATES];

// preferences[i][j] is jth preference for voter i
int preferences[MAX_VOTERS][MAX_CANDIDATES];

// Numbers of voters and candidates
int voter_count;
int candidate_count;

bool vote(int voter, int rank, string name);

int get_candidate_index(string candidate_name);

void tabulate(void);

int find_min(void);

bool is_tie(int min);

void eliminate(int min);

int main(int argc, string argv[])
{

    candidate_count = argc - 1;

    if (candidate_count > MAX_CANDIDATES)
    {
        printf("Maximum number of candidates is %i\n", MAX_CANDIDATES);
        return 2;
    }

    // fill the candidates array
    for (int i = 0; i < candidate_count; i++)
    {
        candidates[i].name = argv[i + 1];
        candidates[i].votes = 0;
        candidates[i].eliminated = false;
    }

    voter_count = get_int("Number of voters: ");
    for (int i = 0; i < voter_count; i++)
    {
        for (int j = 0; j < candidate_count; j++)
        {
            string name_of;
            bool validVote;
            do
            {
                name_of = get_string("Rank %i: ", (j + 1));
                validVote = vote(i, j, name_of);
                if (!validVote)
                {
                    printf("Invalid vote.\n");
                }
            }
            while (!validVote);
        }
    }

    tabulate();

    return 0;
}

bool vote(int voter, int rank, string name)
{
    int index = get_candidate_index(name);
    if (index < 0)
    {
        printf("Not found\n");
        return false;
    }
    preferences[voter][rank] = index;

    return true;
}

// return the index of the candidate by name
int get_candidate_index(string candidate_name)
{
    for (int i = 0; i < candidate_count; i++)
    {
        if (strcmp(candidate_name, candidates[i].name) == 0)
        {
            return i;
        }
    }
    return -1;
}

void tabulate(void)
{
    for (int i = 0; i < voter_count; i++)
    {
        for (int j = 0; j < candidate_count; j++)
        {
            int indexCandidate = preferences[i][j];
            if (!candidates[indexCandidate].eliminated)
            {
                candidates[indexCandidate].votes++;
                break;
            }
        }
    }
}

int find_min(void)
{
    int min_votes = MAX_VOTERS;
    for (int i = 0; i < candidate_count; i++)
    {
        candidate c = candidates[i];
        if (!c.eliminated && c.votes < min_votes)
        {
            min_votes = c.votes;
        }
    }
    return min_votes;
}

bool is_tie(int min)
{
    for (int i = 0; i < candidate_count; i++)
    {
        candidate c = candidates[i];
        if (!c.eliminated && c.votes != min)
        {
            return false;
        }
    }
    return true;
}

void eliminate(int min)
{
    for (int i = 0; i < candidate_count; i++)
    {
        candidate c = candidates[i];
        if (!c.eliminated && c.votes == min)
        {
            candidates[i].eliminated = true;
        }
    }
}

bool print_winner(void)
{
    int half_votes = voter_count / 2;
    for (int i = 0; i < candidate_count; i++)
    {
        candidate c = candidates[i];
        if (!c.eliminated && c.votes > half_votes)
        {
            printf("%s\n", c.name);
            return true;
        }
    }

    return false;
}
