package br.com.meli.listpaymetbrick.dtos;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ListPayment {
  private String title;
  private int quantity;
  private BigDecimal unitPrice;
}
