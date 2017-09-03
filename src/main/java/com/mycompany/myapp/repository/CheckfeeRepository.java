package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Checkfee;
import org.springframework.stereotype.Repository;

import com.datastax.driver.core.*;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Cassandra repository for the Checkfee entity.
 */
@Repository
public class CheckfeeRepository {

    private final Session session;

    private final Validator validator;

    private Mapper<Checkfee> mapper;

    private PreparedStatement findAllStmt;

    private PreparedStatement truncateStmt;

    public CheckfeeRepository(Session session, Validator validator) {
        this.session = session;
        this.validator = validator;
        this.mapper = new MappingManager(session).mapper(Checkfee.class);
        this.findAllStmt = session.prepare("SELECT * FROM checkfee");
        this.truncateStmt = session.prepare("TRUNCATE checkfee");
    }

    public List<Checkfee> findAll() {
        List<Checkfee> checkfeesList = new ArrayList<>();
        BoundStatement stmt = findAllStmt.bind();
        session.execute(stmt).all().stream().map(
            row -> {
                Checkfee checkfee = new Checkfee();
                checkfee.setId(row.getUUID("id"));
                checkfee.setFeeamount(row.getInt("feeamount"));
                checkfee.setReason(row.getString("reason"));
                checkfee.setStatus(row.getString("status"));
                return checkfee;
            }
        ).forEach(checkfeesList::add);
        return checkfeesList;
    }

    public Checkfee findOne(UUID id) {
        return mapper.get(id);
    }

    public Checkfee save(Checkfee checkfee) {
        if (checkfee.getId() == null) {
            checkfee.setId(UUID.randomUUID());
        }
        Set<ConstraintViolation<Checkfee>> violations = validator.validate(checkfee);
        if (violations != null && !violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
        mapper.save(checkfee);
        return checkfee;
    }

    public void delete(UUID id) {
        mapper.delete(id);
    }

    public void deleteAll() {
        BoundStatement stmt = truncateStmt.bind();
        session.execute(stmt);
    }
}
