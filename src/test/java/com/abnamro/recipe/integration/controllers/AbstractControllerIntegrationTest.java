package com.abnamro.recipe.integration.controllers;

import com.abnamro.recipe.utils.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Ignore
public class AbstractControllerIntegrationTest {
    @Autowired
    protected MockMvc mockMvc;

    protected ObjectMapper objectMapper = TestUtils.createObjectMapperInstance();


    protected String toJson(Object obj) throws IOException {
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> List<T> getListFromMvcResult(MvcResult result, Class<T> listElementClass) throws IOException {
        return objectMapper.readValue(result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructCollectionType(List.class, listElementClass));
    }

    protected <T> T getFromMvcResult(MvcResult result, Class<T> objectClass) throws IOException {
        return objectMapper.readValue(result.getResponse().getContentAsString(),
                objectMapper.getTypeFactory().constructType(objectClass));
    }

    protected <T> T readByJsonPath(MvcResult result, String jsonPath) throws UnsupportedEncodingException {
        return JsonPath.read(result.getResponse().getContentAsString(), jsonPath);
    }

    protected ResultActions performGet(String path) throws Exception {
        return mockMvc.perform(get(path));
    }

    protected ResultActions performPost(String path, Object request) throws Exception {
        return mockMvc.perform(post(path)
                .content(toJson(request))
                .contentType(MediaType.APPLICATION_JSON));
    }

    protected ResultActions performPatch(String path, Object request) throws Exception {
        return mockMvc.perform(patch(path)
                .content(toJson(request))
                .contentType(MediaType.APPLICATION_JSON));
    }

    protected ResultActions performDelete(String path, Object request) throws Exception {
        return mockMvc.perform(delete(path)
                .content(toJson(request))
                .contentType(MediaType.APPLICATION_JSON));
    }
    protected ResultActions performDelete(String path) throws Exception {
        return mockMvc.perform(delete(path)
                .contentType(MediaType.APPLICATION_JSON));
    }

    protected ResultActions performDelete(String path, Pair<String, String> param) throws Exception {
        return mockMvc.perform(delete(path)
                .contentType(MediaType.APPLICATION_JSON)
                .param(param.getFirst(), param.getSecond()));
    }

}
