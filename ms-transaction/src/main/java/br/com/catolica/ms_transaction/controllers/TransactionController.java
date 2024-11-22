package br.com.catolica.ms_transaction.controllers;

import br.com.catolica.ms_transaction.dto.TransactionDTO;
import br.com.catolica.ms_transaction.services.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @PostMapping("/save")
    public ResponseEntity<TransactionDTO> save(@Valid @RequestBody TransactionDTO transactionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionService.save(transactionDTO));
    }

    @GetMapping
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        return ResponseEntity.ok().body(transactionService.getAll());
    }

    @GetMapping("/delayed-movements")
    public ResponseEntity<List<TransactionDTO>> getDelayedMovements() {
        return ResponseEntity.ok().body(transactionService.delayedMovements());
    }

    @GetMapping("/verify-fine/{id}")
    public ResponseEntity<TransactionDTO> verifyFine(@PathVariable Long id) throws Exception {
        TransactionDTO transactionDTO = transactionService.verifyFine(id);
        return ResponseEntity.ok().body(transactionDTO);
    }

}