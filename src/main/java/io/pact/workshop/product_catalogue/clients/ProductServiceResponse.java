package io.pact.workshop.product_catalogue.clients;

import io.pact.workshop.product_catalogue.models.Product;
import lombok.Data;

import java.util.List;

@Data
public class provider-xResponse {
  private List<Product> products;
}
