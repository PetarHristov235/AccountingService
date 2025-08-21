package com.accounting.demo.repository;

import com.accounting.demo.entity.TransferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<TransferEntity, Long> {

    @Query("""
            SELECT t FROM TransferEntity t WHERE t.account.id = :accountId""")
    List<TransferEntity> findByAccountId(@Param("accountId") Long accountId);
}
