package com.poly.j6d8_asm_ph24125.interceptor;

import com.poly.j6d8_asm_ph24125.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class GlobalInterceptor implements HandlerInterceptor {
    @Autowired
    CategoryService categoryService;

    // sau khi thuc hiện method trong controller thì ms chạy ( pre - post)
    @Override
    public void postHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView
    ) throws Exception {
        request.setAttribute("cates", categoryService.findAll());
    }
}
