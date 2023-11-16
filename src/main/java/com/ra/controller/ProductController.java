package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.service.CategoryService;
import com.ra.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @RequestMapping("/product")
    public String index(Model model){
        List<Product> products = productService.findAll();
        model.addAttribute("products",products);
        return "product/index";
    }



    @RequestMapping("/add-product")
    public String add(Model model) {
        Product product = new Product();
        model.addAttribute("category", categoryService.findAll());
        model.addAttribute("product", product);
        return "product/add";
    }

    @RequestMapping("/create-product")
    public String create(@ModelAttribute("product") Product product, RedirectAttributes redirectAttributes) {
        Boolean check = productService.create(product);
        if (!check) {

            return "redirect:/product/add-product?err=1";
        }
        redirectAttributes.addFlashAttribute("mess", "thanh cong");
        return "redirect:/product";
    }

    @GetMapping("/delete-product/{id}")
    public  String delete(@PathVariable("id") int id){
        productService.deleteById(id);
        return "redirect:/product";
    }


    @GetMapping ("/edit-product/{id}")
    public  String edit (@PathVariable("id") Integer id,Model model) {
        model.addAttribute("category", categoryService.findAll());
        model.addAttribute("product", productService.findById(id));

        return "product/edit";
    }
    @PostMapping("/update-product/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute("product") Product product) {
        System.out.println(product);
        productService.updateById(product,id);
        return "redirect:/product";

    }
}
