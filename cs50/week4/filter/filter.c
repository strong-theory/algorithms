#include "helpers.h"
#include <math.h>

void grayPixel(RGBTRIPLE *pixel);

void sepiaPixel(RGBTRIPLE *pixel);

void blurPixel(int height, int width, RGBTRIPLE *pixel, RGBTRIPLE copy[height][width], int i,
               int j);

// Convert image to grayscale
void grayscale(int height, int width, RGBTRIPLE image[height][width])
{
    // Loop over all pixels
    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < width; j++)
        {
            grayPixel(&image[i][j]);
        }
    }
}

// Convert image to sepia
void sepia(int height, int width, RGBTRIPLE image[height][width])
{
    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < width; j++)
        {
            sepiaPixel(&image[i][j]);
        }
    }
}

// Reflect image horizontally
void reflect(int height, int width, RGBTRIPLE image[height][width])
{
    int middleW = width / 2;
    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < middleW; j++)
        {
            int red = image[i][j].rgbtRed;
            int green = image[i][j].rgbtGreen;
            int blue = image[i][j].rgbtBlue;
            int redOpposite = image[i][width - 1 - j].rgbtRed;
            int greenOpposite = image[i][width - 1 - j].rgbtGreen;
            int blueOpposite = image[i][width - 1 - j].rgbtBlue;

            image[i][j].rgbtRed = redOpposite;
            image[i][j].rgbtGreen = greenOpposite;
            image[i][j].rgbtBlue = blueOpposite;

            image[i][width - 1 - j].rgbtRed = red;
            image[i][width - 1 - j].rgbtGreen = green;
            image[i][width - 1 - j].rgbtBlue = blue;
        }
    }
}

// Blur image
void blur(int height, int width, RGBTRIPLE image[height][width])
{
    RGBTRIPLE copy[height][width];
    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < width; j++)
        {
            copy[i][j] = image[i][j];
        }
    }

    for (int i = 0; i < height; i++)
    {
        for (int j = 0; j < width; j++)
        {
            blurPixel(height, width, &image[i][j], copy, i, j);
        }
    }
}

void grayPixel(RGBTRIPLE *pixel)
{
    int red = pixel->rgbtRed;
    int green = pixel->rgbtGreen;
    int blue = pixel->rgbtBlue;

    // calculate the sum
    double sum = red + green + blue;

    // calulate the average
    int average = (int) round(sum / 3.0);
    pixel->rgbtRed = average;
    pixel->rgbtGreen = average;
    pixel->rgbtBlue = average;
}

void sepiaPixel(RGBTRIPLE *pixel)
{
    float red = pixel->rgbtRed;
    float green = pixel->rgbtGreen;
    float blue = pixel->rgbtBlue;

    int sepiaRed = (int) round((.393 * red) + (.769 * green) + (.189 * blue));
    if (sepiaRed > 255)
    {
        sepiaRed = 255;
    }

    int sepiaGreen = (int) round((.349 * red) + (.686 * green) + (.168 * blue));
    if (sepiaGreen > 255)
    {
        sepiaGreen = 255;
    }

    int sepiaBlue = (int) round((.272 * red) + (.534 * green) + (.131 * blue));
    if (sepiaBlue > 255)
    {
        sepiaBlue = 255;
    }

    pixel->rgbtRed = sepiaRed;
    pixel->rgbtGreen = sepiaGreen;
    pixel->rgbtBlue = sepiaBlue;
}

void blurPixel(int height, int width, RGBTRIPLE *pixel, RGBTRIPLE copy[height][width], int i, int j)
{
    double sumR = 0.0;
    double sumG = 0.0;
    double sumB = 0.0;
    double elements = 0.0;
    for (int x = -1; x <= 1; x++)
    {
        for (int y = -1; y <= 1; y++)
        {
            if ((x + j) >= 0 && (x + j) < width && (y + i) >= 0 && (y + i) < height)
            {
                sumR += copy[y + i][x + j].rgbtRed;
                sumG += copy[y + i][x + j].rgbtGreen;
                sumB += copy[y + i][x + j].rgbtBlue;
                elements += 1.0;
            }
        }
    }

    pixel->rgbtRed = (int) round(sumR / elements);
    pixel->rgbtGreen = (int) round(sumG / elements);
    pixel->rgbtBlue = (int) round(sumB / elements);
}
