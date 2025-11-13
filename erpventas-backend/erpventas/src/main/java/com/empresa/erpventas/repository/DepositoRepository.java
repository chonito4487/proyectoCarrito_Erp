package com.empresa.erpventas.repository;

import com.empresa.erpventas.entities.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositoRepository extends JpaRepository <Deposito,Long> {
}
