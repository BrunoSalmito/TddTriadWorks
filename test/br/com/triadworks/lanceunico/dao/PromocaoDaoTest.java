package br.com.triadworks.lanceunico.dao;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import br.com.triadworks.lanceunico.builder.CriadorDePromocao;
import br.com.triadworks.lanceunico.modelo.Promocao;
import br.com.triadworks.lanceunico.modelo.Status;
import br.com.triadworks.lanceunico.util.JPAUtil;

public class PromocaoDaoTest {

	EntityManager entityManager ;
	@Before
	public void setUp(){
		entityManager = new JPAUtil().getEntityManager();
		entityManager.getTransaction().begin();
	}
	
	@After
	public void tearsDown(){
		entityManager.getTransaction().rollback();
		entityManager.close();
	}
	
	@Test
	public void deveContarPromocoesEncerradas(){
		Promocao promocao1 = new CriadorDePromocao()
				.para("xbox")
				.comStatus(Status.ABERTA)
				.cria();
	
		Promocao promocao2 = new CriadorDePromocao()
				.para("xbox")
				.comStatus(Status.ENCERRADA)
				.cria();
		
		entityManager.persist(promocao1);
		entityManager.persist(promocao2);
		
		PromocaoDao dao = new PromocaoDao(entityManager);
		Long total = dao.totalDeEncerradas();
		
		Long totalEsperado =1L;
		assertEquals(totalEsperado,total);
	
	}
	
	@Test
	public void deveRemoverUmaPromocao(){
		Promocao promocao1 = new CriadorDePromocao()
				.para("xbox")
				.cria();
	
		
		entityManager.persist(promocao1);
		
		PromocaoDao dao = new PromocaoDao(entityManager);
		dao.remove(promocao1);
		

		Promocao promocaoDoBanco = dao.carrega(promocao1.getId());
		
		assertNull(promocaoDoBanco);
	
	}
}
