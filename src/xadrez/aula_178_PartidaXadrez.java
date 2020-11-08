package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.aula_156_Tabuleiro;
import tabuleiro.aula_157_Peca;
import tabuleiro.aula_159_Posicao;
import xadrez.pecas.aula_159_Torre;
import xadrez.pecas.aula_172_Rei;
import xadrez.pecas.aula_174_Peao;
import xadrez.pecas.aula_169_Bispo;
import xadrez.pecas.aula_170_Cavalo;
import xadrez.pecas.aula_171_Rainha;

public class aula_178_PartidaXadrez
{
	private int turno;
	private aula_151_Cor jogadorAtual;
	private aula_156_Tabuleiro tabuleiro;
	private boolean xeque;
	private boolean xequemate;
	private aula_167_PecaXadrez enPassant;
	private aula_167_PecaXadrez promocao;
	
	private List<aula_157_Peca> pecasTabuleiro  = new ArrayList<>();
	private List<aula_157_Peca> pecasCapturadas  = new ArrayList<>();
	
	public aula_178_PartidaXadrez()
	{
		tabuleiro = new aula_156_Tabuleiro( 8, 8);
		turno = 1;
		jogadorAtual = aula_151_Cor.BRANCA;
		xeque = false;
		enPassant = null;
		promocao = null;
		configInicial();
	}
	
	public aula_167_PecaXadrez[][] getPecas()
	{
		aula_167_PecaXadrez[][] matriz = new aula_167_PecaXadrez[tabuleiro.getLinhas() ][tabuleiro.getColunas() ];
		
		for (int i =0; i< tabuleiro.getLinhas(); i++)
		{
			for (int j =0; j< tabuleiro.getColunas(); j++)
			{
				matriz[i][j] = (aula_167_PecaXadrez)tabuleiro.peca(i,j);
			}
		}
		return matriz;
	}
	
	public int getTurno()
	{
		return turno;
	}
	
	public aula_151_Cor getJogadorAtual()
	{
		return jogadorAtual;
	}
	
	public boolean getXeque()
	{
		return xeque;
	}
	
	public boolean getXequemate()
	{
		return xequemate;
	}
	
	public aula_167_PecaXadrez getEnPassant()
	{
		return enPassant;
	}
	
	public aula_167_PecaXadrez getPromocao()
	{
		return promocao;
	}
	
	public boolean[][] movimentosPossiveis(aula_154_PosicionamentoXadrez posicaoOrigem)
	{
		aula_159_Posicao posicao = posicaoOrigem.convertePosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}
	
	public aula_167_PecaXadrez realizarMovimentoXadrez(aula_154_PosicionamentoXadrez posicaoOrigem, aula_154_PosicionamentoXadrez posicaoDestino)
	{
		aula_159_Posicao origem = posicaoOrigem.convertePosicao();
		aula_159_Posicao destino =  posicaoDestino.convertePosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		aula_157_Peca pecaCapturada = realizarMovimento(origem, destino);	
		
		if (testeXeque(jogadorAtual))
		{
			desfazerMovimento(origem, destino, pecaCapturada);
			throw new aula_156_ExcecaoXadrez("Não é permitido que o jogador se coloque em xeque");
		}
		
		aula_167_PecaXadrez pecaMovida = (aula_167_PecaXadrez)tabuleiro.peca(destino);
		
		// jogada especial promocao
		
		promocao = null;
		
		if (pecaMovida instanceof aula_174_Peao) 
		{
			if ( (pecaMovida.getCor() == aula_151_Cor.BRANCA && destino.getLinha() == 0) || (pecaMovida.getCor() == aula_151_Cor.PRETA && destino.getLinha() == 7) )
			{
				promocao = (aula_167_PecaXadrez) tabuleiro.peca(destino);
				promocao = (aula_167_PecaXadrez) trocarPecaPromovida("r");				
			}
		}
		
		xeque = (testeXeque(oponente(jogadorAtual) ) ) ? true : false;
		
		if (testeXequemate(oponente(jogadorAtual)))
		{
			xequemate = true;
		}
		else
		{
			proximoTurno();
		}		
		
		//jogada especial en passant
		if (pecaMovida instanceof aula_174_Peao && (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha()+ 2))
		{
			enPassant = pecaMovida;
		}
		else
		{
			enPassant = null;
		}
				
		return (aula_167_PecaXadrez) pecaCapturada;		
	}
	
