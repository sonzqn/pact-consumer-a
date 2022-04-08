package io.pact.workshop.product_catalogue.models;

import lombok.Data;

import java.util.List;

@Data
public class consumer-a {
  private final String name;
  private final List<Product> products;
}
