package xadrez.pecas;

import tabuleiro.aula_156_Tabuleiro;
import tabuleiro.aula_157_Peca;
import tabuleiro.aula_159_Posicao;
import xadrez.aula_151_Cor;
import xadrez.aula_167_PecaXadrez;

public class aula_161_Rei extends aula_167_PecaXadrez
{
	public aula_161_Rei ( aula_156_Tabuleiro tabuleiro, aula_151_Cor cor)
	{
		super(tabuleiro, cor);
	}
	
	@Override
	public String toString()
	{
		return "K";
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
		
		//acima
		p.setValores(posicao.getLinha() - 1, posicao.getColuna());
		
		if (getTabuleiro().posicaoExistente(p) && permiteMovimento(p) )
		{
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//abaixo
		p.setValores(posicao.getLinha() + 1, posicao.getColuna());
		
		if (getTabuleiro().posicaoExistente(p) && permiteMovimento(p) )
		{
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//esquerda
		p.setValores(posicao.getLinha(), posicao.getColuna() - 1);
		
		if (getTabuleiro().posicaoExistente(p) && permiteMovimento(p) )
		{
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//direita
		p.setValores(posicao.getLinha(), posicao.getColuna() + 1);
		
		if (getTabuleiro().posicaoExistente(p) && permiteMovimento(p) )
		{
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//esquerda superior
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() - 1);
		
		if (getTabuleiro().posicaoExistente(p) && permiteMovimento(p) )
		{
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//direita superior
		p.setValores(posicao.getLinha() - 1, posicao.getColuna() + 1);
		
		if (getTabuleiro().posicaoExistente(p) && permiteMovimento(p) )
		{
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//esquerda inferior
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() - 1);
		
		if (getTabuleiro().posicaoExistente(p) && permiteMovimento(p) )
		{
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		//direita inferior
		p.setValores(posicao.getLinha() + 1, posicao.getColuna() + 1);
		
		if (getTabuleiro().posicaoExistente(p) && permiteMovimento(p) )
		{
			matriz[p.getLinha()][p.getColuna()] = true;
		}
		
		return matriz;
	}	
}
