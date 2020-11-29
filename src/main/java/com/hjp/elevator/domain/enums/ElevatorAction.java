package com.hjp.elevator.domain.enums;

public enum ElevatorAction {
  // 上招指令
  UP_COMMAND,
  // 下招指令
  DOWN_COMMAND,
  // 上招且为当前楼层
  UP_CF_COMMAND,
  // 下招且为当前楼层
  DOWN_CF_COMMAND,
  // 到站
  ARRIVE_STATION_COMMAND,
  // 平成完成
  LEVELING_COMPLETED_COMMAND,
  // 开门指令
  OPEN_DOOR_COMMAND,
  // 关门超时
  CLOSE_DOOR_OVER_TIME,
  // 关门指令
  CLOSE_DOOR_COMMAND
}
