package xadrez;

import tabuleiro.aula_157_Peca;
import tabuleiro.aula_156_Tabuleiro;

public abstract class aula_157_PecaXadrez extends aula_157_Peca
{
	private aula_151_Cor cor;

	public aula_157_PecaXadrez() { super(); }
	
	public aula_157_PecaXadrez(aula_156_Tabuleiro tabuleiro, aula_151_Cor cor)
	{
		super(tabuleiro);
		this.cor = cor;
	}

	public aula_151_Cor getCor() {
		return cor;
	}	
}
