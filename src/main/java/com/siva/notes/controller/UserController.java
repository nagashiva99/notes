package com.siva.notes.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.siva.notes.model.Note;
import com.siva.notes.model.User;
import com.siva.notes.repository.NoteRepository;
import com.siva.notes.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	NoteRepository noteRepository;

	@GetMapping("/register")
    public String showSignUpForm(User user) {
        return "register";
    }
	
	@GetMapping("/login")
    public String showLoginForm(User user) {
        return "login";
    }
	
	@GetMapping("/")
    public String index(User user) {
        return "register";
    }
	
	@GetMapping("/addnote")
    public String addNote(Note note) {
		User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		note.setCreatorId(loggedInUser.getId());
		note.setModifierId(loggedInUser.getId());
		
        return "addnote";
    }
	
	@PostMapping("/note")
    public String index(@Valid Note note, BindingResult result, Model model) {
		if (result.hasErrors()) {
            return "addnote";
        }
		noteRepository.save(note);
		model.addAttribute("notes", noteRepository.findByCreatorId(1));
		
        return "notes";
    }
	
	@PostMapping("/welcome")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
         
        userRepository.save(user);
        model.addAttribute("notes", noteRepository.findAll());
        return "notes";
    }
	
	@PostMapping("/loginsuccess")
    public String login(User user, Model model) {         
        model.addAttribute("notes", noteRepository.findAll());
        return "notes";
    }
}
