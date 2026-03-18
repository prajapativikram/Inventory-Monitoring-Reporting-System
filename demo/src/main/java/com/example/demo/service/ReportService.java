package com.example.demo.service;

import com.opencsv.CSVWriter;
import com.example.demo.entity.InventoryItem;
import com.example.demo.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.util.List;

@Service
public class ReportService {

    private final InventoryRepository repository;

    public ReportService(InventoryRepository repository) {
        this.repository = repository;
    }

    public void exportCSV() throws Exception {

        List<InventoryItem> items = repository.findAll();

        CSVWriter writer = new CSVWriter(new FileWriter("inventory_report.csv"));
        writer.writeNext(new String[]{"ID","Name","Category","Quantity","Price"});

        for (InventoryItem item : items) {
            writer.writeNext(new String[]{
                    item.getId().toString(),
                    item.getItemName(),
                    item.getCategory(),
                    String.valueOf(item.getQuantity()),
                    String.valueOf(item.getPrice())
            });
        }
        writer.close();
    }
}