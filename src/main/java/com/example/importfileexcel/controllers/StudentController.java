package com.example.importfileexcel.controllers;

import com.example.importfileexcel.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/importOrderExcel")
    public String importExcelFile(@RequestParam("file") MultipartFile files, Model model) throws IOException, ParseException {
        studentService.importExcelFile(files);
        return "redirect:search";
    }
    @GetMapping("/")
    public String hello() {
        return "index";
    }

    @GetMapping("/search")
    public String search(Model model) {
        model.addAttribute("studentList", studentService.getStudents());
        return "search";
    }

    @PostMapping("/search")
    public String searchStudent(@RequestParam(name = "fullName", defaultValue = "") String fullName, @RequestParam(name = "studentCode", defaultValue = "") String studentCode, Model model) {
        model.addAttribute("studentList", studentService.searchStudentBy(studentCode, fullName));
        return "search";

    }
}


