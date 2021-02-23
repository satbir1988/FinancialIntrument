package com.rbc.dip.dataservice;

import java.util.stream.Stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rbc.fi.FinancialInstrument;

@SpringBootApplication
public class DataServiceApplication {
  public static void main(String[] args) {
    SpringApplication.run(DataServiceApplication.class, args);
    FinancialInstrument fi = new FinancialInstrument();

    Stream.of(30.00, 27.00, 31.00, 25.00, 18.00, 22.00)
        .forEach(
            price -> {
              fi.updateAmount(price);
              try {
                Thread.sleep(1000);
              } catch (InterruptedException e) {
                e.printStackTrace();
              }
            });
  }
}
