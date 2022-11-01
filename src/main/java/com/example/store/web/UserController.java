package com.example.store.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.store.domain.User;
import com.example.store.domain.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository urepository; 
	
	@RequestMapping(value="/userlist")
    public String userList(Model model) {	
        model.addAttribute("users", urepository.findAll());
        return "userlist";
    }
	
	@RequestMapping(value = "/signup")
    public String addUser(Model model){
    	model.addAttribute("users", new User());
        return "signup";
    } 
	
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result, Model model){
		if (result.hasErrors()) {
            return "signup";
        }
        urepository.save(user);
        return "redirect:login";
    }
	
	@RequestMapping(value = "/deleteuser/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") Long userId, Model model) {
    	urepository.deleteById(userId);
        return "redirect:../userlist";
    }   

}
