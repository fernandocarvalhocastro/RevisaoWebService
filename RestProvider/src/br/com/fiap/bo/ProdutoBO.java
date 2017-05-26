package br.com.fiap.bo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import br.com.fiap.dao.ProdutoDAO;
import br.com.fiap.dao.impl.ProdutoDAOImpl;
import br.com.fiap.entity.Produto;
import br.com.fiap.exception.CommitErrorException;
import br.com.fiap.singleton.EntityManagerFactorySingleton;

public class ProdutoBO {

	private EntityManagerFactory fabrica;

	public ProdutoBO() {
		fabrica = EntityManagerFactorySingleton.getInstance();
	}

	public Produto buscar(int id) {
		EntityManager em = fabrica.createEntityManager();
		ProdutoDAO dao = new ProdutoDAOImpl(em);
		Produto produto = dao.buscar(id);
		em.close();
		return produto;
	}

	public List<Produto> listar() {
		EntityManager em = fabrica.createEntityManager();
		ProdutoDAO dao = new ProdutoDAOImpl(em);
		List<Produto> lista = dao.listar();
		em.close();
		return lista;
	}

	public void atualizar(Produto produto) throws CommitErrorException {
		EntityManager em = fabrica.createEntityManager();
		ProdutoDAO dao = new ProdutoDAOImpl(em);
		
		try {
			dao.atualizar(produto);
			dao.commit();
		} catch (CommitErrorException e) {
			e.printStackTrace();
			throw new CommitErrorException(e);
		} finally {
			em.close();
		}
	}

	public void delete(int codigo) throws Exception {
		EntityManager em = fabrica.createEntityManager();
		ProdutoDAO dao = new ProdutoDAOImpl(em);
		try {
			dao.excluir(codigo);
			dao.commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			em.close();
		}
	}

	public void cadastrar(Produto produto) throws CommitErrorException {
		EntityManager em = fabrica.createEntityManager();
		ProdutoDAO dao = new ProdutoDAOImpl(em);
		try {
			dao.cadastrar(produto);
			dao.commit();
		} catch (CommitErrorException e) {		
			e.printStackTrace();
			throw new CommitErrorException(e);
		} finally {
			em.close();
		}
	}
}
