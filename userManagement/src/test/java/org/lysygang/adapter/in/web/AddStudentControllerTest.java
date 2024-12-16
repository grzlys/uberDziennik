package org.lysygang.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.lysygang.adapter.out.persistence.repository.StudentRepository;
import org.lysygang.application.domain.service.AddStudentJobLauncher;
import org.lysygang.application.domain.service.AddStudentService;
import org.lysygang.application.port.in.AddStudentCommand;
import org.lysygang.config.ServiceConfig;
import org.lysygang.config.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(controllers = AddStudentController.class)
@ContextConfiguration(classes = {ServiceConfig.class, WebConfig.class})
class AddStudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddStudentService willSaveStudent;

    @MockBean
    private AddStudentJobLauncher addstudentJobLauncher;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    void controllerShouldReturnCreatedStudentIdProvidedByService() throws Exception {
        // given
        NewStudentRequest newStudentRequest = new NewStudentRequest("testFirstName", "testLastName");

        AddStudentCommand addStudentCommand = new AddStudentCommand("testFirstName", "testLastName");
        given(willSaveStudent.addNewStudent(addStudentCommand)).willReturn(21);

        // expect
        var mvcResult = mockMvc.perform(
                        post("/student/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(asJsonString(newStudentRequest)))
                .andExpect(status().isOk()).andReturn();

        assertEquals(21, Integer.valueOf(mvcResult.getResponse().getContentAsString()));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}