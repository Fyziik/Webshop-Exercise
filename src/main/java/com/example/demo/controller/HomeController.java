package com.example.demo.controller;

import com.example.demo.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    IProductRepository ipr;

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        //Load all available products when first visiting
        if (session.getAttribute("isSearching") == null) {
            model.addAttribute("products", ipr.readAll());
        }
        else {
            model.addAttribute("products", ipr.read((String)session.getAttribute("isSearching")));
        }
        return "index";
    }

    @PostMapping("/search")
    public String search(HttpSession session, Model model, @RequestParam("keyword") String keyword) {
        //Should filter based on what was searched for
        session.setAttribute("isSearching", keyword);
        return "redirect:/";
    }

}
