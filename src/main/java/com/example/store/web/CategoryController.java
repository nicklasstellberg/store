package com.example.store.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.store.domain.Category;
import com.example.store.domain.CategoryRepository;

@Controller
public class CategoryController {
	@Autowired
	private CategoryRepository repository;
	
	@RequestMapping(value="/categorylist")
    public String categoryList(Model model) {	
        model.addAttribute("categories", repository.findAll());
        return "categorylist";
    }
	@RequestMapping(value = "/addcategory")
    public String addCategory(Model model){
    	model.addAttribute("category", new Category());
        return "addcategory";
    }     
    
    
    @RequestMapping(value = "/savecategory", method = RequestMethod.POST)
    public String save(@Valid Category category, BindingResult result){
    	if (result.hasErrors()) {
            return "addcategory";
        }
        repository.save(category);
        return "redirect:categorylist";
    }
    
    @RequestMapping(value = "/deletecategory/{categoryid}", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable("categoryid") Long categoryId, Model model) {
    	repository.deleteById(categoryId);
        return "redirect:../categorylist";
    }   
	
	@RequestMapping(value = "/editcategory/{categoryid}", method = RequestMethod.GET)
    public String editBook(@PathVariable("categoryid") Long categoryId, Model model) {
    	model.addAttribute("category", repository.findById(categoryId));
        return "editcategory";
    }
	
	@RequestMapping(value = "/saveeditcategory", method = RequestMethod.POST)
    public String saveeditcategory(@Valid Category category, BindingResult result){
    	if (result.hasErrors()) {
            return "editcategory";
        }
        repository.save(category);
        return "redirect:categorylist";
    }
	
	@RequestMapping(value="/categories", method = RequestMethod.GET)
    public @ResponseBody List<Category> getCategoriesRest() {	
        return (List<Category>) repository.findAll();
    } 
	
	@RequestMapping(value="/categories/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Category> findCategoryRest(@PathVariable("id") Long categoryId) {	
    	return repository.findById(categoryId);
    }
	
	@RequestMapping(value="/categories", method = RequestMethod.POST)
    public @ResponseBody Category saveCategoryRest(@RequestBody Category category) {	
    	return repository.save(category);
    }
}
