package com.example.democustomermanagement.controller;

import com.example.democustomermanagement.model.Customer;
import com.example.democustomermanagement.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/create-customer")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "New customer created successfully");
        return modelAndView;
    }

//    @GetMapping("/customers")
//    public ModelAndView listCustomers() {
////        Iterable<Customer> customers = ;
//        ModelAndView modelAndView = new ModelAndView("list");
//        modelAndView.addObject("customersPage", customerService.findAll());
//        return modelAndView;
//    }

    @GetMapping("/edit-customer/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("edit");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/edit-customer")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "Customer updated successfully");
        return modelAndView;
    }

    @GetMapping("/delete-customer/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("delete");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;

        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @PostMapping("/delete-customer")
    public ModelAndView deleteCustomer(@ModelAttribute("customer") Customer customer) {
        ModelAndView modelAndView = new ModelAndView("redirect:/customers");
        customerService.remove(customer.getId());
//        modelAndView.addObject("message","Deleted successfully");
        return modelAndView;
    }

    @GetMapping("/customers/{id}")
    public ModelAndView showCustomerDetail(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);
        if (customer.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("info");
            modelAndView.addObject("customer", customer.get());
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error.404");
            return modelAndView;
        }
    }

    @GetMapping("/customers")
    public ModelAndView listCustomers(@PageableDefault(value = 4) Pageable pageable, @RequestParam("search") Optional<String> search){
        Page<Customer> customers;
        ModelAndView modelAndView = new ModelAndView("list");
        if(search.isPresent()){
            customers = customerService.findAllByName(search.get(), pageable);
            modelAndView.addObject("search", "searching");
            modelAndView.addObject("searchObject", search.get());
        } else {
            customers = customerService.findAll(pageable);
        }
        modelAndView.addObject("customersPage", customers);
        return modelAndView;
    }
}
