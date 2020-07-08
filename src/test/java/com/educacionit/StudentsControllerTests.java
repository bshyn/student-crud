package com.educacionit;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.educacionit.student.api.entity.StudentEntity;
import com.educacionit.student.api.exception.NotFoundException;
import com.educacionit.student.api.model.StudentModel;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnNotFound() throws Exception {
        this.mockMvc.perform(get("/students/1")).andExpect(status().isNotFound());
    }

    @Test
    void nameShouldBeFranco() throws Exception{
        this.mockMvc.perform(get("/students/12123123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Franco"));
    }

    @Test
    void shouldReturnConflict() throws Exception {
        Gson gson = new Gson();

        StudentModel model = new StudentModel();
        model.setDni("12123123");

        String jsonModel = gson.toJson(model);

        this.mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonModel))
                .andExpect(status().isConflict());
    }

}
