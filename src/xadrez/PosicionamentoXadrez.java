package xadrez;

import tabuleiro.Posicao;

public class PosicionamentoXadrez
{
	private char coluna;
	private int linha;
	
	public PosicionamentoXadrez(char coluna, int linha)
	{
		if  (coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8)
		{
			throw new ExcecaoXadrez("Erro ao instanciar: Valores válidos são de A1 a H8");
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
	
	protected Posicao convertePosicao()
	{
		return new Posicao(8 - linha, coluna - 'a');
	}
	
	protected static PosicionamentoXadrez converteMatriz(Posicao posicao)
	{
		return new PosicionamentoXadrez( (char)('a' + posicao.getColuna()) , 8 -  posicao.getLinha() );
	}
	
	@Override
	public String toString()
	{
		return "" + coluna + linha;
	}
}
