package com.example.controllers;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.testcontainers.junit.jupiter.Testcontainers;

import com.example.model.Book;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	
	private Book mockBook() {
		Book testBook = new Book();
		testBook.setAuthor("Test author");
		testBook.setDescription("Test description");
		testBook.setIsbn("Test isbn");
		testBook.setName("Test name");
		testBook.setPublisher("Test publisher");
		testBook.setYear(2020);
		return testBook;
	}

	@Test
	void createBook() throws Exception {
		Book testBook = mockBook();

		String json = objectMapper.writeValueAsString(testBook);
		MvcResult mvcResult = mockMvc.perform(post("/book").contentType(MediaType.APPLICATION_JSON).content(json))
				.andDo(print()).andExpect(status().isCreated()).andReturn();

		Book bookResult = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Book.class);

		assertThat(bookResult.getId(), notNullValue());
		assertThat(bookResult.getAuthor(), equalTo(testBook.getAuthor()));
		assertThat(bookResult.getDescription(), equalTo(testBook.getDescription()));
		assertThat(bookResult.getIsbn(), equalTo(testBook.getIsbn()));
		assertThat(bookResult.getName(), equalTo(testBook.getName()));
		assertThat(bookResult.getPublisher(), equalTo(testBook.getPublisher()));
		assertThat(bookResult.getYear(), equalTo(testBook.getYear()));
	}
	
	@Test
	void findBook() throws Exception {
		Book testBook = mockBook();
		String json = objectMapper.writeValueAsString(testBook);
		mockMvc.perform(post("/book")
					.contentType(MediaType.APPLICATION_JSON)
					.content(json))
					.andDo(print())
					.andExpect(status().isCreated())
					.andReturn();
		
		mockMvc.perform(get("/book").param("page", "0").param("size", "1").param("query", "ascscs"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.content", empty()))
					.andReturn();
		
		mockMvc.perform(get("/book").param("page", "0").param("size", "1").param("query", "descript"))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.content", hasSize(1)))
					.andReturn();
		
	}

}
