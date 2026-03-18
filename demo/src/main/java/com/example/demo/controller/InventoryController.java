package com.example.demo.controller;

import com.example.demo.entity.InventoryItem;
import com.example.demo.entity.StockLog;
import com.example.demo.service.InventoryService;
import com.example.demo.repository.StockLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.io.IOException;
import java.util.List;

@Controller
public class InventoryController {

    @Autowired
    private InventoryService service;

    @Autowired
    private StockLogRepository stockLogRepository;

    // Dashboard
    @GetMapping("/")
    public String dashboard(Model model){

        List<InventoryItem> items = service.getAll();

        model.addAttribute("items", service.getAll());

        model.addAttribute("totalValue", service.getTotalValue());

        model.addAttribute("lowStockCount", service.getLowStockCount());

        model.addAttribute("lowStockItems", service.getLowStockItems());

        // For Stock Chart
        model.addAttribute("chartItems", items);

        return "index";
    }

    // Products Page
    @GetMapping("/products")
    public String products(Model model){
        model.addAttribute("items", service.getAll());
        return "products";
    }

    // Add Product Page
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/add")
    public String addProductPage(Model model){
        model.addAttribute("item", new InventoryItem());
        return "add-product";
    }

    // Save Product
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute InventoryItem item){
        service.save(item);
        return "redirect:/products";
    }

    // Delete Product
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        service.delete(id);
        return "redirect:/products";
    }

    // Export Inventory CSV
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition",
                "attachment; filename=inventory.csv");

        List<InventoryItem> items = service.getAll();

        PrintWriter writer = response.getWriter();

        writer.println("ID,Name,Category,Price,Quantity");

        for(InventoryItem item : items){

            writer.println(
                    item.getId()+","+
                            item.getItemName()+","+
                            item.getCategory()+","+
                            item.getPrice()+","+
                            item.getQuantity()
            );
        }

        writer.flush();
        writer.close();
    }

    // Stock Logs Page
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping("/stock-logs")
    public String logs(Model model){
        model.addAttribute("logs", service.getAllLogs());
        return "stock-logs";
    }

    // Low Stock Page
    @GetMapping("/low-stock")
    public String lowStock(Model model){
        model.addAttribute("items", service.getLowStockItems());
        return "low-stock";
    }

    // Increase Stock
    @GetMapping("/increase/{id}")
    public String increaseStock(@PathVariable Long id,
                                @RequestParam(defaultValue = "/") String redirect) {

        InventoryItem item = service.getById(id);

        item.setQuantity(item.getQuantity() + 1);

        service.save(item);

        return "redirect:" + redirect;
    }

    // Decrease Stock
    @GetMapping("/decrease/{id}")
    public String decreaseStock(@PathVariable Long id,
                                @RequestParam(defaultValue = "/") String redirect) {

        InventoryItem item = service.getById(id);

        if(item.getQuantity() > 0){
            item.setQuantity(item.getQuantity() - 1);
        }

        service.save(item);

        return "redirect:" + redirect;
    }

    // Export Stock Logs CSV
    @GetMapping("/export-logs")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public void exportLogs(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition",
                "attachment; filename=stock_logs.csv");

        List<StockLog> logs = stockLogRepository.findAll();

        PrintWriter writer = response.getWriter();

        writer.println("ID,Item Name,Quantity,Action,Timestamp");

        for (StockLog log : logs) {

            writer.println(
                    log.getId() + "," +
                            log.getItemName() + "," +
                            log.getQuantity() + "," +
                            log.getAction() + "," +
                            log.getTimestamp()
            );
        }

        writer.flush();
        writer.close();
    }

    @GetMapping("/analytics")
    public String viewAnalytics(Model model) {

        List<InventoryItem> items = service.getAll();

        model.addAttribute("items", items);

        model.addAttribute("totalValue", service.getTotalValue());

        model.addAttribute("lowStockCount", service.getLowStockCount());

        model.addAttribute("chartItems", items);

        return "analytics";
    }

}