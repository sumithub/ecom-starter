package com.cakefactory.catalog;

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

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
class CatalogControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	private WebClient webClient;

	@MockBean
	CatalogService catalogService;

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
		final String expectedName = "Carrot Cake";
		//given
		when(catalogService.getAllProducts()).thenReturn(Collections.singletonList(new Product(expectedName, 3.45)));

		//when
		HtmlPage page = this.webClient.getPage("/");
		List<HtmlDivision> items = page.getByXPath("//div[@class='card-body']");

		// then
		assertThat(items.get(0).getFirstElementChild().getVisibleText()).isEqualTo(expectedName);
	}
}
