package br.com.aluguel.test;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.ResultActions;

import br.com.aluguel.entity.cadastral.aluguel.Categoria;
import br.com.aluguel.json.bean.cadastral.CadastrarCategoria;
import br.com.aluguel.servico.service.cadastral.CategoriaService;
import br.com.aluguel.util.DefaultTestAnnotations;
import br.com.aluguel.util.IntegrationTestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@DefaultTestAnnotations
@SpringBootTest
public class CategoriaControllerTest extends IntegrationTestUtil {

	private final static String URL = "/api/categoria";

	@Autowired
	CategoriaService service;
	
	Categoria categoria = null;
	Categoria categoriaFilha = null;

	@Test
	public void shouldGetCategoriaByChaveById() throws IOException, Exception  {
		categoria = service.save(getCategoria());
		
		String filter = URL + "/"+categoria.getId();

		mockMvc.perform(get(filter).session(session)
				.headers(getHeaderToken())
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
						.andExpect(status().isOk())
							.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}
	

	@Test
	public void shouldGetCategoriaByChaveByIdCategoriaPai() throws IOException, Exception  {
		
		categoria = service.save(getCategoria());
		
		categoriaFilha = service.save(getCategoriaFilha());
		
		String filter = URL + "/topParent/"+categoria.getId();

		mockMvc.perform(get(filter).session(session)
				.headers(getHeaderToken())
					.param("page", DEFAULT_PAGE)
					.param("size", DEFAULT_SIZE_PAGE)
					.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
						.andExpect(status().isOk())
							.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}
	
	@Test
	public void shouldCreateCategoria() throws IOException, Exception {
		CadastrarCategoria model = new CadastrarCategoria("Eletronicos","E");
		
		ResultActions result = cadastrarCategoria(model);
		
		result.andExpect(status().isCreated()).andExpect(status().isCreated())
		.andExpect(content().contentType(MEDIA_TYPE_JSON_UTF_8_SEM_ESPACO))
		.andExpect(content().string(containsString("Categoria cadastrada com sucesso")));
	}
	
	
	private ResultActions cadastrarCategoria(CadastrarCategoria model) throws IOException, Exception {
		
		return mockMvc.perform(post(URL).session(session).headers(getHeaderToken())
				.contentType(MEDIA_TYPE_JSON_UTF_8_SEM_ESPACO).content(convertObjectToJson(model)));
	}

	public Categoria getCategoria() {
		categoria = new Categoria();
		categoria.setNome("Eletronicos");
		categoria.setTipo("E");
		return categoria;
	}


	public Categoria getCategoriaFilha() {
		categoriaFilha = new Categoria();
		categoriaFilha.setNome("Televisão");
		categoriaFilha.setTipo("E");
		categoriaFilha.setCategoriaPai(categoria);
		categoriaFilha.setIdCategoriaPai(categoria.getId());
		return categoriaFilha;
	}

}
