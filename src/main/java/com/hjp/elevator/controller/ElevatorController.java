package com.hjp.elevator.controller;

import com.hjp.elevator.domain.entity.Elevator;
import com.hjp.elevator.domain.enums.ElevatorAction;
import com.hjp.elevator.service.ElevatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ElevatorController {

  private ElevatorService elevatorService;

  public ElevatorController(ElevatorService elevatorService) {
    this.elevatorService = elevatorService;
  }

  @GetMapping(value = "/change-status")
  public void acceptCommand(@RequestParam("eleId") Long eleId, @RequestParam("action") ElevatorAction action) {
    Elevator elevator = elevatorService.getElevatorById(eleId);
    elevatorService.acceptCommand(elevator, action);
  }
}
