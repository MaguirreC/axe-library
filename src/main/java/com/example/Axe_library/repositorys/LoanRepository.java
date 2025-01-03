package com.example.Axe_library.repositorys;

import com.example.Axe_library.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan,Long> {

    List<Loan> findByBookBookNameContainingIgnoreCase(String bookName);

    List<Loan> findByClientId(Long clientId);

}
