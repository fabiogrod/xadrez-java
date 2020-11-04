package xadrez;

import tabuleiro.aula_156_Peca;
import tabuleiro.aula_156_Tabuleiro;

public class aula_156_PecaXadrez extends aula_156_Peca
{
	private aula_151_Cor cor;

	public aula_156_PecaXadrez() { super(); }
	
	public aula_156_PecaXadrez(aula_156_Tabuleiro tabuleiro, aula_151_Cor cor)
	{
		super(tabuleiro);
		this.cor = cor;
	}

	public aula_151_Cor getCor() {
		return cor;
	}	
}
