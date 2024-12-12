#include <stdio.h>
#include <string.h>

typedef char* (* speak) ();

typedef struct
{
	speak speak;

} Animal;

char* meow()
{
	return "meow";
}

char* bark()
{
	return "bark bark";
}

int main ()
{
	Animal cat;	
    cat.speak = &meow;
    printf("Cat: %s\n", cat.speak());

    Animal dog;
    dog.speak = &bark;
    printf("Dog: %s\n", dog.speak());

 	return 0;
}