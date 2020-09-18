package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private IProductRepository pDB;

    @Autowired
    private IUserRepository uDB;

    User user;

    /*public HomeController() {
        this.pDB = new ProductDBRepository();
        this.uDB = new UserDBRepository();
    }*/


    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        //Check if user is logged in
        if (session.getAttribute("user") == null) {
            return "login";
        }
        //Load all available products when first visiting
        if (session.getAttribute("isSearching") == null) {
            model.addAttribute("products", pDB.readAll());
        }
        else {
            model.addAttribute("products", pDB.read((String)session.getAttribute("isSearching")));
        }
        //Load the users current cart
        model.addAttribute("cart", user.getCart().getCart());
        //Show the total amount for all items
        model.addAttribute("total", user.getTotal());
        return "index";
    }

    @PostMapping("/search")
    public String search(HttpSession session, Model model, @RequestParam("keyword") String keyword) {
        //Should filter based on what was searched for
        session.setAttribute("isSearching", keyword);
        return "redirect:/";
    }

    @PostMapping("/checkCredentials")
    public String checkCredentials(HttpSession session, Model model, @ModelAttribute User user) {
        //Checks whether a username is in the database, if yes, then checks password for that username
        this.user = uDB.read(user.getUsername());

        if (this.user != null) {
            //Check users password against usernames' password
            if (user.getPassword().equals(this.user.getPassword())) {
                //If successful, set session attribute and redirect to index
                session.setAttribute("user", this.user);
                return "redirect:/";
            }
        }
        //If everything fails, user being null OR password being wrong, return login page
        return "login";
    }

    @PostMapping("/addProductToCart/{id}")
    public String addProductToCart(HttpSession session, @PathVariable int id) {
        // Add a product to cart via product ID
        this.user.addToCart(pDB.findProductInDB(id));
        // TODO Once added to a users cart, an ID should be connected to the users ID in the DB
        return "redirect:/";
    }

    @PostMapping("/removeProductFromCart/{id}")
    public String removeProductFromCart(HttpSession session, @PathVariable int id) {
        this.user.removeFromCart(pDB.findProductInDB(id));
        return "redirect:/";
    }

    @GetMapping("/createProduct")
    public String createProduct() {
        return "createProduct";
    }

    @PostMapping("/createProduct")
    public String createProductAction(@RequestParam("name") String name, @RequestParam("price") double price, @RequestParam("image") String image) {
        pDB.create(new Product(name, image, price));
        return "createProduct";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        //TODO 'reset' a session
        session.removeAttribute("user");
        return "login";
    }

}
