package com.GraduationProject.demo.controller;

import com.GraduationProject.demo.model.Product;
import com.GraduationProject.demo.service.ProductIngredientService;
import com.GraduationProject.demo.service.ScanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scan")
@RequiredArgsConstructor
public class ScanController {

    private final ScanService scanService;

    @GetMapping("/{barcode}")
    public ResponseEntity<?> scanBarcode(@PathVariable String barcode) {
        return ResponseEntity.ok(scanService.scanProduct(barcode));
    }
}