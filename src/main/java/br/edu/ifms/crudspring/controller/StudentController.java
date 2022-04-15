package br.edu.ifms.crudspring.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.edu.ifms.crudspring.Services.StudentService;
import br.edu.ifms.crudspring.model.Student;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/student")
@Slf4j
public class StudentController {

    @Autowired
    StudentService studentService;

    @PostMapping("/")
    public String save(@ModelAttribute("student") Student student) {
        studentService.save(student);
        return "redirect:/student/";
    }

    @GetMapping("/")
    public String locAll(Model model) {
        List<Student> students = studentService.getStudents();
        model.addAttribute("students", students);
        return "index";
    }

    @GetMapping("/mudar-senha")
    public String mudarSenha() {
        return "mudar_senha";
    }

    @GetMapping("/cadastrar")
    public String newStudent(Model model) {
        model.addAttribute("student", new Student());
        return "cadastrar";
    }
    @GetMapping("/remove/{id}")
    public String removeStudent(@PathVariable("id") UUID id){
        log.info("id =" + id);
        studentService.delete(id);
        return "redirect:/student/";
    
    }

    //chama a pag edit-student.html
    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") UUID id, Model model){

        //deveria ter um try para ver se realmente existe o id
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "edit-student";
    
    }
    
    @PostMapping("/update/{id}")

    public String updateStudent(@PathVariable("id") UUID id, @ModelAttribute Student student, Model model){
        
        studentService.updateStudent(student);

    

        return "redirect:/student/";

    }

}
