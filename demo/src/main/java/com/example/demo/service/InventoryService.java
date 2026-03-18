package com.example.demo.service;

import com.example.demo.entity.InventoryItem;
import com.example.demo.entity.StockLog;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.StockLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class InventoryService {

    private final InventoryRepository repository;
    private final StockLogRepository stockLogRepository;
    private final EmailService emailService;

    public InventoryService(InventoryRepository repository,
                            StockLogRepository stockLogRepository,
                            EmailService emailService) {
        this.repository = repository;
        this.stockLogRepository = stockLogRepository;
        this.emailService = emailService;
    }

    // CREATE or UPDATE
    public InventoryItem save(InventoryItem item) {

        boolean isNew = (item.getId() == null);

        InventoryItem savedItem = repository.save(item);

        // Low stock email alert
        if (savedItem.getQuantity() <= savedItem.getReorderLevel()) {
            emailService.sendLowStockAlert(
                    savedItem.getItemName(),
                    savedItem.getQuantity(),
                    savedItem.getReorderLevel()
            );
        }

        // Create log
        StockLog log = new StockLog();
        log.setItemName(savedItem.getItemName());
        log.setQuantity(savedItem.getQuantity());
        log.setAction(isNew ? "CREATED" : "UPDATED");
        log.setTimestamp(LocalDateTime.now());

        stockLogRepository.save(log);

        return savedItem;
    }

    // GET ALL
    public List<InventoryItem> getAll() {
        return repository.findAll();
    }

    // GET BY ID
    public InventoryItem getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    // DELETE
    public void delete(Long id) {

        InventoryItem item = getById(id);
        repository.deleteById(id);

        StockLog log = new StockLog();
        log.setItemName(item.getItemName());
        log.setQuantity(item.getQuantity());
        log.setAction("DELETED");
        log.setTimestamp(LocalDateTime.now());

        stockLogRepository.save(log);
    }

    // TOTAL INVENTORY VALUE
    public double getTotalValue() {
        return repository.findAll().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    // LOW STOCK COUNT
    public long getLowStockCount() {
        return repository.findAll().stream()
                .filter(item -> item.getQuantity() <= item.getReorderLevel())
                .count();
    }

    // LOW STOCK ITEMS LIST
    public List<InventoryItem> getLowStockItems() {
        return repository.findAll().stream()
                .filter(item -> item.getQuantity() <= item.getReorderLevel())
                .toList();
    }
    public List<StockLog> getAllLogs(){
        return stockLogRepository.findAll();
    }


}