package com.example.store.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.store.domain.OrderItemRepository;

@Controller
public class OrderItemController {
	@Autowired
	private OrderItemRepository oirepository; 
	
	@RequestMapping(value="/orderitemlist")
    public String productList(Model model) {	
        model.addAttribute("orderitems", oirepository.findAll());
        return "orderitemlist";
    }

}
