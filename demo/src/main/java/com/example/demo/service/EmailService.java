package com.example.demo.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendLowStockAlert(String itemName, int quantity, int reorderLevel) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("rkrahulkumar8651@gmail.com");
        message.setSubject("Low Stock Alert - Inventory System");

        message.setText(
                "⚠ LOW STOCK ALERT\n\n" +
                        "Item Name: " + itemName + "\n" +
                        "Current Quantity: " + quantity + "\n" +
                        "Reorder Level: " + reorderLevel + "\n\n" +
                        "Please restock this item."
        );

        mailSender.send(message);
    }
}