package com.GraduationProject.demo.controller;


import com.GraduationProject.demo.model.ActionType;
import com.GraduationProject.demo.model.Product;
import com.GraduationProject.demo.repo.ProductRepository;
import com.GraduationProject.demo.repo.UserRepository;
import com.GraduationProject.demo.service.HistoryService;
import com.GraduationProject.demo.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/history")
@RequiredArgsConstructor
public class History {

    private final HistoryService historyService;

    @PostMapping("/add")
    public ResponseEntity<String> addUserProduct(
            @RequestBody Map<String, Object> requestBody,
//            @RequestBody ActionType actionType,
            @AuthenticationPrincipal User user) {

        String productId = (String) requestBody.get("barcode");
        ActionType actionType = ActionType.valueOf((String) requestBody.get("actionType"));

        historyService.addUserProduct(user.getId(), productId, actionType);
        return ResponseEntity.ok("User product action recorded successfully.");
    }

    @GetMapping("/scanned")
    public ResponseEntity<List<Product>> getScannedProducts(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(historyService.getProductsByAction(user.getId(), ActionType.SCANNED));
    }

    @GetMapping("/added")
    public ResponseEntity<List<Product>> getAddedProducts(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(historyService.getProductsByAction(user.getId(), ActionType.ADDED));
    }


}
