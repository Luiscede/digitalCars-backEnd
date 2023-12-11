package com.grupo4.digitalcars.services;

import com.grupo4.digitalcars.model.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationService {


    List<Reservation> findByUser_id(Integer userId);
    List<Reservation> findByProduct_id(Integer productId);
    List<Reservation> getAllReservation();

    List<LocalDate> obtenerFechasDisponiblesParaDosMeses(Integer productId);

    List<Reservation> findByProductIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(Integer productId, LocalDate check_in_date, LocalDate check_out_date);
    Optional<Reservation> getReservationById(Integer id);


    Reservation saveReservation(Reservation reservation);

    Reservation updateReservation(Reservation reservation);
    void deleteReservationById(Integer reservation);



}
