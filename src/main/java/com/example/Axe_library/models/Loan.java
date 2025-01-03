package com.example.Axe_library.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     @ManyToOne(fetch = FetchType.EAGER)
     @JoinColumn(name = "client_id")
     private Client client;
     @ManyToOne(fetch = FetchType.EAGER)
     @JoinColumn(name = "book_id")
     private Book book;
     private LocalDateTime loanDate;
     private LocalDateTime loanReturn;
     private Boolean returned;

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
     }

     public Client getClient() {
          return client;
     }

     public void setClient(Client client) {
          this.client = client;
     }

     public Book getBook() {
          return book;
     }

     public void setBook(Book book) {
          this.book = book;
     }

     public LocalDateTime getLoanDate() {
          return loanDate;
     }

     public void setLoanDate(LocalDateTime loanDate) {
          this.loanDate = loanDate;
     }

     public LocalDateTime getLoanReturn() {
          return loanReturn;
     }

     public void setLoanReturn(LocalDateTime loanReturn) {
          this.loanReturn = loanReturn;
     }

     public Boolean getReturned() {
          return returned;
     }

     public void setReturned(Boolean returned) {
          this.returned = returned;
     }
}
