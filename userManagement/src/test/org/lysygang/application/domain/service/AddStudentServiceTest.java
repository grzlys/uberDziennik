package org.lysygang.application.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lysygang.adapter.out.persistence.repository.StudentPersistenceAdapter;
import org.lysygang.application.port.in.AddStudentCommand;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AddStudentServiceTest {

    @InjectMocks
    private AddStudentService service;

    @Mock
    private StudentPersistenceAdapter studentPersistenceAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldReturnCreatedStudentIdWithSuccess() {
        // given
        AddStudentCommand addStudentCommand = new AddStudentCommand("testName", "testLastName");
        BDDMockito.given(studentPersistenceAdapter.saveStudent(addStudentCommand)).willReturn(20);

        // expect
        assertThat(service.addNewStudent(addStudentCommand)).isEqualTo(20);
    }
}