package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.repository.IUserRepository;
import com.example.demo.repository.IProductRepository;
import com.example.demo.repository.ProductArraylistRepository;
import com.example.demo.repository.UserArraylistRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    IProductRepository ipr;
    IUserRepository iur;

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
        User u = iur.read(user.getUsername());
        if (u != null) {
            //Check users password against usernames' password
            if (user.getPassword().equals(u.getPassword())) {
                //If successful, set session attribute and redirect to index
                //TODO add "setup" part where a productList is created from items in DB
                session.setAttribute("user", u);
                return "redirect:/";
            }
        }
        //If everything fails, user being null OR password being wrong, return login page
        return "login";
    }

    @GetMapping("/addProductToCart/id")
    public String addProductToCart(HttpSession session) {
        return "redirect:/";
    }

}
