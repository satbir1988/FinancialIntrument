package com.rbc.fi;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FinancialInstrument {

  Logger logger = LoggerFactory.getLogger(FinancialInstrument.class);

  public static final String SELL_TRIGGER = "Sell";
  public static final String BUY_TRIGGER = "Buy";

  private Double price;
  private Double previousPrice = 0.0;

  public void updateAmount(Double price) {
    this.price = price;
    this.notifySubscribers();
    this.previousPrice = price;
  }

  private List<User> getSubscribers() {
    return Stream.of(
            new User(24.00, 2.00, SELL_TRIGGER, "Jon"),
            new User(27.00, .25, BUY_TRIGGER, "Arya"),
            new User(24.00, .25, SELL_TRIGGER, "Sansa"))
        .collect(Collectors.toList());
  }

  private void notifySubscribers() {
    this.getSubscribers()
        .parallelStream()
        .filter(this::eligibleToNotify)
        .forEach(this::sendNotification);
  }

  private boolean eligibleToNotify(User user) {
    if (SELL_TRIGGER.equals(user.getTriggerType())
        && user.getThreshold() < this.price && this.previousPrice < this.price
        && this.price - this.previousPrice > user.getFluctuations()) {
      return true;
    }

    if (BUY_TRIGGER.equals(user.getTriggerType())
    		&& user.getThreshold() > this.price && this.previousPrice > this.price
            && this.previousPrice - this.price > user.getFluctuations()) {
      return true;
    }
    return false;
  }

  private void sendNotification(User user) {
    logger.info(
        "Dear {}, It is good time to {} now at price {}",
        user.getUserName(),
        user.getTriggerType(),
        this.price);
  }
}
