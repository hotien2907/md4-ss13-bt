package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.model.service.CategoryService;
import com.ra.model.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
    public String create(@ModelAttribute("product") Product product, @RequestParam("fileImage") MultipartFile file, HttpServletRequest request, RedirectAttributes redirectAttributes) {


        String fileName = file.getOriginalFilename();
        String path = request.getServletContext().getRealPath("uploads/images");
        File destination = new File(path+"/"+fileName);


        try {
            file.transferTo(destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

            product.setImage(fileName);
        System.out.println(fileName);
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
        System.out.println(productService.findById(id));

        return "product/edit";
    }
    @PostMapping("/update-product/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute("product") Product product,@RequestParam("fileUpdate") MultipartFile fileUpdate, HttpServletRequest request) {


        String fileName = fileUpdate.getOriginalFilename();
        String path = request.getServletContext().getRealPath("uploads/images");
        File destination = new File(path+"/"+fileName);


        try {
            fileUpdate.transferTo(destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        product.setImage(fileName);
        System.out.println(fileName);
        productService.updateById(product,id);
        return "redirect:/product";

    }
}
