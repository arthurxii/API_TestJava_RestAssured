package br.com.conductor;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TesteAPIRest {
	
	@Test
	public void TesteConexaoStatusCode() {
		RestAssured.baseURI = "http://52.191.254.38/desafio/produtos";
		Response req = RestAssured.given().header("Token", "desafio").get("");
		int statuscode = req.getStatusCode();
		System.out.println("Status Code " + statuscode);
		System.out.println("Tipo de retorno " + req.contentType());
		assertThat(statuscode,Matchers.equalTo(200));
		
	}
	
	@Test
	public void TesteGetProdutos() {
		RestAssured.baseURI = "http://52.191.254.38/desafio/produtos";
		Response req = RestAssured.given().header("Token", "desafio").get("");
		String dados = req.asString();
		System.out.println();
		System.out.println("Produtos:" + dados);
		System.out.println("Tempo de Resposta: " + req.getTime());
		System.out.println();
	}
	
	
	@Test
	public void TesteGetProdutoNome() {
		RestAssured.baseURI = "http://52.191.254.38/desafio/produtos";
		Response req = RestAssured.given().header("Token", "desafio").get();
		int statuscode = req.getStatusCode();
		assertThat(statuscode,Matchers.equalTo(200));
		req.then().body("nome", Matchers.hasItem("Pipoca"));
	}
	
	@Test
	public void TesteGetProdutoDescricao() {
		RestAssured.baseURI = "http://52.191.254.38/desafio/produtos";
		RestAssured.given().when().header("Token", "desafio").get()
		.then()
		.assertThat()
		.log()
		.all()
		.statusCode(200)
		.and()
		.body("descricao", Matchers.hasItem("Pipoca doce"));
	}
	
	@Test
	public void TesteTokenInvalido() {
		RestAssured.baseURI = "http://52.191.254.38/desafio/produtos";
		Response req = RestAssured.given().header("Tokenqa", "desafio").get();
		int statuscode = req.getStatusCode();
		assertThat(statuscode,Matchers.equalTo(401));
		System.out.println("Erro: " + req.getStatusCode());
		
	}
	
}
