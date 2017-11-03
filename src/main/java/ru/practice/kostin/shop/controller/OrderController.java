package ru.practice.kostin.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.practice.kostin.shop.security.CustomUserDetails;
import ru.practice.kostin.shop.service.OrderService;
import ru.practice.kostin.shop.service.dto.OrderDto;

@Controller
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;

    @GetMapping
    public String order() {
        return "order";
    }

    @PostMapping("/")
    public String createOrder(@ModelAttribute("order") OrderDto orderDto, RedirectAttributes redirectAttributes) {
        CustomUserDetails user = (CustomUserDetails)
                (SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        try {
            Integer orderId = orderService.createOrder(user.getId(), orderDto);
            redirectAttributes.addFlashAttribute("success", "Order created successfully with id:" + orderId);
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/order";
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
}
