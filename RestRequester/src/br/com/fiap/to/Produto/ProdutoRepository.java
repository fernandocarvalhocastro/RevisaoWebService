package br.com.fiap.to.Produto;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import br.com.fiap.entity.Produto;

public class ProdutoRepository {
	
	private Client client = Client.create();
	
	private static final String URL = "http://localhost:8080/RestProvider/rest/produto/";
	
	public void cadastrar(Produto produto) throws Exception{
		WebResource resource = client.resource(URL);
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,produto);
		
		if(response.getStatus()!=201){
			throw new Exception("Erro");
		}
	}
	
	public void atualizar(Produto produto) throws Exception{
		WebResource resource = client.resource(URL + produto.getCodigo());
		ClientResponse response = resource.type(MediaType.APPLICATION_JSON).put(ClientResponse.class,produto);
		if(response.getStatus()!=200){
			throw new Exception("Erro");
		}
	}
}
