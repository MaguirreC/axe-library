package com.example.Axe_library.services;

import com.example.Axe_library.models.Book;
import com.example.Axe_library.models.Client;
import com.example.Axe_library.models.DtoResponseLoan;
import com.example.Axe_library.models.Loan;
import com.example.Axe_library.repositorys.BookRepository;
import com.example.Axe_library.repositorys.ClientRepository;
import com.example.Axe_library.repositorys.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoanService {
    @Autowired
    LoanRepository loanRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ClientRepository clientRepository;

    public Loan newLoan(Long bookId,Long clientId){
        Book book = bookRepository.findById(bookId).orElseThrow(()->new RuntimeException("no book "));
        Client client = clientRepository.findById(clientId).orElseThrow(()-> new RuntimeException("no user"));

        Loan loan = new Loan();

        loan.setBook(book);
        loan.setClient(client);
        loan.setLoanDate(LocalDateTime.now());
        loan.setLoanReturn(LocalDateTime.now().plusWeeks(2));
        loan.setReturned(false);

        book.setAvailable(false);
        bookRepository.save(book);

        return loanRepository.save(loan);

    }

    public List<DtoResponseLoan> getLoans(){
        List<Loan> loans = loanRepository.findAll();

        return loans.stream().map(loan-> new DtoResponseLoan(loan.getId(),loan.getClient().getName(),loan.getBook().getBookName(),loan.getBook().getBookAuthor()
                ,loan.getReturned(),loan.getLoanDate(),loan.getLoanReturn())).toList();
    }

    public void deleteLoanById(Long id) {
        if (!loanRepository.existsById(id)) {
            throw new RuntimeException("loan with ID " + id + " not found");
        }
        loanRepository.deleteById(id);
    }

    public Loan returnLoan(Long id){
    Loan loan = loanRepository.findById(id).orElseThrow(()-> new RuntimeException("Loan not found"));
    loan.setReturned(true);
    Book book = loan.getBook();
    book.setAvailable(true);
    bookRepository.save(book);

    return loanRepository.save(loan);


    }

    public List<Loan> searchLoanByBookName(String bookName){
        return loanRepository.findByBookBookNameContainingIgnoreCase(bookName);
    }

   public List<DtoResponseLoan> getLoansByClient(Long clientId){

      List<Loan>loans =  loanRepository.findByClientId(clientId);

      return loans.stream().map(loan ->new DtoResponseLoan(loan.getId(),loan.getClient().getName(),loan.getBook().getBookName(),loan.getBook().getBookAuthor()
              ,loan.getReturned(),loan.getLoanDate(),loan.getLoanReturn())).toList();
   }
}
