package com.grupo4.digitalcars.services.impl;


import com.grupo4.digitalcars.model.Product;
import com.grupo4.digitalcars.model.Reservation;
import com.grupo4.digitalcars.repository.ProductRepository;
import com.grupo4.digitalcars.repository.ReservationRepository;
import com.grupo4.digitalcars.repository.UserRepository;
import com.grupo4.digitalcars.services.ProductService;
import com.grupo4.digitalcars.services.ReservationService;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Reservation> findByProduct_id(Integer productId) {
        return reservationRepository.findByProduct_Id(productId);
    }

    @Override
    public List<Reservation> findByUser_id(Integer userId) {
        return reservationRepository.findByUser_id(userId);
    }

    @Override
    public List<Reservation> getAllReservation() {
        return reservationRepository.findAll();
    }

    @Override
    public Optional<Reservation> getReservationById(Integer id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
        return reservation;
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservationById(Integer id) {
        reservationRepository.deleteById(id);
    }

    public List<LocalDate> obtenerFechasDisponiblesParaDosMeses(Integer productId) {

        // Paso 1: Obtener el auto por ID

        Product product= productRepository.findById(productId).orElse(null);

        if (product == null) {

            return null;
        }

        // Paso 2: Obtener todas las fechas reservadas para el automóvil en los próximos ''dos meses''
        LocalDate hoy = LocalDate.now();
        LocalDate primerDiaDelProximoMes = hoy.plusMonths(1).withDayOfMonth(1);
        LocalDate ultimoDiaDelSiguienteMes = hoy.plusMonths(2).withDayOfMonth(hoy.plusMonths(2).lengthOfMonth());
        List<LocalDate> fechasReservadas = reservationRepository.findByProductIdAndCheckInDateBetween(productId, hoy, ultimoDiaDelSiguienteMes)
                .stream()
                .flatMap(reservation -> obtenerRangoDeFechas(reservation.getCheck_in_date(), reservation.getCheckout_date()).stream())
                .collect(Collectors.toList());

        // Paso 3: Generar todas las fechas en los próximos dos meses
        List<LocalDate> todasLasFechasEnElRango = obtenerRangoDeFechas(primerDiaDelProximoMes, ultimoDiaDelSiguienteMes);

        // Paso 4: Filtrar las fechas disponibles
        List<LocalDate> fechasDisponibles = todasLasFechasEnElRango.stream()
                .filter(fecha -> !fechasReservadas.contains(fecha))
                .collect(Collectors.toList());

        return fechasDisponibles;
    }

    private List<LocalDate> obtenerRangoDeFechas(LocalDate check_in_date, LocalDate check_out_date) {
        return check_in_date.datesUntil(check_out_date.plusDays(1)).collect(Collectors.toList());}

    public List<Reservation> findByProductIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(Integer productId, LocalDate check_in_date, LocalDate check_out_date) {
        return reservationRepository.findByProductIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(productId, check_in_date, check_out_date);
    }
}
