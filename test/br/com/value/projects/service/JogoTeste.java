package br.com.value.projects.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.hamcrest.core.IsNot;
import org.junit.Test;

import br.com.value.projects.builder.CriadorDeJogo;
import br.com.value.projects.dominio.Jogo;
import br.com.value.projects.dominio.Participante;
import br.com.value.projects.dominio.Resultado;

public class JogoTeste {
	
	@Test
	public void novoJogoDeveTerDescricao() //cen�rio de teste Enrico Acquaviva
	{
		Jogo jogoSemNome = new Jogo("Futebol de formiga");

		assertNotEquals(null, jogoSemNome.getDescricao());//valida��o para verificar se � poss�vel criar um jogo sem nome
		assertNotEquals("", jogoSemNome.getDescricao());
	}
	
	//Cenário de teste, um jogo de corrida com um unico participante
	@Test
	public void deveTerJogoComUnicoParticipante() {
		Jogo jogo = new Jogo("Jogo de corrida");
		
		//Testa se o jogo iniciou sem resultados
		assertEquals(0, jogo.getResultados().size());
		
		//Insere um novoresultado e participante ao jogo
		jogo.anota(new Resultado(new Participante("Leonardo"), 150));

		//Testa a quantidade de resultados no jogo
		assertEquals(1, jogo.getResultados().size());
		
		//Testa se a metrica inserida permanece a mesma
		assertEquals(150.0, jogo.getResultados().get(0).getMetrica(), 0.00001);
	}
	//CEnário de testes: Um jogo de cata moedas que deve ter vários resultados
	@Test
	public void deveTerVariosResultados() {
		Jogo jogo = new CriadorDeJogo()
        .para("Cata moedas")
        .resultado(new Participante("Nelson"), 150.0)
        .resultado(new Participante("Pedro"), 200.0)
        .constroi();
		
		//O teste inicia inserindo dois resultados e participantes, com duas métricas diferentes
		//Em seguida é testado q quantidade de resultados inseridos, e se as métricas coincidem com as inseridas no inicio
		assertEquals(2, jogo.getResultados().size());
		assertEquals(150.0, jogo.getResultados().get(0).getMetrica(), 0.00001);
		assertEquals(200.0, jogo.getResultados().get(1).getMetrica(), 0.00001);
		
	}
	//Cenário de teste, um caça peças que não aceita mais de um resultado de um mesmo participante
	@Test
	public void naoDeveAceitarDoisResultadosDoMesmoParticipante(){

		Jogo jogo = new Jogo("Ca�a pe�as");
		Participante leonardo = new Participante("Leonardo");
		
		//Insere um novo resultado para o participante leonardo
		jogo.anota(new Resultado(leonardo, 500.0));
		
		//Insere um segundo resultado para o participante leonardo
		jogo.anota(new Resultado(leonardo, 600.0));
		
		//Testa a quantidade de resultados por participante e verifica se a pontuação foi mantida foi a primeira
		assertEquals(1, jogo.getResultados().size());
        assertEquals(500, jogo.getResultados().get(0).getMetrica(), 0.00001);
		}
		//Gustavo Hideo Takao
		@Test
		public void oJogadorDeveTerUmNome()
		{
			Jogo jogo = new Jogo("Jogo da velha");
			Participante participante = new Participante("Jorginho");

			jogo.anota(new Resultado(participante, 0));
			assertNotEquals(null, jogo.getResultados().get(0).getParticipante().getNome());
			assertNotEquals("", jogo.getResultados().get(0).getParticipante().getNome());
		}
}