package com.homs4j.expendlr.app.repository;

import com.homs4j.expendlr.app.model.PlannedPayment;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface PlannedPaymentRepository {
    public Optional<PlannedPayment> save(PlannedPayment payment);
    public Optional<PlannedPayment> update(PlannedPayment payment);
    public Optional<PlannedPayment> findById(String id);
    public List<PlannedPayment> findAllByDateInterval(Timestamp start, Timestamp end);
    public int delete(String id);
}
