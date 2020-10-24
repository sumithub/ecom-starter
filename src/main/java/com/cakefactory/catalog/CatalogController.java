package com.cakefactory.catalog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("/")
    String index(Model model) {
        List<Product> productList = (List<Product>) catalogService.getAllProducts();
        model.addAttribute("products", productList);
        return "catalog";
    }
}