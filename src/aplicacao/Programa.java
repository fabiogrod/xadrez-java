package aplicacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import xadrez.PosicionamentoXadrez;
import xadrez.ExcecaoXadrez;
import xadrez.PartidaXadrez;
import xadrez.PecaXadrez;

public class Programa
{
	public static void main(String[] args)
	{
//		aula_149_Posicao pos = new aula_149_Posicao(3,5); 
//		
//		System.out.println("Vai filhão!! - " + pos);
//		
//		aula_150_Tabuleiro tabuleiro = new aula_150_Tabuleiro( 8, 8);
		
		Locale.setDefault(new Locale("pt","BR") );
		
		Scanner sc = new Scanner(System.in);
		PartidaXadrez partidaXadrez =  new PartidaXadrez();
		List<PecaXadrez> capturas = new ArrayList<>();		
		
		while (!partidaXadrez.getXequemate())
		{
			try
			{				
				InterfaceUsuario.limpaTela();
				InterfaceUsuario.imprimirPartida(partidaXadrez, capturas);
				
				System.out.println();
				System.out.print("Origem: ");
				PosicionamentoXadrez origem = InterfaceUsuario.verificarPosicionamentoXadrez(sc);
				
				boolean [][] movimentosPossiveis = partidaXadrez.movimentosPossiveis(origem);
				InterfaceUsuario.limpaTela();
				InterfaceUsuario.imprimirTabuleiro(partidaXadrez.getPecas(), movimentosPossiveis);
				
				System.out.println();			
				System.out.print("Destino: ");
				PosicionamentoXadrez destino = InterfaceUsuario.verificarPosicionamentoXadrez(sc);
				
				PecaXadrez pecaCapturada = partidaXadrez.realizarMovimentoXadrez(origem, destino);
				if (pecaCapturada != null)
				{
					capturas.add(pecaCapturada);
				}
				
				if (partidaXadrez.getPromocao() != null)
				{
					System.out.print("Digite um peça para promoção (B/C/R/T): ");
					String tipo = sc.nextLine().toLowerCase();
					
					while (!tipo.equals("b") && !tipo.equals("c") && !tipo.equals("r") && !tipo.equals("t"))
					{
						System.out.print("Valor inválido! Digite um peça para promoção (B/C/R/T): ");
						tipo = sc.nextLine();
					}
					
					partidaXadrez.trocarPecaPromovida(tipo.toLowerCase());
				}
			}
			catch(ExcecaoXadrez e)
			{
				System.out.println(e.getMessage());				
				sc.nextLine();
			}
			catch(InputMismatchException e)
			{
				System.out.println(e.getMessage());
				sc.nextLine();
			}
		}
		InterfaceUsuario.limpaTela();
		InterfaceUsuario.imprimirPartida(partidaXadrez, capturas);
	}
}
