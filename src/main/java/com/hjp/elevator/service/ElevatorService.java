package com.hjp.elevator.service;

import com.hjp.elevator.domain.entity.Elevator;
import com.hjp.elevator.domain.enums.ElevatorAction;
import com.hjp.elevator.domain.enums.ElevatorFaultStatus;
import com.hjp.elevator.domain.enums.ElevatorStatus;
import com.hjp.elevator.repo.ElevatorRepo;
import org.springframework.stereotype.Service;

@Service
public class ElevatorService {

  private ElevatorRepo elevatorRepo;
  private ElevatorFaultService elevatorFaultService;

  public ElevatorService(ElevatorRepo elevatorRepo, ElevatorFaultService elevatorFaultService) {
    this.elevatorRepo = elevatorRepo;
    this.elevatorFaultService = elevatorFaultService;
  }

  public void acceptCommand(Elevator elevator, ElevatorAction action) {
    ElevatorStatus status = elevator.getStatus();
    switch (action) {
      case UP_COMMAND:
        System.out.println("开始执行命令，命令为：上招指令");
        if (status.equals(ElevatorStatus.STOP)) {
//          elevator.setStatus(ElevatorStatus.RUNNING);
//          elevatorRepo.save(elevator);
          if (!checkEleStatus(elevator.getId(), ElevatorStatus.RUNNING)) {
            elevatorFaultService.createFaultRecord(elevator, ElevatorFaultStatus.RUN_ERROR);
            System.out.println("电梯异常运行");
          }
        } else if (status.equals(ElevatorStatus.CLOSED)) {
//          elevator.setStatus(ElevatorStatus.RUNNING);
//          elevatorRepo.save(elevator);
          if (!checkEleStatus(elevator.getId(), ElevatorStatus.RUNNING)) {
            elevatorFaultService.createFaultRecord(elevator, ElevatorFaultStatus.START_ERROR);
            System.out.println("电梯启动异常");
          }
        } else {
          System.out.println("当前命令不满足执行条件，命令为：下招指令");
        }
        break;
      case DOWN_COMMAND:
        System.out.println("开始执行命令，命令为：下招指令");
        if (status.equals(ElevatorStatus.STOP)) {
//          elevator.setStatus(ElevatorStatus.RUNNING);
//          elevatorRepo.save(elevator);
          if (!checkEleStatus(elevator.getId(), ElevatorStatus.RUNNING)) {
            elevatorFaultService.createFaultRecord(elevator, ElevatorFaultStatus.RUN_ERROR);
            System.out.println("电梯异常运行");
          }
        } else if (status.equals(ElevatorStatus.CLOSED)) {
//          elevator.setStatus(ElevatorStatus.RUNNING);
//          elevatorRepo.save(elevator);
          if (!checkEleStatus(elevator.getId(), ElevatorStatus.RUNNING)) {
            elevatorFaultService.createFaultRecord(elevator, ElevatorFaultStatus.START_ERROR);
            System.out.println("电梯启动异常");
          }
        } else {
          System.out.println("当前命令不满足执行条件，命令为：下招指令");
        }
        break;
      case UP_CF_COMMAND:
        System.out.println("开始执行命令，命令为：上招且为当前楼层指令");
        if (status.equals(ElevatorStatus.STOP)) {
//          elevator.setStatus(ElevatorStatus.RUNNING);
//          elevatorRepo.save(elevator);
          if (!checkEleStatus(elevator.getId(), ElevatorStatus.RUNNING)) {
            elevatorFaultService.createFaultRecord(elevator, ElevatorFaultStatus.OPENING_DOOR_ERROR);
            System.out.println("电梯门无法开启");
          }
        } else {
          System.out.println("当前命令不满足执行条件，命令为：上招且为当前楼层指令");
        }
        break;
      case DOWN_CF_COMMAND:
        System.out.println("开始执行命令，命令为：下招且为当前楼层指令");
        if (status.equals(ElevatorStatus.STOP)) {
//          elevator.setStatus(ElevatorStatus.RUNNING);
//          elevatorRepo.save(elevator);
          if (!checkEleStatus(elevator.getId(), ElevatorStatus.RUNNING)) {
            elevatorFaultService.createFaultRecord(elevator, ElevatorFaultStatus.OPENING_DOOR_ERROR);
            System.out.println("电梯门无法开启");
          }
        } else {
          System.out.println("当前命令不满足执行条件，命令为：下招且为当前楼层指令");
        }
        break;
      case ARRIVE_STATION_COMMAND:
        System.out.println("开始执行命令，命令为：到站指令");
        if (status.equals(ElevatorStatus.RUNNING)) {
//          elevator.setStatus(ElevatorStatus.ARRIVE);
//          elevatorRepo.save(elevator);
          if (!checkEleStatus(elevator.getId(), ElevatorStatus.ARRIVE)) {
            elevatorFaultService.createFaultRecord(elevator, ElevatorFaultStatus.LEVELING_ERROR);
            System.out.println("电梯平层异常");
          }
        } else {
          System.out.println("当前命令不满足执行条件，命令为：到站指令");
        }
        break;
      case LEVELING_COMPLETED_COMMAND:
        System.out.println("开始执行命令，命令为：平层完成指令");
        if (status.equals(ElevatorStatus.ARRIVE)) {
//          elevator.setStatus(ElevatorStatus.OPENING);
//          elevatorRepo.save(elevator);
          if (!checkEleStatus(elevator.getId(), ElevatorStatus.OPENING)) {
            elevatorFaultService.createFaultRecord(elevator, ElevatorFaultStatus.OPENING_DOOR_ERROR);
            System.out.println("电梯门无法开启");
          }
        } else {
          System.out.println("当前命令不满足执行条件，命令为：平层完成指令");
        }
        break;
      case OPEN_DOOR_COMMAND:
        System.out.println("开始执行命令，命令为：执行开门指令");
        if (status.equals(ElevatorStatus.OPENING)) {
//          elevator.setStatus(ElevatorStatus.OPENED);
//          elevatorRepo.save(elevator);
          if (!checkEleStatus(elevator.getId(), ElevatorStatus.OPENING)) {
            elevatorFaultService.createFaultRecord(elevator, ElevatorFaultStatus.OPENED_DOOR_ERROR);
            System.out.println("电梯门无法开门到位");
          }
          // 检查电梯是否开门超时
          checkOpeningTime(elevator);
          // 开门到位 5s 之后会自动执行关门命令
          checkOpenedTime(elevator);
        } else {
          System.out.println("当前命令不满足执行条件，命令为：执行开门指令");
        }
        break;
      case CLOSE_DOOR_OVER_TIME:
        System.out.println("开始执行命令，命令为：开门到位超时指令（自动关门）");
        if (status.equals(ElevatorStatus.OPENED)) {
//          elevator.setStatus(ElevatorStatus.CLOSING);
//          elevatorRepo.save(elevator);
          if (!checkEleStatus(elevator.getId(), ElevatorStatus.CLOSING)) {
            elevatorFaultService.createFaultRecord(elevator, ElevatorFaultStatus.CLOSING_DOOR_ERROR);
            System.out.println("电梯关门异常");
          }
        } else {
          System.out.println("当前命令不满足执行条件，命令为：开门到位超时指令（自动关门）");
        }
        break;
      case CLOSE_DOOR_COMMAND:
        System.out.println("开始执行命令，命令为：执行关门指令");
        if (status.equals(ElevatorStatus.OPENED)) {
//          elevator.setStatus(ElevatorStatus.CLOSING);
//          elevatorRepo.save(elevator);
          if (!checkEleStatus(elevator.getId(), ElevatorStatus.CLOSING)) {
            elevatorFaultService.createFaultRecord(elevator, ElevatorFaultStatus.CLOSING_DOOR_ERROR);
            System.out.println("电梯关门异常");
          }
        } else if (status.equals(ElevatorStatus.CLOSING)) {
//          elevator.setStatus(ElevatorStatus.CLOSED);
//          elevatorRepo.save(elevator);
          if (!checkEleStatus(elevator.getId(), ElevatorStatus.CLOSED)) {
            elevatorFaultService.createFaultRecord(elevator, ElevatorFaultStatus.CLOSED_DOOR_ERROR);
            System.out.println("电梯门无法关门到位");
          }
        } else {
          System.out.println("当前命令不满足执行条件，命令为：执行关门指令");
        }
        break;
      default:
        break;
    }
  }

