using System;
using System.Data;

namespace Simplex
{
    internal class SimplexService
    {

        private Double[][] table { set; get; }

        private DataTable tab;

        private double[] fo;
        private bool _fo = false;

        private string[] listaBase = null;
        private int indexListaBase = 0;

        private double[,] matriz = null;

        private int pivoLin = 0;
        private int pivoCol = 0;

        private bool stop = false;


        private bool comecou = false;

        public SimplexService(DataTable tab) {
            this.tab = tab;
        }

        public void simplex(int linhas, int colunas, bool max)
        {

            matriz = new double[linhas, colunas - 1];
            fo = new double[tab.Columns.Count - 2];

            listaBase = new string[linhas];
            indexListaBase = 0;

            while (!stop)
            {
                for (int i = 0; i < linhas; i++)
                {
                    for (int j = 1; j < colunas; j++)
                    {
                        string aux = tab.Rows[i][j].ToString();
                        matriz[i, j - 1] = Convert.ToDouble(aux);
                        if (max == false)
                        {
                            if (comecou == false)
                            {
                                matriz[0, j - 1] *= -1;

                            }
                        }
                        tab.Rows[i][j] = matriz[i, j - 1];
                        if (_fo == false && j <= colunas - 2)
                        {
                            fo[i] = matriz[0, j - 1];
                        }
                    }
                    comecou = true;
                    _fo = true;
                }
                pivoCol = maiorNeg();
                pivoLin = menorPos();
                arrumarPivo(pivoLin, pivoCol - 1);
                zerarLinha();
                atualizaBase();
                parada();
            }
        }

        private void atualizaBase()
        {
            listaBase[pivoLin - 1] = "x" + (int)(pivoCol);
            for (int i = 1; i < tab.Rows.Count; i++)
            {
                tab.Rows[i][0] = listaBase[i - 1];
            }

        }

        private void parada()
        {
            for (int i = 0; i < tab.Columns.Count - 2; i++)
            {
                if (matriz[0, i] < 0)
                {
                    stop = false;
                    return;
                }
            }
            stop = true;
        }

        private int maiorNeg()
        {
            int coluna = 1;
            double menor = double.MaxValue;

            for (int i = 0; i < tab.Columns.Count - 2; i++)
            {
                if (matriz[0, i] < 0)
                {
                    if (menor > matriz[0, i])
                    {
                        menor = matriz[0, i];
                        coluna = i + 1;
                    }
                }
            }

            return coluna;
        }

        private int menorPos()
        {
            double menor = double.MaxValue;
            int linha = 1;
            double[] vetor = new double[tab.Rows.Count - 1];

            int col = tab.Columns.Count - 2;

            for (int i = 0; i < vetor.Length; i++)
            {
                double res = 0;
                res = matriz[linha, col] / matriz[linha, pivoCol - 1];
                if (res > double.MaxValue)
                {
                    vetor[i] = -1;
                }
                else
                {
                    vetor[i] = res;
                }
                linha++;
            }

            for (int i = 0; i < vetor.Length; i++)
            {
                if (vetor[i] > 0)
                {
                    if (menor > vetor[i])
                    {
                        menor = vetor[i];
                        linha = i + 1;
                    }
                }
            }
            //MessageBox.Show("menor = " + menor);
            return linha;
        }

        private void arrumarPivo(int lin, int col)
        {
            if (matriz[lin, col] != 1)
            {
                double num = matriz[lin, col];
                for (int i = 0; i < tab.Columns.Count - 1; i++)
                {
                    matriz[lin, i] = matriz[lin, i] / num;
                    tab.Rows[lin][i + 1] = matriz[lin, i];
                }
            }
        }

        private void zerarLinha()
        {
            int pivoCol = this.pivoCol - 1;

            for (int i = 0; i < tab.Rows.Count; i++)
            {
                if (i != pivoLin)
                {
                    if (matriz[i, pivoCol] != 0)
                    {
                        double num = matriz[i, pivoCol];
                        num *= -1;
                        for (int j = 0; j < tab.Columns.Count - 1; j++)
                        {
                            matriz[i, j] = matriz[i, j] + (matriz[pivoLin, j] * num);
                            tab.Rows[i][j + 1] = matriz[i, j];
                        }
                    }
                }
            }

        }
    }
}
