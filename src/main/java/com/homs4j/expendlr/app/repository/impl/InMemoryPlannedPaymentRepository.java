package com.homs4j.expendlr.app.repository.impl;

import com.homs4j.expendlr.app.model.PlannedPayment;
import com.homs4j.expendlr.app.repository.PlannedPaymentRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class InMemoryPlannedPaymentRepository implements PlannedPaymentRepository {

    @Override
    public Optional<PlannedPayment> save(PlannedPayment payment) {
        String SQL = "INSERT INTO planned_payment " +
                "VALUES(:id,:title,:amount,:startDate,:endDate,:interval,:categoryId,:accountId)";
        return Optional.empty();
    }

    @Override
    public Optional<PlannedPayment> update(PlannedPayment payment) {
        String SQL = "";
        return Optional.empty();
    }

    @Override
    public Optional<PlannedPayment> findById(String id) {
        String SQL = "SELECT * FROM planned_payment WHERE planned_payment_id = :id";
        return Optional.empty();
    }

    @Override
    public List<PlannedPayment> findAllByDateInterval(Timestamp start, Timestamp end) {
        String SQL = "SELECT * FROM planned_payment WHERE start_date > :start AND end_date < :end";
        return null;
    }

    @Override
    public int delete(String id) {
        String SQL = "DELETE FROM planned_payment WHERE planned_payment_id = :id";
        return 0;
    }

}
