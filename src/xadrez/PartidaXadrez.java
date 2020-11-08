package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tabuleiro.Tabuleiro;
import tabuleiro.Peca;
import tabuleiro.Posicao;
import xadrez.pecas.Torre;
import xadrez.pecas.Rei;
import xadrez.pecas.Peao;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Rainha;

public class PartidaXadrez
{
	private int turno;
	private Cor jogadorAtual;
	private Tabuleiro tabuleiro;
	private boolean xeque;
	private boolean xequemate;
	private PecaXadrez enPassant;
	private PecaXadrez promocao;
	
	private List<Peca> pecasTabuleiro  = new ArrayList<>();
	private List<Peca> pecasCapturadas  = new ArrayList<>();
	
	public PartidaXadrez()
	{
		tabuleiro = new Tabuleiro( 8, 8);
		turno = 1;
		jogadorAtual = Cor.BRANCA;
		xeque = false;
		enPassant = null;
		promocao = null;
		configInicial();
	}
	
	public PecaXadrez[][] getPecas()
	{
		PecaXadrez[][] matriz = new PecaXadrez[tabuleiro.getLinhas() ][tabuleiro.getColunas() ];
		
		for (int i =0; i< tabuleiro.getLinhas(); i++)
		{
			for (int j =0; j< tabuleiro.getColunas(); j++)
			{
				matriz[i][j] = (PecaXadrez)tabuleiro.peca(i,j);
			}
		}
		return matriz;
	}
	
	public int getTurno()
	{
		return turno;
	}
	
	public Cor getJogadorAtual()
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
	
	public PecaXadrez getEnPassant()
	{
		return enPassant;
	}
	
	public PecaXadrez getPromocao()
	{
		return promocao;
	}
	
	public boolean[][] movimentosPossiveis(PosicionamentoXadrez posicaoOrigem)
	{
		Posicao posicao = posicaoOrigem.convertePosicao();
		validarPosicaoOrigem(posicao);
		return tabuleiro.peca(posicao).movimentosPossiveis();
	}
	
	public PecaXadrez realizarMovimentoXadrez(PosicionamentoXadrez posicaoOrigem, PosicionamentoXadrez posicaoDestino)
	{
		Posicao origem = posicaoOrigem.convertePosicao();
		Posicao destino =  posicaoDestino.convertePosicao();
		validarPosicaoOrigem(origem);
		validarPosicaoDestino(origem, destino);
		Peca pecaCapturada = realizarMovimento(origem, destino);	
		
		if (testeXeque(jogadorAtual))
		{
			desfazerMovimento(origem, destino, pecaCapturada);
			throw new ExcecaoXadrez("Não é permitido que o jogador se coloque em xeque");
		}
		
		PecaXadrez pecaMovida = (PecaXadrez)tabuleiro.peca(destino);
		
		// jogada especial promocao
		
		promocao = null;
		
		if (pecaMovida instanceof Peao) 
		{
			if ( (pecaMovida.getCor() == Cor.BRANCA && destino.getLinha() == 0) || (pecaMovida.getCor() == Cor.PRETA && destino.getLinha() == 7) )
			{
				promocao = (PecaXadrez) tabuleiro.peca(destino);
				promocao = (PecaXadrez) trocarPecaPromovida("r");				
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
		if (pecaMovida instanceof Peao && (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha()+ 2))
		{
			enPassant = pecaMovida;
		}
		else
		{
			enPassant = null;
		}
				
		return (PecaXadrez) pecaCapturada;		
	}
	
	public Peca trocarPecaPromovida(String tipo)
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
		
		Posicao posicao = promocao.getPosicionamentoXadrez().convertePosicao();
		Peca peca = tabuleiro.removerPeca(posicao);
		pecasTabuleiro.remove(peca);
		
		PecaXadrez novaPeca = novaPeca( tipo, promocao.getCor() );		
		tabuleiro.posicionarPeca(novaPeca, posicao);
		pecasTabuleiro.add(novaPeca);
		
		return novaPeca;
	}
	
	private PecaXadrez novaPeca(String tipo, Cor cor)
	{
		if(tipo.equals("b")) return new Bispo(tabuleiro, cor);
		if(tipo.equals("c")) return new Cavalo(tabuleiro, cor);		
		if(tipo.equals("r")) return new Rainha(tabuleiro, cor);		
		return new Torre(tabuleiro, cor);
	}
	
	private Peca realizarMovimento(Posicao origem, Posicao destino)
	{
		PecaXadrez peca = (PecaXadrez)tabuleiro.removerPeca(origem);
		peca.somarContagemMovimentos();
		Peca pecaCapturada = tabuleiro.removerPeca(destino);
		tabuleiro.posicionarPeca(peca, destino);
		
		if (pecaCapturada != null)
		{
			pecasTabuleiro.remove(pecaCapturada);
			pecasCapturadas.add(pecaCapturada);
		}
		
		// jogada especial roque do rei
		if(peca instanceof Rei && destino.getColuna() == origem.getColuna() + 2)
		{
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			
			PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(origemT);
			tabuleiro.posicionarPeca(torre, destinoT);
			torre.somarContagemMovimentos();
		}
		
		// jogada especial roque do rainha
		if(peca instanceof Rei && destino.getColuna() == origem.getColuna() - 2)
		{
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			
			PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(origemT);
			tabuleiro.posicionarPeca(torre, destinoT);
			torre.somarContagemMovimentos();
		}
		
		// jogada especial en passant
		if(peca instanceof Peao)
		{
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == null)
			{
				Posicao posicaoPeao;
				if(peca.getCor() == Cor.BRANCA)
				{
					posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());
				}
				else
				{
					posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
				}
				
				pecaCapturada = tabuleiro.removerPeca(posicaoPeao);
				pecasCapturadas.add(pecaCapturada);
				pecasTabuleiro.remove(pecaCapturada);
			}
		}
		
