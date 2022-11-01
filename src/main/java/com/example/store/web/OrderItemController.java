package com.example.store.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.store.domain.OrderItem;
import com.example.store.domain.OrderItemRepository;
import com.example.store.domain.ProductRepository;
import com.example.store.domain.UserRepository;

@Controller
public class OrderItemController {
	@Autowired
	private OrderItemRepository oirepository; 
	
	@Autowired
	private ProductRepository repository; 
	
	@Autowired
	private UserRepository urepository; 
	
	@RequestMapping(value="/orderitemlist")
    public String productList(Model model) {	
        model.addAttribute("orderitems", oirepository.findAll());
        return "orderitemlist";
    }
	
	@RequestMapping(value = "/addorderitem")
    public String addProduct(Model model){
    	model.addAttribute("orderitem", new OrderItem());
    	model.addAttribute("products", repository.findAll());
    	model.addAttribute("users", urepository.findAll());
        return "addorderitem";
    } 
	
	@RequestMapping(value = "/saveorderitem", method = RequestMethod.POST)
    public String save(@Valid OrderItem orderitem, BindingResult result, Model model){
		if (result.hasErrors()) {
			model.addAttribute("products", repository.findAll());
			model.addAttribute("users", urepository.findAll());
            return "addorderitem";
        }
        oirepository.save(orderitem);
        return "redirect:orderitemlist";
    }  

}
