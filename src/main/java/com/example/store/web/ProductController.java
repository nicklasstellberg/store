package com.example.store.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.store.domain.CategoryRepository;
import com.example.store.domain.Product;
import com.example.store.domain.ProductRepository;

@CrossOrigin
@Controller
public class ProductController {
	@Autowired
	private ProductRepository repository; 
	
	@Autowired
	private CategoryRepository crepository; 
	
	// login
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	// list all products
	@RequestMapping(value="/productlist")
    public String productList(Model model) {	
        model.addAttribute("products", repository.findAll());
        return "productlist";
    }
	
	// add product
	@RequestMapping(value = "/add")
    public String addProduct(Model model){
    	model.addAttribute("product", new Product());
    	model.addAttribute("categories", crepository.findAll());
        return "addproduct";
    } 
	
	// save prodcut
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@Valid Product product, BindingResult result, Model model){
		if (result.hasErrors()) {
			model.addAttribute("categories", crepository.findAll());
            return "addproduct";
        }
		// pitää tutkia product id ja ohjata joko lisää lomakkeele tai muokkauslomakkeelle
        repository.save(product);
        return "redirect:productlist";
    }  
	
	// delete product
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("id") Long productId, Model model) {
    	repository.deleteById(productId);
        return "redirect:../productlist";
    }   
	
	// edit product
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editProduct(@PathVariable("id") Long productId, Model model) {
		model.addAttribute("categories", crepository.findAll());
    	model.addAttribute("product", repository.findById(productId));
        return "editproduct";
    }
	
	// save edited product
	@RequestMapping(value = "/saveedit", method = RequestMethod.POST)
    public String saveEdit(@Valid Product product, BindingResult result, Model model){
		if (result.hasErrors()) {
			model.addAttribute("categories", crepository.findAll());
            return "editproduct";
        }
        repository.save(product);
        return "redirect:productlist";
    }  
	
	//
	@RequestMapping(value="/products", method = RequestMethod.GET)
    public @ResponseBody List<Product> productListRest() {	
        return (List<Product>) repository.findAll();
    }  
	
	//
	@RequestMapping(value="/products/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Product> findProductRest(@PathVariable("id") Long productId) {	
    	return repository.findById(productId);
    } 
	
	//
	@RequestMapping(value="/products", method = RequestMethod.POST)
    public @ResponseBody Product saveProductRest(@RequestBody Product product) {	
    	return repository.save(product);
    }
}