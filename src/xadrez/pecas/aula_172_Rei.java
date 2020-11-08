package xadrez.pecas;

import tabuleiro.aula_156_Tabuleiro;
import tabuleiro.aula_157_Peca;
import tabuleiro.aula_159_Posicao;
import xadrez.aula_151_Cor;
import xadrez.aula_167_PecaXadrez;
import xadrez.aula_176_PartidaXadrez;

public class aula_172_Rei extends aula_167_PecaXadrez
{
	private aula_176_PartidaXadrez partidaXadrez;
	
	public aula_172_Rei ( aula_156_Tabuleiro tabuleiro, aula_151_Cor cor, aula_176_PartidaXadrez partidaXadrez)
	{
		super(tabuleiro, cor);
		this.partidaXadrez = partidaXadrez;
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
	
	private boolean testeRoque(aula_159_Posicao posicao)
	{
		aula_167_PecaXadrez peca = (aula_167_PecaXadrez)getTabuleiro().peca(posicao);
		return peca != null && peca instanceof aula_159_Torre &&  peca.getCor() == getCor() && peca.getContagemMovimentos() == 0;
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
		
		//jogada especial roque
		if(getContagemMovimentos() == 0 && !partidaXadrez.getXeque())
		{
			//roque do rei
			aula_159_Posicao posT1 = new aula_159_Posicao(posicao.getLinha(), posicao.getColuna() + 3);
			
			if (testeRoque(posT1))
			{
				 aula_159_Posicao p1 = new aula_159_Posicao(posicao.getLinha(), posicao.getColuna() + 1);
				aula_159_Posicao p2 = new aula_159_Posicao(posicao.getLinha(), posicao.getColuna() + 2);
				
				if( getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null)
				{
					matriz[posicao.getLinha()][posicao.getColuna() + 2] = true;
				}
			}
			
			//roque da rainha
			aula_159_Posicao posT2 = new aula_159_Posicao(posicao.getLinha(), posicao.getColuna() - 4);
			
			if (testeRoque(posT2))
			{
				aula_159_Posicao p1 = new aula_159_Posicao(posicao.getLinha(), posicao.getColuna() - 1);
				aula_159_Posicao p2 = new aula_159_Posicao(posicao.getLinha(), posicao.getColuna() - 2);
				aula_159_Posicao p3 = new aula_159_Posicao(posicao.getLinha(), posicao.getColuna() - 3);
				
				if( getTabuleiro().peca(p1) == null && getTabuleiro().peca(p2) == null && getTabuleiro().peca(p3) == null)
				{
					matriz[posicao.getLinha()][posicao.getColuna() - 2] = true;
				}
			}
		}
		
		return matriz;
	}	
}
