package tabuleiro;

public class aula_150_Peca
{
	protected aula_149_Posicao posicao;
	private aula_150_Tabuleiro tabuleiro;
	
	public aula_150_Peca(aula_150_Tabuleiro tabuleiro)
	{
		this.tabuleiro = tabuleiro;
		posicao  = null;
	}

	protected aula_150_Tabuleiro getTabuleiro() {
		return tabuleiro;
	}	
	
	
}
