package br.com.aluguel.util;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.apache.http.cookie.Cookie;
import org.junit.Before;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.aluguel.StatefullRestTemplate;
import br.com.aluguel.WebConfig;
import br.com.util.entity.AcessoUsuario;
import br.com.util.json.bean.Login;
import br.com.util.security.SecurityUser;

public class IntegrationTestUtil {
	public static final String MEDIA_TYPE_JSON_UTF_8_SEM_ESPACO = MediaType.APPLICATION_JSON_UTF8_VALUE.trim()
			.replace(" ", "");
	
	public static final String DEFAULT_SIZE_PAGE = "20";
	public static final String DEFAULT_PAGE = "0";
	public static final String CPF_VALIDO = "12017773484";
	public static final String CPF_VALIDO_2 = "03959816111";
	public static final Integer PESSOA_FISICA =1;
	public static final Integer PESSOA_JURIDICA =2;
	
	@Autowired
	public WebApplicationContext wac;
	public String cookies = null;
	public HttpHeaders userHeaders;
	public String sessionValue;
	private ObjectMapper mapper;

	public MockMvc mockMvc;
	public MockHttpSession session;
	public StatefullRestTemplate rest = new StatefullRestTemplate();
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		this.session = new MockHttpSession(wac.getServletContext(), UUID.randomUUID().toString());
		this.mapper = new WebConfig().objectMapper();
	}

	public static final String BASE_URL = "http://localhost:9090";
	public static final String URL_AUTH = "http://localhost:8888";
	
	@SuppressWarnings("unchecked")
	public String getToken() {
		Login login = new Login("thiago.vieira", "3fd4b097");
		ResponseEntity<HashMap<String, String>> response = this.rest.postForEntity(URL_AUTH + "/auth/login", login,
				Map.class, Collections.EMPTY_MAP);
		this.cookies = response.getHeaders().getFirst("Set-Cookie");
		List<Cookie> cookiesList = this.rest.getCookieStore().getCookies();
		for (Cookie c : cookiesList) {
			if ("SESSION".equals(c.getName())) {
				this.sessionValue = c.getValue();
			}
		}
		String token = response.getBody().get("token");
		this.userHeaders = response.getHeaders();
		return token;
	}
	
	public byte[] convertObjectToJsonBytes(Object object) throws IOException {
		return this.mapper.writeValueAsBytes(object);
	}

	public String convertObjectToJson(Object object) throws IOException {
		String json = this.mapper.writeValueAsString(object);
		return json;
	}

	public String generateRandomString(int length) {
		Random random = new Random();
		return random.ints(48, 122).mapToObj(i -> (char) i).limit(length)
				.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	}

	public HttpHeaders getHeaderToken() {
		String token = getToken();
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		requestHeaders.add("Authorization", token);
		return requestHeaders;
	}
	
	private void setUserSession(AcessoUsuario usuario) {
		SecurityUser securityUser = new SecurityUser(usuario);
		session.setAttribute("sec-user", securityUser);
	}

	public static String[] getNullPropertyNames(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

		Set<String> emptyNames = new HashSet<String>();
		for (java.beans.PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null)
				emptyNames.add(pd.getName());
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	public SecurityUser getAthenticatedUser(HttpSession session) {
		Object obj = session.getAttribute("sec-user");
		SecurityUser user = null;
		if (obj instanceof SecurityUser) {
			user = (SecurityUser) obj;
		}
		return user;
	}
	
}