package com.poly.j6d8_asm_ph24125.controller;

import com.poly.j6d8_asm_ph24125.dao.ProductDAO;
import com.poly.j6d8_asm_ph24125.entity.Product;
import com.poly.j6d8_asm_ph24125.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductDAO productDAO;
    @Autowired
    HttpServletRequest req;
    @RequestMapping("/product/list")
    public String list(
            Model model,
            @RequestParam("cid") Optional<String> cid
    ) {

        String pageParam = req.getParameter("page");
        String limitParam = req.getParameter("limit");
        if(cid.isPresent()) {
            model.addAttribute("cid", cid);
            List<Product> list = productService.findByCategoryId(cid.get());
            model.addAttribute("items", list);
        }
        else {
            int page = pageParam == null || Integer.parseInt(pageParam) == 1? 1 : Integer.parseInt(pageParam);
            int limit = limitParam == null ? 9 : Integer.parseInt(limitParam);
            Pageable pageable = PageRequest.of(page -1 , limit);
            Page pageData = this.productDAO.findAll(pageable);
            List<Product> list = productService.findAll();
            model.addAttribute("items", pageData);
        }
        return "product/list";
    }

    @RequestMapping("/product/detail/{id}")
    public String detail(
            Model model,
            @PathVariable("id") Integer id
    ) {
        Product p = productService.findById(id);
        model.addAttribute("item", p);
        return "product/detail";
    }
}
