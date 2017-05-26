package br.com.fiap.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import br.com.fiap.bo.ProdutoBO;
import br.com.fiap.entity.Produto;
import br.com.fiap.exception.CommitErrorException;

@Path("/produto")
public class ProdutoResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response cadastrar(Produto produto, @Context UriInfo uriInfo){
		ProdutoBO bo = new ProdutoBO();
		try {
			bo.cadastrar(produto);
			UriBuilder builder = uriInfo.getAbsolutePathBuilder();
			builder.path(Integer.toString(produto.getCodigo()));
			return Response.created(builder.build()).build();
		} catch (CommitErrorException e) {
			e.printStackTrace();
			return Response.serverError().build();	
		}
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response atualizar(Produto produto, @PathParam("id") int codigo){
		ProdutoBO bo = new ProdutoBO();
		produto.setCodigo(codigo);
		try {
			bo.atualizar(produto);
			return Response.ok().build();
		} catch (CommitErrorException e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> listar() throws Exception{
		ProdutoBO bo = new ProdutoBO();
		try{
			return bo.listar();
		}catch(Exception e){
			e.printStackTrace();
			throw new Exception("Falha ao listar");	
		}
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Produto buscar(@PathParam("id") int codigo){
		return new ProdutoBO().buscar(codigo);
	}
}
