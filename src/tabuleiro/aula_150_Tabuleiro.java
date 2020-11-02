package tabuleiro;

public class aula_150_Tabuleiro
{
	private int linhas;
	private int colunas;
	private aula_150_Peca[][] pecas;
	
	public aula_150_Tabuleiro(int linhas, int colunas) 
	{
		this.linhas = linhas;
		this.colunas = colunas;
		pecas = new aula_150_Peca[linhas][colunas]; 
	}
	
	
}