	public aula_157_Peca trocarPecaPromovida(String tipo)
	{
		if (promocao == null)
		{
			throw new IllegalStateException("Não há peça para ser promovida");
		}
		if (!tipo.equals("b") && !tipo.equals("c") && !tipo.equals("r") && !tipo.equals("t"))
		{
			//throw new InvalidParameterException("Tipo inválido para promoção");
			return promocao;
		}
		
		aula_159_Posicao posicao = promocao.getPosicionamentoXadrez().convertePosicao();
		aula_157_Peca peca = tabuleiro.removerPeca(posicao);
		pecasTabuleiro.remove(peca);
		
		aula_167_PecaXadrez novaPeca = novaPeca( tipo, promocao.getCor() );		
		tabuleiro.posicionarPeca(novaPeca, posicao);
		pecasTabuleiro.add(novaPeca);
		
		return novaPeca;
	}
	
	private aula_167_PecaXadrez novaPeca(String tipo, aula_151_Cor cor)
	{
		if(tipo.equals("b")) return new aula_169_Bispo(tabuleiro, cor);
		if(tipo.equals("c")) return new aula_170_Cavalo(tabuleiro, cor);		
		if(tipo.equals("r")) return new aula_171_Rainha(tabuleiro, cor);		
		return new aula_159_Torre(tabuleiro, cor);
	}
	
	private aula_157_Peca realizarMovimento(aula_159_Posicao origem, aula_159_Posicao destino)
	{
		aula_167_PecaXadrez peca = (aula_167_PecaXadrez)tabuleiro.removerPeca(origem);
		peca.somarContagemMovimentos();
		aula_157_Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.posicionarPeca(peca, destino);
		
		if (pecaCapturada != null)
		{
			pecasTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		// jogada especial roque do rei
		if(peca instanceof aula_172_Rei && destino.getColuna() == origem.getColuna() + 2)
		{
			aula_159_Posicao origemT = new aula_159_Posicao(origem.getLinha(), origem.getColuna() + 3);
			aula_159_Posicao destinoT = new aula_159_Posicao(origem.getLinha(), origem.getColuna() + 1);
			
			aula_167_PecaXadrez torre = (aula_167_PecaXadrez)tabuleiro.removerPeca(origemT);
			tabuleiro.posicionarPeca(torre, destinoT);
			torre.somarContagemMovimentos();
		}
		
		// jogada especial roque do rainha
		if(peca instanceof aula_172_Rei && destino.getColuna() == origem.getColuna() - 2)
		{
			aula_159_Posicao origemT = new aula_159_Posicao(origem.getLinha(), origem.getColuna() - 4);
			aula_159_Posicao destinoT = new aula_159_Posicao(origem.getLinha(), origem.getColuna() - 1);
			
			aula_167_PecaXadrez torre = (aula_167_PecaXadrez)tabuleiro.removerPeca(origemT);
			tabuleiro.posicionarPeca(torre, destinoT);
			torre.somarContagemMovimentos();
		}
		
		// jogada especial en passant
		if(peca instanceof aula_174_Peao)
		{
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == null)
			{
				aula_159_Posicao posicaoPeao;
				if(peca.getCor() == aula_151_Cor.BRANCA)
				{
					posicaoPeao = new aula_159_Posicao(destino.getLinha() + 1, destino.getColuna());
				}
				else
				{
					posicaoPeao = new aula_159_Posicao(destino.getLinha() - 1, destino.getColuna());
				}
				
				pecaCapturada = tabuleiro.removerPeca(posicaoPeao);
				pecasCapturadas.add(pecaCapturada);
				pecasTabuleiro.remove(pecaCapturada);
			}
		}
		
		return pecaCapturada;
	}
	
