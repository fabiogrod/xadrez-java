package xadrez.pecas;

import tabuleiro.aula_156_Tabuleiro;
import xadrez.aula_151_Cor;
import xadrez.aula_156_PecaXadrez;

public class aula_156_Rei extends aula_156_PecaXadrez
{
	public aula_156_Rei ( aula_156_Tabuleiro tabuleiro, aula_151_Cor cor)
	{
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString()
	{
		return "K";
	}
}
