package org.lysygang.application.port.out;

import org.lysygang.application.port.in.AddStudentCommand;

public interface SaveStudentPort {

    int saveStudent(AddStudentCommand studentCommand);

}
