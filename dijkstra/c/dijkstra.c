#include <stdio.h>
#include <stdbool.h>

int menorCaminho(int *matCid, int qtde_cidades, int origem, int destino, char *c)
{
    int i, j;
    int noAtual, pesoAtual;
    int *noAnterior = (int*)malloc(sizeof(int)*qtde_cidades);
    int *pesos = (int*)malloc(sizeof(int)*qtde_cidades);
    bool *caminho = (bool*)malloc(sizeof(bool)*qtde_cidades);
	
	*c = (char) (origem + 48);
	c++;
	
    for( i=0;i<qtde_cidades;i++){
        noAnterior[i] = 0;
        pesos[i] = 0;
        caminho[i] = false;
    }
	
    noAtual = origem;
    pesoAtual = 0;
    while(noAtual != destino)
    {		
        caminho[noAtual] = true;
		int indice = 0;					
        for(j=0;j<qtde_cidades;j++){
			indice = (noAtual*qtde_cidades) + j;

            if(matCid[indice] != 0 && (matCid[indice] + pesoAtual < pesos[j] || pesos[j] == 0))
            {
                pesos[j] = matCid[indice] + pesoAtual;
                noAnterior[j] = noAtual;
            }
        }		
        for(j=0;j<qtde_cidades;j++){
            if(caminho[j] == false && pesos[j] != 0 && (pesos[j] < pesos[noAtual] || caminho[noAtual]==true))
            {
                noAtual = j;
                pesoAtual = pesos[noAtual];
            }
        }		
		*c = (char) (noAtual + 48);
		c++;
    }
	*c = '\0';
    return pesoAtual;
}

int main(){
	int qtde_cidades = 4;
	int matriz[4][4] = {{ 0, 3, 0, 1 },
                        { 1, 0, 25, 2 },
                        { 0, 3, 0, 1 },
                        { 3, 0, 10, 0 }};

	system("cls");
	char c[10];
	int origem, destino;
	for(origem = 0; origem < qtde_cidades; origem++){
		for(destino = 0; destino < qtde_cidades; destino++){
			printf("%i %i", origem, destino);
			int dist = menorCaminho(matriz, 4,origem ,destino, c);		
			printf("\nMenor caminho = %i\n", dist);
			int i =0;
			printf("Caminho = ");
			for(i = 0; i < strlen(c); i++){
				printf("%c", c[i]);
			}
			printf("\n*******************\n");	
		}
	}		

	return 0;
}