package tabuleiro;

public class aula_156_Peca
{
	protected aula_149_Posicao posicao;
	private aula_156_Tabuleiro tabuleiro;
	
	public aula_156_Peca() {};
	
	public aula_156_Peca(aula_156_Tabuleiro tabuleiro)
	{
		this.tabuleiro = tabuleiro;
		posicao  = null;
	}

	protected aula_156_Tabuleiro getTabuleiro() {
		return tabuleiro;
	}	
	
	
}
