package tabuleiro;

public class aula_151_Peca
{
	protected aula_149_Posicao posicao;
	private aula_153_Tabuleiro tabuleiro;
	
	public aula_151_Peca() {};
	
	public aula_151_Peca(aula_153_Tabuleiro tabuleiro)
	{
		this.tabuleiro = tabuleiro;
		posicao  = null;
	}

	protected aula_153_Tabuleiro getTabuleiro() {
		return tabuleiro;
	}	
	
	
}
