package xadrez;

import tabuleiro.aula_156_Tabuleiro;
import tabuleiro.aula_157_Peca;
import tabuleiro.aula_159_Posicao;

public abstract class aula_164_PecaXadrez extends aula_157_Peca
{
	private aula_151_Cor cor;

	public aula_164_PecaXadrez() { super(); }
	
	public aula_164_PecaXadrez(aula_156_Tabuleiro tabuleiro, aula_151_Cor cor)
	{
		super(tabuleiro);
		this.cor = cor;
	}

	public aula_151_Cor getCor() {
		return cor;
	}
	
	public aula_154_PosicionamentoXadrez getPosicionamentoXadrez()
	{
		return aula_154_PosicionamentoXadrez.converteMatriz(posicao);
	}
	
	protected boolean verificaPecaOponente(aula_159_Posicao posicao)
	{
		aula_164_PecaXadrez p = (aula_164_PecaXadrez)getTabuleiro().peca(posicao);
		
		return p != null && p.getCor() != cor;		
	}
}
