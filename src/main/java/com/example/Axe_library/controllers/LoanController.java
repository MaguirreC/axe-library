package com.example.Axe_library.controllers;

import com.example.Axe_library.models.Client;
import com.example.Axe_library.models.DtoLoan;
import com.example.Axe_library.models.DtoResponseLoan;
import com.example.Axe_library.models.Loan;
import com.example.Axe_library.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {
    @Autowired
    LoanService loanService;

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody DtoLoan dtoLoan){
    Loan loan =loanService.newLoan(dtoLoan.bookId(), dtoLoan.clientId());

        return ResponseEntity.ok(loan);
    }
    @GetMapping
    public ResponseEntity<List<DtoResponseLoan>> getLoans(){
        return ResponseEntity.ok(loanService.getLoans());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteLoan(@PathVariable Long id){
        try {
            loanService.deleteLoanById(id);
            return ResponseEntity.ok("loan delete");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/return/{loanId}")
    public ResponseEntity<Loan> returnLoan(@PathVariable Long loanId){
        Loan loan = loanService.returnLoan(loanId);
        return ResponseEntity.ok(loan);

}

    @GetMapping("/bookName")
    public ResponseEntity<List<Loan>> loanByBookName(@RequestParam String bookName){
        List<Loan> loans = loanService.searchLoanByBookName(bookName);
        if (loans.isEmpty())
        {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<DtoResponseLoan>> loanByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(loanService.getLoansByClient(clientId));
    }
}
