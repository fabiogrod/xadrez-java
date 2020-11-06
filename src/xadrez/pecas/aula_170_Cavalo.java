package xadrez.pecas;

import tabuleiro.aula_156_Tabuleiro;
import tabuleiro.aula_159_Posicao;
import xadrez.aula_151_Cor;
import xadrez.aula_167_PecaXadrez;

public class aula_170_Cavalo extends aula_167_PecaXadrez
{
	public aula_170_Cavalo ( aula_156_Tabuleiro tabuleiro, aula_151_Cor cor)
	{
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString()
	{
		return "C";
	}
	
	private boolean permiteMovimento(aula_159_Posicao posicao)
	{
		aula_167_PecaXadrez p = (aula_167_PecaXadrez)getTabuleiro().peca(posicao);
		
		return p == null || p.getCor() != getCor();
	}
	
	@Override
	public boolean[][] movimentosPossiveis()
	{	
		boolean[][] matriz = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
		
		aula_159_Posicao p = new aula_159_Posicao( 0, 0);
		
		//
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 2);
		
		if (getTabuleiro().posicaoExistente(p) && permiteMovimento(p) )
		{
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//
		p.setValores(posicao.getLinha() - 2, posicao.getColuna() -1);
		
		if (getTabuleiro().posicaoExistente(p) && permiteMovimento(p) )
		{
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//
		p.setValores(posicao.getLinha() -2, posicao.getColuna() + 1);
		
		if (getTabuleiro().posicaoExistente(p) && permiteMovimento(p) )
		{
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//
		p.setValores(posicao.getLinha() -1, posicao.getColuna() + 2);
		
		if (getTabuleiro().posicaoExistente(p) && permiteMovimento(p) )
		{
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 2);
		
		if (getTabuleiro().posicaoExistente(p) && permiteMovimento(p) )
		{
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//
		p.setValores(posicao.getLinha() + 2, posicao.getColuna() + 1);
		
		if (getTabuleiro().posicaoExistente(p) && permiteMovimento(p) )
		{
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//
		p.setValores(posicao.getLinha() + 2, posicao.getColuna() - 1);
		
		if (getTabuleiro().posicaoExistente(p) && permiteMovimento(p) )
		{
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 2);
		
		if (getTabuleiro().posicaoExistente(p) && permiteMovimento(p) )
		{
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		return matriz;
	}
}
