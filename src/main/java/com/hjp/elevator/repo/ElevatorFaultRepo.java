package com.hjp.elevator.repo;

import com.hjp.elevator.domain.entity.ElevatorFault;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElevatorFaultRepo extends JpaRepository<ElevatorFault, Long> {

}
