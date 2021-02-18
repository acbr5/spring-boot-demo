package com.example.demo.repository;

import com.example.demo.domainobject.CustomerDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerRepository extends JpaRepository<CustomerDO, Long> {
    // eğer böyle bir değer mevcutsa isPresent() metodu true döner. Bir objenin veritabanındaki varlığını kontrol etmek için kullanılır.
    Optional<CustomerDO> findByUsername(String username);
}
