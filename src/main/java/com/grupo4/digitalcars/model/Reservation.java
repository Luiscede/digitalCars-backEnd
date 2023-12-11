package com.grupo4.digitalcars.model;


import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDate;


@Entity
@Table(name="reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Time arrival_time;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String comments;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Reservation() {
    }

    public Reservation(Integer id, Time arrival_time, LocalDate checkInDate, LocalDate checkOutDate, String comments, Product product, User user) {
        this.id = id;
        this.arrival_time = arrival_time;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.comments = comments;
        this.product = product;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Time getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(Time arrival_time) {
        this.arrival_time = arrival_time;
    }

    public LocalDate getCheck_in_date() {
        return checkInDate;
    }

    public void setCheck_in_date(LocalDate check_in_date) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckout_date() {
        return checkOutDate;
    }

    public void setCheckout_date(LocalDate checkout_date) {
        this.checkOutDate = checkOutDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
