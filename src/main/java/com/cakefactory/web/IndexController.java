package com.cakefactory.web;

import com.cakefactory.service.CatalogService;
import com.cakefactory.domain.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class IndexController {

    private final CatalogService catalogService;

    public IndexController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/")
    String index(Model model) {
        List<Product> productList = (List<Product>) catalogService.getAllProducts();
        model.addAttribute("products", productList);
        return "catalog";
    }
}