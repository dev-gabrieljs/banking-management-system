package com.br.account.controller;


import com.br.account.entity.Transfer;
import com.br.account.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    // Endpoint para realizar transferência entre contas
    @PostMapping
    public ResponseEntity<Transfer> transfer(@RequestParam Long fromUserId,
                                             @RequestParam Long toUserId,
                                             @RequestParam BigDecimal amount) {
        transferService.transfer(fromUserId, toUserId, amount);

        URI location = URI.create(String.format("/transfers?fromUserId=%d&toUserId=%d", fromUserId, toUserId));
        return ResponseEntity.created(location).build();
    }

    // Endpoint para listar todas as transferências
    @GetMapping
    public ResponseEntity<List<Transfer>> getAllTransfers() {
        List<Transfer> transfers = transferService.getAllTransfers();
        return ResponseEntity.ok(transfers);
    }
}

