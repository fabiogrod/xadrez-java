package tabuleiro;

public class aula_159_Posicao
{
	private int linha;
	private int coluna;
	
	public aula_159_Posicao(){}

	public aula_159_Posicao(int linha, int coluna)
	{
		this.linha = linha;
		this.coluna = coluna;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}
	
	public void setValores(int linha, int coluna)
	{
		this.linha = linha;
		this.coluna = coluna;
	}

	@Override
	public String toString() {
		return "Linha: " + linha + " - Coluna: " + coluna;
	}	
	
}
