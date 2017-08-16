package br.com.triadworks.lanceunico.service;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import static org.mockito.Mockito.*;

import br.com.triadworks.lanceunico.builder.CriadorDePromocao;
import br.com.triadworks.lanceunico.dao.PromocaoDao;
import br.com.triadworks.lanceunico.modelo.Promocao;
import br.com.triadworks.lanceunico.util.DateUtils;

public class EncerradorDePromocaoTest {

	@Test
	public void deveEncerrarPromocoesForaDaVigencia(){
		//montar cenario
		Date antiga = DateUtils.novaData("01/01/1999");

		Promocao ipad = new CriadorDePromocao()
				.para("ipad")
				.naData(antiga)
				.cria();

		Promocao iphone = new CriadorDePromocao()
				.para("iphone")
				.naData(antiga)
				.cria();
		List<Promocao> promocoes = Arrays.asList(iphone,ipad);

		PromocaoDao daoFalso =mock(PromocaoDao.class);
		//caso seja chamado um metodo que nao foi ensinado para o mock e ele retornar uma lista ele irá retornar 
		// uma lista vazia
		when(daoFalso.abertas()).thenReturn(promocoes);

		//acao
		EncerradorDePromocoes encerrador = new EncerradorDePromocoes(daoFalso);
		encerrador.encerra();

		//validacao
		assertTrue("ipad encerrada:",ipad.isEncerrada());
		assertTrue("iphone encerrada:",iphone.isEncerrada());


	}


	@Test
	public void deveAtualizarPromocoesEncerradas(){
		//montar cenario
		Date antiga =DateUtils.novaData("01/01/2015");

		Promocao iphone = new CriadorDePromocao()
				.para("iphone")
				.naData(antiga)
				.cria();
		List<Promocao> promocoes = Arrays.asList(iphone);

		PromocaoDao daoFalso =mock(PromocaoDao.class);
		//caso seja chamado um metodo que nao foi ensinado para o mock e ele retornar uma lista ele irá retornar 
		// uma lista vazia
		when(daoFalso.abertas()).thenReturn(promocoes);

		//acao
		EncerradorDePromocoes encerrador = new EncerradorDePromocoes(daoFalso);
		encerrador.encerra();

		//validacao
		verify(daoFalso,times(1)).atualiza(iphone);

	}

	@Test
	public void deveEncerrarAsPromocoesRestantesMesmoEmCasoDeFalhas(){

		//montar cenario
		Date antiga =DateUtils.novaData("01/01/2015");

		

		
		Promocao iphone = new CriadorDePromocao()
				.para("iphone")
				.naData(antiga)
				.cria();
		
		Promocao ipad = new CriadorDePromocao()
				.para("ipad")
				.naData(antiga)
				.cria();
		
		List<Promocao> promocoes = Arrays.asList(iphone,ipad);

		PromocaoDao daoFalso =mock(PromocaoDao.class);
		when(daoFalso.abertas()).thenReturn(promocoes);
		doThrow(new RuntimeException()).when(daoFalso).atualiza(iphone);
		
		
		//acao
		EncerradorDePromocoes encerrador = new EncerradorDePromocoes(daoFalso);
		int encerrados =encerrador.encerra();

		//validacao
		verify(daoFalso).atualiza(ipad);
		assertEquals("total",1, encerrados);


	}
}
