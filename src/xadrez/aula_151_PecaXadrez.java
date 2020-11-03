package xadrez;

import tabuleiro.aula_151_Peca;
import tabuleiro.aula_153_Tabuleiro;

public class aula_151_PecaXadrez extends aula_151_Peca
{
	private aula_151_Cor cor;

	public aula_151_PecaXadrez() { super(); }
	
	public aula_151_PecaXadrez(aula_153_Tabuleiro tabuleiro, aula_151_Cor cor)
	{
		super(tabuleiro);
		this.cor = cor;
	}

	public aula_151_Cor getCor() {
		return cor;
	}	
}
