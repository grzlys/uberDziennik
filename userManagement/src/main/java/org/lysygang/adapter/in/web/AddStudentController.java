package org.lysygang.adapter.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lysygang.application.domain.service.AddStudentJobLauncher;
import org.lysygang.application.domain.service.AddStudentService;
import org.lysygang.application.port.in.AddStudentCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AddStudentController {

    private final AddStudentService addStudentService;
    private final AddStudentJobLauncher addstudentJobLauncher;


    @PostMapping("/student/add")
    public int addNewStudent(@RequestBody NewStudentRequest request) {
        return addStudentService.addNewStudent(new AddStudentCommand(request.firstName(), request.lastName()));
    }

    @PostMapping(value = "/students/add", consumes = "multipart/form-data")
    public ResponseEntity<String> addStudents(@RequestBody MultipartFile file) {
        try {
            addstudentJobLauncher.runJob(file);
            return ResponseEntity.ok(STR."Job was executed successfully for file: \{file.getOriginalFilename()}");
        } catch (Exception ex) {
            log.error("Error during job execution:", ex);
            return ResponseEntity.internalServerError().body(STR."Error during job execution: \{ex.getMessage()}");
        }
    }

}
