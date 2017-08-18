package br.com.triadworks.lanceunico.dao;

import javax.persistence.Query;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;



import br.com.triadworks.lanceunico.modelo.Cliente;
import br.com.triadworks.lanceunico.util.JPAUtil;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class ClienteDaoTest {

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
	public void deveEncontrarClientePorEmail(){
		
		
		
		Cliente cliente = new Cliente("principe do oceano","principe@oceano.com");
		entityManager.persist(cliente);
		
		ClienteDao dao = new ClienteDao(entityManager);
		Cliente clienteDoBanco = dao.buscaPorEmail("principe@oceano.com");
		
		
		assertEquals(cliente.getNome(),clienteDoBanco.getNome());
		assertEquals(cliente.getNome(),clienteDoBanco.getNome());
		
		
	}
	
	@Test
	public void naoDeveEncontrarClientePorEmail(){
		
		
		
		
		
		ClienteDao dao = new ClienteDao(entityManager);
		Cliente clienteDoBanco = dao.buscaPorEmail("principe222@oceano.com");
		
		
		assertNull(clienteDoBanco);
		
		
	}
}
