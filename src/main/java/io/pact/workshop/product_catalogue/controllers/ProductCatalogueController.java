package io.pact.workshop.product_catalogue.controllers;

import io.pact.workshop.product_catalogue.clients.provider-xClient;
import io.pact.workshop.product_catalogue.models.consumer-a;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class consumer-aController {
  @Autowired
  private provider-xClient provider-xClient;

  @GetMapping("/catalogue")
  public String catalogue(Model model) {
    consumer-a catalogue = new consumer-a("Default Catalogue", provider-xClient.fetchProducts().getProducts());
    model.addAttribute("catalogue", catalogue);
    return "catalogue";
  }

  @GetMapping("/catalogue/{id}")
  public String catalogue(@PathVariable("id") Long id, Model model) {
    model.addAttribute("product", provider-xClient.getProductById(id));
    return "details";
  }
}
