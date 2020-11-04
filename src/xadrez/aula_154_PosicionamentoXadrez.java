package xadrez;

import tabuleiro.aula_149_Posicao;

public class aula_154_PosicionamentoXadrez
{
	private char coluna;
	private int linha;
	
	public aula_154_PosicionamentoXadrez(char coluna, int linha)
	{
		if  (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8)
		{
			throw new aula_156_ExcecaoXadrez("Erro ao instanciar: Valores válidos são de A1 a H8");
		}
		
		this.coluna = coluna;
		this.linha = linha;
	}

	public char getColuna() {
		return coluna;
	}

	public int getLinha() {
		return linha;
	}
	
	protected aula_149_Posicao convertePosicao()
	{
		return new aula_149_Posicao(8 - linha, coluna - 'a');
	}
	
	protected aula_154_PosicionamentoXadrez converteMatriz(aula_149_Posicao posicao)
	{
		return new aula_154_PosicionamentoXadrez( (char)('a' - posicao.getColuna()) , 8 -  posicao.getLinha() );
	}
	
	@Override
	public String toString()
	{
		return "" + coluna + linha;
	}
}
