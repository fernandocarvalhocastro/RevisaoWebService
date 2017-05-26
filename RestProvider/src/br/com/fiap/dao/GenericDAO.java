package br.com.fiap.dao;

import java.util.List;

import br.com.fiap.exception.CodigoInvalidoException;
import br.com.fiap.exception.CommitErrorException;

public interface GenericDAO<T,K> {

	void cadastrar(T entidade);
	void atualizar(T entidade);
	void excluir(K codigo) throws CodigoInvalidoException;
	T buscar(K codigo);
	List<T> listar();
	void commit() throws CommitErrorException;
	
}