  public Elevator getElevatorById(Long id) {
    return elevatorRepo.findById(id).orElse(null);
  }

  public boolean checkEleStatus(Long id, ElevatorStatus elevatorStatus) {
    Elevator elevator = getElevatorById(id);
    return elevator.getStatus().equals(elevatorStatus);
  }

  /**
   * 检查开门到位状态是否超时
   * 若超时则自动执行关门指令
   *
   * @param elevator 电梯对象
   */
  private void checkOpenedTime(Elevator elevator) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(5000);
          if (elevator.getStatus().equals(ElevatorStatus.OPENED)) {
            acceptCommand(elevator, ElevatorAction.CLOSE_DOOR_OVER_TIME);
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    Thread thread = new Thread(runnable);
    thread.start();
  }

  /**
   * 检查开门到位状态是否超时
   * 若超时则自动执行关门指令
   *
   * @param elevator 电梯对象
   */
  private void checkOpeningTime(Elevator elevator) {
    Runnable runnable = new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(5000);
          if (elevator.getStatus().equals(ElevatorStatus.OPENING)) {
            elevatorFaultService.createFaultRecord(elevator, ElevatorFaultStatus.OPEN_DOOR_OVER_TIME);
            System.out.println("电梯开门超时");
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    };
    Thread thread = new Thread(runnable);
    thread.start();
  }
}