		return pecaCapturada;
	}
	
	private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada)
	{
		PecaXadrez peca = (PecaXadrez)tabuleiro.removerPeca(destino);
		
		tabuleiro.posicionarPeca(peca, origem);
		
		if(pecaCapturada != null)
		{
			tabuleiro.posicionarPeca(pecaCapturada, destino);
			pecasCapturadas.remove(pecaCapturada);
			pecasTabuleiro.add(pecaCapturada);
		}
		
		// jogada especial roque do rei
		if(peca instanceof Rei && destino.getColuna() == origem.getColuna() + 2)
		{
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
			
			PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(destinoT);
			tabuleiro.posicionarPeca(torre, origemT);
			torre.subtrairContagemMovimentos();
		}
		
		// jogada especial roque do rainha
		if(peca instanceof Rei && destino.getColuna() == origem.getColuna() - 2)
		{
			Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
			Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
			
			PecaXadrez torre = (PecaXadrez)tabuleiro.removerPeca(destinoT);
			tabuleiro.posicionarPeca(torre, origemT);
			torre.subtrairContagemMovimentos();
		}
		
		// jogada especial en passant
		if(peca instanceof Peao)
		{
			if (origem.getColuna() != destino.getColuna() && pecaCapturada == enPassant)
			{
				PecaXadrez peao = (PecaXadrez)tabuleiro.removerPeca(destino);
				Posicao posicaoPeao;
				if(peca.getCor() == Cor.BRANCA)
				{
					posicaoPeao = new Posicao(3, destino.getColuna());
				}
				else
				{
					posicaoPeao = new Posicao(4, destino.getColuna());
				}				
				tabuleiro.posicionarPeca(peao, posicaoPeao);				
			}
		}
	}
	
	private void validarPosicaoOrigem(Posicao posicao)
	{
		if (!tabuleiro.pecaExistente(posicao))
		{
			throw new ExcecaoXadrez("Não existe peça na posição de origem");
		}
		
		if (jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor() )
		{
			throw new ExcecaoXadrez("A peça escolhida não é sua");
		}
		
		if(!tabuleiro.peca(posicao).haAlgumMovimentoPossivel())
		{
			throw new ExcecaoXadrez("Não existe movimento possível para a peça selecionada");
		}
	}
	
	private void validarPosicaoDestino(Posicao origem, Posicao destino)
	{
		if(!tabuleiro.peca(origem).movimentoPossivel(destino))
		{
			throw new ExcecaoXadrez("A peça escolhida não poder movimentada para a posição escolhida");
		}
	}
	
	private Cor oponente(Cor cor)
	{
		return (cor == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}
	
	private PecaXadrez rei(Cor cor)
	{
		List<Peca> lista = pecasTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor ).collect(Collectors.toList());
		
		for (Peca p : lista)
		{
			if (p instanceof Rei)
			{
				return (PecaXadrez)p;
			}
		}
		throw new IllegalStateException("Não existe o rei da cor"+ cor +" no tabuleiro");
	}
	
	private boolean testeXeque(Cor cor)
	{
		Posicao posicaoRei = rei(cor).getPosicionamentoXadrez().convertePosicao();
		List<Peca> pecasOponente = pecasTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == oponente(cor) ).collect(Collectors.toList());
		
		for (Peca p : pecasOponente)
		{
			boolean[][] matriz = p.movimentosPossiveis();
			if (matriz[posicaoRei.getLinha()][posicaoRei.getColuna()])
			{
				return true;
			}
		}
		return false;
	}
	
	private boolean testeXequemate(Cor cor)
	{
		if(!testeXeque(cor))
		{
			return false;
		}
		List<Peca> lista = pecasTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor) .collect(Collectors.toList());
		
		for(Peca p : lista)
		{
			boolean[][] matriz = p.movimentosPossiveis();
			for(int i = 0; i < tabuleiro.getLinhas(); i++)
			{
				for(int j = 0; j < tabuleiro.getColunas(); j++)
				{
					if(matriz[i][j])
					{
						Posicao origem = ((PecaXadrez)p).getPosicionamentoXadrez().convertePosicao();
						Posicao destino = new Posicao( i, j);
						Peca pecaCapturada = realizarMovimento(origem, destino);
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
	
	private void posicionaNovaPeca(char coluna, int linha, PecaXadrez peca)
	{
		tabuleiro.posicionarPeca(peca, new PosicionamentoXadrez(coluna, linha).convertePosicao() );
		pecasTabuleiro.add(peca);
	}
	
	private void proximoTurno()
	{
		turno++;
		jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
	}
	
	private void configInicial()
	{					
		posicionaNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCA));
		posicionaNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCA));
		posicionaNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCA));
		posicionaNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCA));
		posicionaNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCA, this));
		posicionaNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCA));
		posicionaNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCA));
        posicionaNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCA));
        posicionaNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        posicionaNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        posicionaNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        posicionaNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        posicionaNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        posicionaNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        posicionaNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        posicionaNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCA, this));        

        posicionaNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETA));
        posicionaNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETA));
        posicionaNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETA));
        posicionaNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETA));
        posicionaNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETA, this));
        posicionaNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETA));
        posicionaNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETA));
        posicionaNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETA));
        posicionaNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETA, this));
        posicionaNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETA, this));
        posicionaNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETA, this));
        posicionaNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETA, this));
        posicionaNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETA, this));
        posicionaNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETA, this));
        posicionaNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETA, this));
        posicionaNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETA, this));        
	}
}
