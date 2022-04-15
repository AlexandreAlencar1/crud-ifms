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
import br.edu.ifms.crudspring.Services.ProfessorService;
import br.edu.ifms.crudspring.model.Professor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/professor")
@Slf4j
public class ProfessorController {

    @Autowired
    ProfessorService professorService;

    @PostMapping("/")
    public String save(@ModelAttribute("professor") Professor professor) {
        professorService.save(professor);
        return "redirect:/professor/";
    }

    @GetMapping("/")
    public String locAll(Model model) {
        List<Professor> professors = professorService.getProfessors();
        model.addAttribute("professors", professors);
        return "listarprofessor";
    }

    @GetMapping("/mudar-senha")
    public String mudarSenha() {
        return "mudar_senha";
    }

    @GetMapping("/cadastrar")
    public String newProfessor(Model model) {
        model.addAttribute("professor", new Professor());
        return "cadastrarprofessor";
    }
    @GetMapping("/remove/{id}")
    public String removeProfessor(@PathVariable("id") UUID id){
        log.info("id =" + id);
        professorService.delete(id);
        return "redirect:/professor/";
    
    }

    //chama a pag edit-professor.html
    @GetMapping("/edit/{id}")
    public String editProfessor(@PathVariable("id") UUID id, Model model){

        //deveria ter um try para ver se realmente existe o id
        Professor professor = professorService.findById(id);
        model.addAttribute("professor", professor);
        return "edit-professor";
    
    }
    
    @PostMapping("/update/{id}")

    public String updateProfessor(@PathVariable("id") UUID id, @ModelAttribute Professor professor, Model model){
        
        professorService.updateProfessor(professor);

    

        return "redirect:/professor/";

    }

}
