package xadrez.pecas;

import tabuleiro.aula_152_Tabuleiro;
import xadrez.aula_151_Cor;
import xadrez.aula_151_PecaXadrez;

public class aula_152_Rei extends aula_151_PecaXadrez
{
	public aula_152_Rei ( aula_152_Tabuleiro tabuleiro, aula_151_Cor cor)
	{
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString()
	{
		return "K";
	}
}
