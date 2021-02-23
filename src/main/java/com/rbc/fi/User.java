package com.rbc.fi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

  private Double threshold;
  private Double fluctuations;
  private String triggerType;
  private String userName;
}
