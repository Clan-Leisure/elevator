package com.hjp.elevator;

import com.hjp.elevator.controller.ElevatorController;
import com.hjp.elevator.domain.enums.ElevatorAction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
class ElevatorApplicationTests {

  @Autowired
  ElevatorController elevatorController;

  @Test
  void contextLoads() {
    for (int i = 0; i < 100; i++) {
      Random random = new Random();
      int nextInt = random.nextInt(9);
      elevatorController.acceptCommand(1L, ElevatorAction.values()[nextInt]);
    }
  }


}
