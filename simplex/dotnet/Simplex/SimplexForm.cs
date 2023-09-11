using System;
using System.Data;
using System.Windows.Forms;

namespace Simplex
{
    public partial class SimplexForm : Form
    {

        private SimplexService service;

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
        public SimplexForm()
        {
            InitializeComponent();
            setComboBox();
            montarTabela();
            teste();

            service = new SimplexService(tab);
            MessageBox.Show("Coloque os valores na forma padrao:\n\r*Adicione as variáveis de folga.\n\r*Passar as VNBs para a esquerda.", "Atenção");
        }

        private void teste()
        {
            tab.Rows[0][1] = -1;
            tab.Rows[0][2] = -1;
            tab.Rows[0][3] = 0;
            tab.Rows[0][4] = 0;
            tab.Rows[0][5] = 0;
            tab.Rows[0][6] = 0;

            tab.Rows[1][1] = -1;
            tab.Rows[1][2] = 1;
            tab.Rows[1][3] = 1;
            tab.Rows[1][4] = 0;
            tab.Rows[1][5] = 0;
            tab.Rows[1][6] = 4;

            tab.Rows[2][1] = 4;
            tab.Rows[2][2] = 5;
            tab.Rows[2][3] = 0;
            tab.Rows[2][4] = 1;
            tab.Rows[2][5] = 0;
            tab.Rows[2][6] = 30;

            tab.Rows[3][1] = 1;
            tab.Rows[3][2] = 0;
            tab.Rows[3][3] = 0;
            tab.Rows[3][4] = 0;
            tab.Rows[3][5] = 1;
            tab.Rows[3][6] = 5;
        }

        private void setComboBox()
        {
            comboBox1.Items.Add("Max");
            comboBox1.Items.Add("Min");
            comboBox1.SelectedIndex = 0;
        }

        private void numericUpDown1_ValueChanged(object sender, EventArgs e)
        {            
            montarTabela();
            stop = false;
        }

        private void numericUpDown2_ValueChanged(object sender, EventArgs e)
        {
            montarTabela();
            stop = false;
        }

        private void montarTabela()
        {
            tab = new DataTable("tableau");
            
            dataGridView1.DataSource = tab;

            int colunas = Convert.ToInt32(numericUpDown1.Value);
            int linhas = Convert.ToInt32(numericUpDown2.Value);
            
            listaBase = new string[linhas];
            indexListaBase = 0;

            tab.Columns.Add("#");
            for (int i = 1; i <= colunas + linhas; i++)
            {                
                if (i > colunas)
                {
                    tab.Columns.Add("x" + i, typeof (double));
                    listaBase[indexListaBase] = ("x" + i);
                    indexListaBase++;
                }
                else
                {                    
                    tab.Columns.Add("x" + i, typeof (double));
                }
            }
            tab.Columns.Add("V.A.", typeof (double));

            tab.Rows.Add();

            tab.Rows[0][0] = "F.O.";

            for (int i = 0; i < linhas; i++)
            {
                tab.Rows.Add("" + listaBase[i]);
            }
            
            dataGridView1.DataSource = tab;
            fo = new double[tab.Columns.Count - 2];
        }

        private void button1_Click(object sender, EventArgs e)
        {

            int linhas = tab.Rows.Count;
            int colunas = tab.Columns.Count;

            matriz = new double[linhas, colunas - 1];

            //MessageBox.Show("" + linhas + " " + colunas);

            try
            {
                if (comboBox1.SelectedIndex == 0)
                {
                    simplex(linhas, colunas, true);
                }
                else
                {
                    simplex(linhas, colunas, false);
                }                
            }
            catch
            {
                msgErro("");
            }

            if (stop == true)
            {
                string resp = "";

                for (int i = 0; i < listaBase.Length; i++)
                {
                    resp += listaBase[i] + " = " + tab.Rows[i + 1][tab.Columns.Count - 1] + "\n\r";
                }

                //MessageBox.Show(resp + "Demais variáveis = 0\n\rSolução ótima = " + tab.Rows[0][tab.Columns.Count - 1], "Resultado");

                stop = false;
                comecou = false;
            }
        }

        private void simplex(int linhas, int colunas, bool max)
        {
            service.simplex(linhas, colunas, max);
        }


        private void msgErro(string msg)
        {
            MessageBox.Show("Erro! Refaça " + msg, "Erro");
        }

        private void button2_Click(object sender, EventArgs e)
        {
            stop = false;
            montarTabela();
        }
    }
}
