package com.example.store.web;

import java.security.Principal;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.store.domain.OrderItem;
import com.example.store.domain.OrderItemRepository;
import com.example.store.domain.ProductRepository;
import com.example.store.domain.User;
import com.example.store.domain.UserRepository;

@Controller
public class OrderItemController {
	@Autowired
	private OrderItemRepository oirepository; 
	
	@Autowired
	private ProductRepository repository; 
	
	@Autowired
	private UserRepository urepository; 
	
	// Show all orders
	@RequestMapping(value="/orderitemlist")
    public String productList(Model model) {	
        model.addAttribute("orderitems", oirepository.findAll());
        return "orderitemlist";
    }
	
	// Show user's own orders
	@RequestMapping(value="/userorders", method = RequestMethod.GET)
	public String findUserOrders(Model model, Principal principal) {
	    String username = principal.getName(); //get logged in username
	    User user = urepository.findByUsername(username);
	    model.addAttribute("orderitems", oirepository.findByUser(user));
	    return "userorderlist";
	}
	
	// Show order details
	@RequestMapping(value = "/orderdetails/{id}", method = RequestMethod.GET)
    public String editProduct(@PathVariable("id") Long orderItemId, Model model) {
		model.addAttribute("orderitems", oirepository.findById(orderItemId));
        return "orderdetails";
    }
	
	// Add order
	@RequestMapping(value = "/addorderitem")
    public String addProduct(Model model){
    	model.addAttribute("orderitem", new OrderItem());
    	model.addAttribute("products", repository.findAll());
    	model.addAttribute("users", urepository.findAll());
        return "addorderitem";
    } 
	
	// Save order
	@RequestMapping(value = "/saveorderitem", method = RequestMethod.POST)
    public String save(@Valid OrderItem orderitem, BindingResult result, Model model, Principal principal){
		if (result.hasErrors()) {
			model.addAttribute("products", repository.findAll());
			model.addAttribute("users", urepository.findAll());
            return "addorderitem";
        }
		Date date = new Date();
		orderitem.setStartDay(date);
		String username = principal.getName(); //get logged in username
        User user = urepository.findByUsername(username);
		orderitem.setUser(user);
        oirepository.save(orderitem);
        return "redirect:orderitemlist";
    }  

}
