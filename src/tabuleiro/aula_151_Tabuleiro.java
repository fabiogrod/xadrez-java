package tabuleiro;

public class aula_151_Tabuleiro
{
	private int linhas;
	private int colunas;
	private aula_151_Peca[][] pecas;
	
	public aula_151_Tabuleiro(int linhas, int colunas) 
	{
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new aula_151_Peca[linhas][colunas]; 
	}
	
	public int getLinhas() {
		return linhas;
	}

	public void setLinhas(int linhas) {
		this.linhas = linhas;
	}

	public int getColunas() {
		return colunas;
	}

	public void setColunas(int colunas) {
		this.colunas = colunas;
	}

	public aula_151_Peca peca(int linha, int coluna)
	{
		return pecas[linha][coluna];
	}
	
	public aula_151_Peca peca(aula_149_Posicao posicao)
	{
		return pecas[posicao.getLinha() ][posicao.getColuna() ];
	}
}
