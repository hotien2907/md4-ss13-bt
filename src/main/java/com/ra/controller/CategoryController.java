package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller

public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping(value = {"category"})
    public String index(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories",categories);

      return "category/index";
    }
    @RequestMapping("/add-category")
    public String add(Model model){
         Category category = new Category();
         model.addAttribute("category",category);
        return "category/add";
    }
    @RequestMapping("/create-category")
    public String save(@ModelAttribute("category") Category category, RedirectAttributes redirectAttributes){
        Boolean check = categoryService.create(category);
        if(!check){
            return "category/add?err=";
        };
        redirectAttributes.addFlashAttribute("mess","thanh cong");
        return "redirect:/category";
    }

    @GetMapping ("/edit-category/{id}")
    public  String edit (@PathVariable("id") Integer id,Model model) {
        model.addAttribute("category", categoryService.findById(id));

        return "category/edit";
    }
    @PostMapping("/update-category/{id}")
    public String update(@PathVariable("id") Integer id, @ModelAttribute("category") Category category) {
        System.out.println(category);
          categoryService.updateById(category,id);
        return "redirect:/category";
    };
    @GetMapping("/delete-category/{id}")
    public  String delete(@PathVariable("id") int id){
        categoryService.deleteById(id);
        return "redirect:/category";
    }

}
