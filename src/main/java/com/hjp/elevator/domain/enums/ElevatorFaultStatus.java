package com.hjp.elevator.domain.enums;

public enum ElevatorFaultStatus {
  RUN_ERROR("RUN_ERROR", "电梯异常运行"),
  OPEN_DOOR_ERROR("OPEN_DOOR_ERROR", "电梯异常开门"),
  LEVELING_ERROR("LEVELING_ERROR", "电梯平层异常"),
  RUNNING_OPEN_DOOR_ERROR("RUNNING_OPEN_DOOR_ERROR", "电梯运行中开门"),
  OPENING_DOOR_ERROR("OPENING_DOOR_ERROR", "电梯门无法开启"),
  OPENED_DOOR_ERROR("OPENED_DOOR_ERROR", "电梯门无法开门到位"),
  OPEN_DOOR_OVER_TIME("OPEN_DOOR_OVER_TIME", "电梯开门超时"),
  CLOSING_DOOR_ERROR("CLOSING_DOOR_ERROR", "电梯关门异常"),
  CLOSED_DOOR_ERROR("CLOSED_DOOR_ERROR", "电梯门无法关门到位"),
  PEOPLE_IN_DOOR_ERROR("PEOPLE_IN_DOOR_ERROR", "电梯卡人"),
  START_ERROR("START_ERROR", "电梯无法启动"),
  ;
  private String code;
  private String name;

  ElevatorFaultStatus(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
