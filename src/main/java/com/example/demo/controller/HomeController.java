package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.repository.IUserRepository;
import com.example.demo.repository.IProductRepository;
import com.example.demo.repository.ProductArraylistRepository;
import com.example.demo.repository.UserArraylistRepository;
import org.apache.catalina.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Controller
public class HomeController {

    ProductArraylistRepository ipr;
    IUserRepository iur;
    User user;

    public HomeController() {
        //Have to instantiate objects of repos myself, @Autowired seems to only instantiate 1 of the repos
        this.ipr = new ProductArraylistRepository();
        this.iur = new UserArraylistRepository();
    }

    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        //Check if user is logged in
        if (session.getAttribute("user") == null) {
            return "login";
        }
        //Load all available products when first visiting
        if (session.getAttribute("isSearching") == null) {
            model.addAttribute("products", ipr.readAll());
        }
        else {
            model.addAttribute("products", ipr.read((String)session.getAttribute("isSearching")));
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
        this.user = iur.read(user.getUsername());
        if (this.user != null) {
            //Check users password against usernames' password
            if (user.getPassword().equals(this.user.getPassword())) {
                //If successful, set session attribute and redirect to index
                //TODO add "setup" part where a productList is created from items in DB
                //this.userCart = new Cart((ArrayList)ipr.readAll());
                session.setAttribute("user", this.user);
                return "redirect:/";
            }
        }
        //If everything fails, user being null OR password being wrong, return login page
        return "login";
    }

    @PostMapping("/addProductToCart/{id}")
    public String addProductToCart(HttpSession session, @PathVariable int id) {
        //TODO Add a product to cart via product ID
        this.user.addToCart(ipr.findProductInDB(id));
        return "redirect:/";
    }

    @GetMapping("/createProduct")
    public String createProduct() {
        return "createProduct";
    }

    @PostMapping("/createProduct")
    public String createProductAction(@RequestParam("name") String name, @RequestParam("price") double price, @RequestParam("image") String image) {
        int id = ipr.getProductCount() + 1;
        ipr.create(new Product(name, image, id, price));
        return "createProduct";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        //TODO 'reset' a session
        session.removeAttribute("user");
        return "login";
    }

}
