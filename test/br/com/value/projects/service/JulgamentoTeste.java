package br.com.value.projects.service;
import org.junit.*;

import br.com.value.projects.builder.CriadorDeJogo;
import br.com.value.projects.dominio.Participante;
import br.com.value.projects.dominio.Jogo;
import br.com.value.projects.dominio.Resultado;




public class JulgamentoTeste {
   

	private Juiz juiz;
	private Participante joao;
	private Participante pedro;
	private Participante katia;
	private Participante maria;
	
	@Before
	public void criaJuiz(){

		this.juiz = new Juiz();
		this.joao = new Participante("Joao");
		this.pedro = new Participante("Pedro");
		this.katia = new Participante("Katia");
		this.maria =new Participante("Maria");
			
		}
	
	//Cenário de teste: Jogo de derruba barreiras com julgamento do primeiro e ultimo colocado
	@Test
	public void deveJulgarPrimeiroEultimoColocado () {
		
		
		Jogo jogo = new Jogo("Derruba barreiras");
		// Inserção de 4 resultados de 4 participantes diferentes
		jogo.anota(new Resultado(joao, 90.0));
		jogo.anota(new Resultado(pedro, 91.0));
		jogo.anota(new Resultado(katia, 93.0));
		jogo.anota(new Resultado(maria, 94.0));
		
		
		juiz.julga(jogo);
		
		double vencedorJogo = 94;
		double ultimoColocadoJogo = 90;
		//Testa se o primeiro colocado foi o participante com maior pontuação e se o ultimo foi o com menor pontuação
		Assert.assertEquals(vencedorJogo, juiz.getPrimeiroColocado(),0.00001);
		Assert.assertEquals(ultimoColocadoJogo, juiz.getUltimoColocado(),0.00001);
	}
	//Cenário: Retorna uma exceção quando um jogo sem resultado for julgado por um juiz 
	@Test(expected=RuntimeException.class)
	public void naoDeveJulgarSemResultado() {
	    Jogo jogo = new CriadorDeJogo()
	        .para("Ca�a pe�as")
	        .constroi();

	    juiz.julga(jogo);
	}

}