	private void desfazerMovimento(aula_159_Posicao origem, aula_159_Posicao destino, aula_157_Peca pecaCapturada)
	{
		aula_167_PecaXadrez peca = (aula_167_PecaXadrez)tabuleiro.removerPeca(destino);
		
		tabuleiro.posicionarPeca(peca, origem);
		
		if(pecaCapturada != null)
		{
			tabuleiro.posicionarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasTabuleiro.add(pecaCapturada);
		}
		
		// jogada especial roque do rei
		if(peca instanceof aula_172_Rei && destino.getColuna() == origem.getColuna() + 2)
		{
			aula_159_Posicao origemT = new aula_159_Posicao(origem.getLinha(), origem.getColuna() + 3);
			aula_159_Posicao destinoT = new aula_159_Posicao(origem.getLinha(), origem.getColuna() + 1);
			
			aula_167_PecaXadrez torre = (aula_167_PecaXadrez)tabuleiro.removerPeca(destinoT);
			tabuleiro.posicionarPeca(torre, origemT);
			torre.subtrairContagemMovimentos();
		}
		
		// jogada especial roque do rainha
		if(peca instanceof aula_172_Rei && destino.getColuna() == origem.getColuna() - 2)
		{
			aula_159_Posicao origemT = new aula_159_Posicao(origem.getLinha(), origem.getColuna() - 4);
			aula_159_Posicao destinoT = new aula_159_Posicao(origem.getLinha(), origem.getColuna() - 1);
			
			aula_167_PecaXadrez torre = (aula_167_PecaXadrez)tabuleiro.removerPeca(destinoT);
			tabuleiro.posicionarPeca(torre, origemT);
			torre.subtrairContagemMovimentos();
		}
		
		// jogada especial en passant
		if(peca instanceof aula_174_Peao)
		{
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == enPassant)
			{
				aula_167_PecaXadrez peao = (aula_167_PecaXadrez)tabuleiro.removerPeca(destino);
				aula_159_Posicao posicaoPeao;
				if(peca.getCor() == aula_151_Cor.BRANCA)
				{
					posicaoPeao = new aula_159_Posicao(3, destino.getColuna());
				}
				else
				{
					posicaoPeao = new aula_159_Posicao(4, destino.getColuna());
				}				
				tabuleiro.posicionarPeca(peao, posicaoPeao);				
			}
		}
	}
	
	private void validarPosicaoOrigem(aula_159_Posicao posicao)
	{
		if (!tabuleiro.pecaExistente(posicao))
		{
			throw new aula_156_ExcecaoXadrez("Não existe peça na posição de origem");
		}
		
		if (jogadorAtual != ((aula_167_PecaXadrez)tabuleiro.peca(posicao)).getCor() )
		{
			throw new aula_156_ExcecaoXadrez("A peça escolhida não é sua");
		}
		
		if(!tabuleiro.peca(posicao).haAlgumMovimentoPossivel())
		{
			throw new aula_156_ExcecaoXadrez("Não existe movimento possível para a peça selecionada");
		}
	}
	
	private void validarPosicaoDestino(aula_159_Posicao origem, aula_159_Posicao destino)
	{
		if(!tabuleiro.peca(origem).movimentoPossivel(destino))
		{
			throw new aula_156_ExcecaoXadrez("A peça escolhida não poder movimentada para a posição escolhida");
		}
	}
	
	private aula_151_Cor oponente(aula_151_Cor cor)
	{
		return (cor == aula_151_Cor.BRANCA) ? aula_151_Cor.PRETA : aula_151_Cor.BRANCA;
	}
	
	private aula_167_PecaXadrez rei(aula_151_Cor cor)
	{
		List<aula_157_Peca> lista = pecasTabuleiro.stream().filter(x -> ((aula_167_PecaXadrez)x).getCor() == cor ).collect(Collectors.toList());
		
		for (aula_157_Peca p : lista)
		{
			if (p instanceof aula_172_Rei)
			{
				return (aula_167_PecaXadrez)p;
			}
		}
		throw new IllegalStateException("Não existe o rei da cor"+ cor +" no tabuleiro");
	}
	
	private boolean testeXeque(aula_151_Cor cor)
	{
		aula_159_Posicao posicaoRei = rei(cor).getPosicionamentoXadrez().convertePosicao();
		List<aula_157_Peca> pecasOponente = pecasTabuleiro.stream().filter(x -> ((aula_167_PecaXadrez)x).getCor() == oponente(cor) ).collect(Collectors.toList());
		
		for (aula_157_Peca p : pecasOponente)
		{
			boolean[][] matriz = p.movimentosPossiveis();
			if (matriz[posicaoRei.getLinha()][posicaoRei.getColuna()])
			{
				return true;
			}
		}
		return false;
	}
	
	private boolean testeXequemate(aula_151_Cor cor)
	{
		if(!testeXeque(cor))
		{
			return false;
		}
		List<aula_157_Peca> lista = pecasTabuleiro.stream().filter(x -> ((aula_167_PecaXadrez)x).getCor() == cor) .collect(Collectors.toList());
		
		for(aula_157_Peca p : lista)
		{
			boolean[][] matriz = p.movimentosPossiveis();
			for(int i = 0; i < tabuleiro.getLinhas(); i++)
			{
				for(int j = 0; j < tabuleiro.getColunas(); j++)
				{
					if(matriz[i][j])
					{
						aula_159_Posicao origem = ((aula_167_PecaXadrez)p).getPosicionamentoXadrez().convertePosicao();
						aula_159_Posicao destino = new aula_159_Posicao( i, j);
						aula_157_Peca pecaCapturada = realizarMovimento(origem, destino);
						boolean testeXeque = testeXeque(cor);
						desfazerMovimento(origem, destino, pecaCapturada);
						if (!testeXeque)
						{
							return false;
						}
					}
				}
			}				
		}
		return true;
	}
	
	private void posicionaNovaPeca(char coluna, int linha, aula_167_PecaXadrez peca)
	{
		tabuleiro.posicionarPeca(peca, new aula_154_PosicionamentoXadrez(coluna, linha).convertePosicao() );
		pecasTabuleiro.add(peca);
	}
	
	private void proximoTurno()
	{
		turno++;
		jogadorAtual = (jogadorAtual == aula_151_Cor.BRANCA) ? aula_151_Cor.PRETA : aula_151_Cor.BRANCA;
	}
	
	private void configInicial()
	{					
		posicionaNovaPeca('a', 1, new aula_159_Torre(tabuleiro, aula_151_Cor.BRANCA));
		posicionaNovaPeca('b', 1, new aula_170_Cavalo(tabuleiro, aula_151_Cor.BRANCA));
		posicionaNovaPeca('c', 1, new aula_169_Bispo(tabuleiro, aula_151_Cor.BRANCA));
		posicionaNovaPeca('d', 1, new aula_171_Rainha(tabuleiro, aula_151_Cor.BRANCA));
		posicionaNovaPeca('e', 1, new aula_172_Rei(tabuleiro, aula_151_Cor.BRANCA, this));
		posicionaNovaPeca('f', 1, new aula_169_Bispo(tabuleiro, aula_151_Cor.BRANCA));
		posicionaNovaPeca('g', 1, new aula_170_Cavalo(tabuleiro, aula_151_Cor.BRANCA));
        posicionaNovaPeca('h', 1, new aula_159_Torre(tabuleiro, aula_151_Cor.BRANCA));
        posicionaNovaPeca('a', 2, new aula_174_Peao(tabuleiro, aula_151_Cor.BRANCA, this));
        posicionaNovaPeca('b', 2, new aula_174_Peao(tabuleiro, aula_151_Cor.BRANCA, this));
        posicionaNovaPeca('c', 2, new aula_174_Peao(tabuleiro, aula_151_Cor.BRANCA, this));
        posicionaNovaPeca('d', 2, new aula_174_Peao(tabuleiro, aula_151_Cor.BRANCA, this));
        posicionaNovaPeca('e', 2, new aula_174_Peao(tabuleiro, aula_151_Cor.BRANCA, this));
        posicionaNovaPeca('f', 2, new aula_174_Peao(tabuleiro, aula_151_Cor.BRANCA, this));
        posicionaNovaPeca('g', 2, new aula_174_Peao(tabuleiro, aula_151_Cor.BRANCA, this));
        posicionaNovaPeca('h', 2, new aula_174_Peao(tabuleiro, aula_151_Cor.BRANCA, this));        

        posicionaNovaPeca('a', 8, new aula_159_Torre(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('b', 8, new aula_170_Cavalo(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('c', 8, new aula_169_Bispo(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('d', 8, new aula_171_Rainha(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('e', 8, new aula_172_Rei(tabuleiro, aula_151_Cor.PRETA, this));
        posicionaNovaPeca('f', 8, new aula_169_Bispo(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('g', 8, new aula_170_Cavalo(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('h', 8, new aula_159_Torre(tabuleiro, aula_151_Cor.PRETA));
        posicionaNovaPeca('a', 7, new aula_174_Peao(tabuleiro, aula_151_Cor.PRETA, this));
        posicionaNovaPeca('b', 7, new aula_174_Peao(tabuleiro, aula_151_Cor.PRETA, this));
        posicionaNovaPeca('c', 7, new aula_174_Peao(tabuleiro, aula_151_Cor.PRETA, this));
        posicionaNovaPeca('d', 7, new aula_174_Peao(tabuleiro, aula_151_Cor.PRETA, this));
        posicionaNovaPeca('e', 7, new aula_174_Peao(tabuleiro, aula_151_Cor.PRETA, this));
        posicionaNovaPeca('f', 7, new aula_174_Peao(tabuleiro, aula_151_Cor.PRETA, this));
        posicionaNovaPeca('g', 7, new aula_174_Peao(tabuleiro, aula_151_Cor.PRETA, this));
        posicionaNovaPeca('h', 7, new aula_174_Peao(tabuleiro, aula_151_Cor.PRETA, this));        
	}
}
