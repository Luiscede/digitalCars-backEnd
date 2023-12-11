package com.grupo4.digitalcars.repository;

import com.grupo4.digitalcars.model.Product;
import com.grupo4.digitalcars.model.Category;
import com.grupo4.digitalcars.model.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> getByCategory(Category id);
    List<Product> getByTransmission(Transmission id);

    Optional<Product> findById(Integer productId);
    @Query(value = "select P.* from product P where P.id not in (select distinct R.product_id from reservation R where (R.checkout_date >= ?1 and R.check_in_date <= ?2));", nativeQuery = true)
    List<Product> getByRangeDate(LocalDate check_in_date, LocalDate check_out_date);

    @Query(value = "select P.* from product P where P.transmission_id = ?1 and P.id not in (select distinct R.product_id from reservation R where (R.checkout_date >= ?2 and R.check_in_date <= ?3));", nativeQuery = true)
    List<Product> getByTransmissionAndRangeDate(Integer transmission_id, LocalDate check_in_date, LocalDate check_out_date);
    @Query(value = "SELECT * FROM product ORDER BY RAND() LIMIT 6", nativeQuery = true)
    List<Product> getRandomProduct();

}

