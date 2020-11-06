package xadrez.pecas;

import tabuleiro.aula_156_Tabuleiro;
import tabuleiro.aula_159_Posicao;
import xadrez.aula_151_Cor;
import xadrez.aula_167_PecaXadrez;

public class aula_168_Peao extends aula_167_PecaXadrez
{	
	public aula_168_Peao(aula_156_Tabuleiro tabuleiro, aula_151_Cor cor)
	{
		super(tabuleiro, cor);
	}
	
	@Override
	public boolean[][] movimentosPossiveis()
	{
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		aula_159_Posicao peca = new aula_159_Posicao( 0, 0);
		
		if(getCor() == aula_151_Cor.BRANCA)
		{
			peca.setValores(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(peca) && !getTabuleiro().pecaExistente(peca))
			{
				matriz[peca.getLinha()][peca.getColuna()] = true;
			}
			
			peca.setValores(posicao.getLinha() - 2, posicao.getColuna());
			aula_159_Posicao peca2 = new aula_159_Posicao(posicao.getLinha() - 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(peca) && !getTabuleiro().pecaExistente(peca) && getTabuleiro().posicaoExistente(peca2) && !getTabuleiro().pecaExistente(peca2) && getContagemMovimentos() == 0)
			{
				matriz[peca.getLinha()][peca.getColuna()] = true;
			}
			
			peca.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExistente(peca) && verificaPecaOponente(peca))
			{
				matriz[peca.getLinha()][peca.getColuna()] = true;
			}
			
			peca.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExistente(peca) && verificaPecaOponente(peca))
			{
				matriz[peca.getLinha()][peca.getColuna()] = true;
			}
		}
		else 
		{
			peca.setValores(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(peca) && !getTabuleiro().pecaExistente(peca))
			{
				matriz[peca.getLinha()][peca.getColuna()] = true;
			}
			
			peca.setValores(posicao.getLinha() + 2, posicao.getColuna());
			aula_159_Posicao peca2 = new aula_159_Posicao(posicao.getLinha() + 1, posicao.getColuna());
			if (getTabuleiro().posicaoExistente(peca) && !getTabuleiro().pecaExistente(peca) && getTabuleiro().posicaoExistente(peca2) && !getTabuleiro().pecaExistente(peca2) && getContagemMovimentos() == 0)
			{
				matriz[peca.getLinha()][peca.getColuna()] = true;
			}
			
			peca.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
			if (getTabuleiro().posicaoExistente(peca) && verificaPecaOponente(peca))
			{
				matriz[peca.getLinha()][peca.getColuna()] = true;
			}
			
			peca.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
			if (getTabuleiro().posicaoExistente(peca) && verificaPecaOponente(peca))
			{
				matriz[peca.getLinha()][peca.getColuna()] = true;
			}
		}
		
		return matriz;
	}

	@Override
	public String toString()
	{
		return "P";
	}
}
