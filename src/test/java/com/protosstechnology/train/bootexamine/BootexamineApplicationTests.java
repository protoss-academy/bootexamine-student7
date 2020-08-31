package com.protosstechnology.train.bootexamine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.protosstechnology.train.bootexamine.entity.Document;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BootexamineApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private Document document;

    @Test
    @Order(1)
    void addDocument_thenOk() throws Exception {
        Document document = new Document();
        document.setDocumentNumber("A2-101");
        document.setDescription("Test Documents");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(document);

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/document")
                .contentType(MediaType.APPLICATION_JSON).content(json);

        mockMvc.perform(mockHttpServletRequestBuilder).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(3)
    void getDocument_thenOk() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/document/{id}", "1");
        mockMvc.perform(mockHttpServletRequestBuilder);
    }

    @Test
    @Order(3)
    void updateDocument_thenOk() throws Exception {
        Document document = new Document();
        document.setDocumentNumber("A2-102");
        document.setDescription("Edited Test Documents");

        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(document);
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/document/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON).content(json);
        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @Order(4)
    void DeleteDocument_thenOk() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/document/{id}", "1");
        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk());
    }
}
