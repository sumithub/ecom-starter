package com.cakefactory;

import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import static org.mockito.Mockito.when;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.gargoylesoftware.htmlunit.*;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class IndexControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private WebClient webClient;

	@MockBean
	CatalogService catalogService;

	private List<Product> products;

	@BeforeEach
	public void setup() {

		products = Arrays.asList(
				new Product("abcr", "All Butter Croissant", 0.75),
				new Product("ccr", "Chocolate Croissant", 0.95),
				new Product("b", "Fresh Baguette", 1.60),
				new Product("rv", "Red Velvet", 3.95),
				new Product("vs", "Victoria Sponge", 5.45),
				new Product("cc", "Carrot Cake", 3.45));

		when(catalogService.findAll()).thenReturn(products);
	}

	@Test
	@DisplayName("index page returns the landing page")
	void returnsLandingPage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("Cake Factory")));
	}

	@Test
	@DisplayName("index page shows list of catalog items")
		void index_page_display_catalog_items() throws Exception {
		HtmlPage page = this.webClient.getPage("/");
		List<HtmlDivision> items = page.getByXPath("//div[@class='card-body']");
		assertThat(items.size() == 6);
		assertThat(items.get(0).getFirstElementChild().getVisibleText()).isEqualTo(products.get(0).getName());
		assertThat(items.get(1).getFirstElementChild().getVisibleText()).isEqualTo(products.get(1).getName());
		assertThat(items.get(2).getFirstElementChild().getVisibleText()).isEqualTo(products.get(2).getName());
		assertThat(items.get(3).getFirstElementChild().getVisibleText()).isEqualTo(products.get(3).getName());
		assertThat(items.get(4).getFirstElementChild().getVisibleText()).isEqualTo(products.get(4).getName());
		assertThat(items.get(5).getFirstElementChild().getVisibleText()).isEqualTo(products.get(5).getName());
	}
}
