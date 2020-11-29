package com.hjp.elevator.service;

import com.hjp.elevator.domain.entity.Elevator;
import com.hjp.elevator.domain.entity.ElevatorFault;
import com.hjp.elevator.domain.enums.ElevatorFaultStatus;
import com.hjp.elevator.repo.ElevatorFaultRepo;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ElevatorFaultService {
  private ElevatorFaultRepo elevatorFaultRepo;

  public ElevatorFaultService(ElevatorFaultRepo elevatorFaultRepo) {
    this.elevatorFaultRepo = elevatorFaultRepo;
  }

  public void createFaultRecord(Elevator elevator, ElevatorFaultStatus elevatorFaultStatus) {
    String[] stringsName = {
        "江苏科技大学",
        "镇江中南新锦城房地产发展有限公司",
        "镇江明旺房地产开发有限公司",
        "江苏基尔特置业有限公司",
        "江苏爵鼎车业集团有限公司",
        "南京丰盛大族科技股份有限公司",
        "江苏爵鼎车业集团有限公司",
        "中国移动通信集团江苏有限公司镇江分公司",
        "镇江幸福基业房地产开发有限公司",
        "江苏恒晟地产有限公司",
        "镇江市中建地产有限公司",
        "江苏钟山度假开发有限公司",
        "镇江协信房地产开发有限公司"
    };
    String[] stringsPhone = {
        "17715377592",
        "15755097593",
        "84440000",
        "13915855630",
        "18151913588",
        "14751703747",
        "13915855630",
        "18661178155",
        "13921556750",
        "18516018982",
        "15205287066",
        "15715165757",
        "15189487536"
    };
    Random random = new Random();
    int i = random.nextInt(stringsPhone.length);
    ElevatorFault elevatorFault = new ElevatorFault();
    elevatorFault.setElevatorCode(elevator.getCode());
    elevatorFault.setElevatorFaultStatus(elevatorFaultStatus);
    elevatorFault.setFaultReport(stringsName[i]);
    elevatorFault.setFaultReportPhone(stringsPhone[i]);
    Random rescue = new Random();
    elevatorFault.setRescueNum(rescue.nextInt(10));
    elevatorFaultRepo.save(elevatorFault);
  }
}
