package com.example.FBJV24001114synrgy7josBinarFudch5.service;

import com.example.FBJV24001114synrgy7josBinarFudch5.model.dto.InvoiceData;
import com.example.FBJV24001114synrgy7josBinarFudch5.model.entity.User;
import com.example.FBJV24001114synrgy7josBinarFudch5.projection.OrderDetailProjection;
import com.example.FBJV24001114synrgy7josBinarFudch5.repository.OrderRepository;
import com.example.FBJV24001114synrgy7josBinarFudch5.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService{

    private final OrderRepository orderRepository;

    private final UserRepository userRepository;


    @Autowired
    public InvoiceServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void generateInvoice(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        List<InvoiceData> orderDetail = orderRepository.getOrderDetailsByUserId(userId)
                .stream().map(this::mapProjectionToDto).collect(Collectors.toList());

        try {
            File file = ResourceUtils.getFile("classpath:invoice_report.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orderDetail);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("customerName", user.getUsername());
            JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            String outputFile = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "invoice.pdf";
            JasperExportManager.exportReportToPdfFile(print, outputFile);
            System.out.println("Report created...");
        } catch (FileNotFoundException | JRException e) {
            throw new RuntimeException(e);
        }
    }

    private InvoiceData mapProjectionToDto(OrderDetailProjection projection) {
        return InvoiceData.builder()
                .orderId(projection.getOrderId().toString())
                .totalPrice(projection.getTotalPrice())
                .productName(projection.getProductName())
                .orderTime(projection.getOrderTime())
                .quantity(projection.getQuantity())
                .build();
    }
}
