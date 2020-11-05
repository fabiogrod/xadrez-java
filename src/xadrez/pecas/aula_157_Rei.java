package xadrez.pecas;

import tabuleiro.aula_156_Tabuleiro;
import xadrez.aula_151_Cor;
import xadrez.aula_159_PecaXadrez;

public class aula_157_Rei extends aula_159_PecaXadrez
{
	public aula_157_Rei ( aula_156_Tabuleiro tabuleiro, aula_151_Cor cor)
	{
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString()
	{
		return "K";
	}

	@Override
	public boolean[][] movimentosPossiveis()
	{	
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		return matriz;
	}	
}
