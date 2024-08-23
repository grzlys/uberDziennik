package org.lysygang.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lysygang.application.domain.service.AddStudentService;
import org.lysygang.application.port.in.AddStudentCommand;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AddStudentController {

    private final AddStudentService addStudentService;

    @PostMapping("/student/add")
    public int addNewStudent(@RequestBody NewStudentRequest request){
        return addStudentService.addNewStudent(new AddStudentCommand(request.firstName(), request.lastName()));
    }

}
