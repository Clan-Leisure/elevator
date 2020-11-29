package com.hjp.elevator.repo;

import com.hjp.elevator.domain.entity.Elevator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElevatorRepo extends JpaRepository<Elevator, Long> {
}
