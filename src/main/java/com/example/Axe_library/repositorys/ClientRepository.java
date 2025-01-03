package com.example.Axe_library.repositorys;

import com.example.Axe_library.models.Book;
import com.example.Axe_library.models.Client;
import com.example.Axe_library.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    public List<Client> findByNameContainingIgnoreCase(String name);

    public  List<Client> findByIdentificationContainingIgnoreCase(String identification);
}
