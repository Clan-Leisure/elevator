package com.hjp.elevator.domain.entity;


import com.hjp.elevator.domain.enums.ElevatorFaultStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ElevatorFault {

  /**
   * 故障id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * 设备代码
   */
  private String elevatorCode;

  /**
   * 故障类型
   */
  private ElevatorFaultStatus elevatorFaultStatus;

  /**
   * 故障来源
   */
  private String faultSource = "FSM状态转移检测算法";

  /**
   * 故障发生时间
   */
  private Date startTime = new Date();

  /**
   * 救援人数
   */
  private int rescueNum = 0;

  /**
   * 故障报告方
   */
  private String faultReport;

  /**
   * 故障报告方电话
   */
  private String faultReportPhone;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getElevatorCode() {
    return elevatorCode;
  }

  public void setElevatorCode(String elevatorCode) {
    this.elevatorCode = elevatorCode;
  }

  public ElevatorFaultStatus getElevatorFaultStatus() {
    return elevatorFaultStatus;
  }

  public void setElevatorFaultStatus(ElevatorFaultStatus elevatorFaultStatus) {
    this.elevatorFaultStatus = elevatorFaultStatus;
  }

  public String getFaultSource() {
    return faultSource;
  }

  public void setFaultSource(String faultSource) {
    this.faultSource = faultSource;
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public int getRescueNum() {
    return rescueNum;
  }

  public void setRescueNum(int rescueNum) {
    this.rescueNum = rescueNum;
  }

  public String getFaultReport() {
    return faultReport;
  }

  public void setFaultReport(String faultReport) {
    this.faultReport = faultReport;
  }

  public String getFaultReportPhone() {
    return faultReportPhone;
  }

  public void setFaultReportPhone(String faultReportPhone) {
    this.faultReportPhone = faultReportPhone;
  }
}
