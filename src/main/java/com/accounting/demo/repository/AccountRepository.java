package com.accounting.demo.repository;

import com.accounting.demo.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    @Query("""
            SELECT a FROM AccountEntity a""")
    List<AccountEntity> findAllAccounts();

    Optional<AccountEntity> findById(Long id);

    @Query("""
            SELECT a FROM AccountEntity a WHERE a.name = :name AND a.id = :id""")
    Optional<AccountEntity> findByNameAndId(@Param("name") String name, @Param("id") Long id);

    @Query(value = """
            SELECT exists(SELECT 1 FROM public.accounts a WHERE a.name = :name OR a.iban = :iban) """,
            nativeQuery = true)
    boolean existsByNameOrIban(@Param("name") String name, @Param("iban") String iban);

}
