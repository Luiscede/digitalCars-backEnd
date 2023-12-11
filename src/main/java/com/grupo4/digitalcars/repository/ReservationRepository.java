package com.grupo4.digitalcars.repository;


import com.grupo4.digitalcars.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    //consultar reserva por ID de producto
    List<Reservation> findByProduct_Id(Integer productId);
    List<Reservation> findByUser_id(Integer userId);

    List<Reservation> findByProductIdAndCheckInDateBetween(Integer productId, LocalDate check_in_date, LocalDate check_out_date);

    List<Reservation> findByProductIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(Integer productId, LocalDate check_in_date, LocalDate check_out_date);


}
