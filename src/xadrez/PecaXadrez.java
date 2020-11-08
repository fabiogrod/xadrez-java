package xadrez;

import tabuleiro.Tabuleiro;
import tabuleiro.Peca;
import tabuleiro.Posicao;

public abstract class PecaXadrez extends Peca
{
	private Cor cor;
	private int contagemMovimentos = 0;

	public PecaXadrez() { super(); }
	
	public PecaXadrez(Tabuleiro tabuleiro, Cor cor)
	{
		super(tabuleiro);
		this.cor = cor;
	}

	public Cor getCor() {
		return cor;
	}
	
	public int getContagemMovimentos()
	{
		return contagemMovimentos;
	}
	
	public void somarContagemMovimentos()
	{
		contagemMovimentos++;
	}
	
	public void subtrairContagemMovimentos()
	{
		contagemMovimentos--;
	}
	
	public PosicionamentoXadrez getPosicionamentoXadrez()
	{
		return PosicionamentoXadrez.converteMatriz(posicao);
	}
	
	protected boolean verificaPecaOponente(Posicao posicao)
	{
		PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
		
		return p != null && p.getCor() != cor;		
	}
}
