package com.hjp.elevator.domain.entity;

import com.hjp.elevator.domain.enums.ElevatorStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Elevator {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  /**
   * 设备编码
   */
  private String code;

  private ElevatorStatus status;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public ElevatorStatus getStatus() {
    return status;
  }

  public void setStatus(ElevatorStatus status) {
    this.status = status;
  }

}
