package io.pact.workshop.product_catalogue.clients;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.pact.workshop.product_catalogue.models.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.lanwen.wiremock.ext.WiremockResolver;
import ru.lanwen.wiremock.ext.WiremockResolver.Wiremock;
import ru.lanwen.wiremock.ext.WiremockUriResolver;
import ru.lanwen.wiremock.ext.WiremockUriResolver.WiremockUri;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ExtendWith({ WiremockResolver.class, WiremockUriResolver.class })
class provider-xClientTest {
  @Autowired
  private provider-xClient provider-xClient;

  @Test
  void fetchProducts(@Wiremock WireMockServer server, @WiremockUri String uri) {
    provider-xClient.setBaseUrl(uri);
    server.stubFor(
      get(urlPathEqualTo("/products"))
        .willReturn(aResponse()
          .withStatus(200)
          .withBody("{\n" +
            "\"products\": [\n" +
            "            {\n" +
            "                \"id\": 9,\n" +
            "                \"type\": \"CREDIT_CARD\",\n" +
            "                \"name\": \"GEM Visa\",\n" +
            "                \"version\": \"v2\"\n" +
            "            },\n" +
            "            {\n" +
            "                \"id\": 10,\n" +
            "                \"type\": \"CREDIT_CARD\",\n" +
            "                \"name\": \"28 Degrees\",\n" +
            "                \"version\": \"v1\"\n" +
            "            }\n" +
            "        ]\n" +
            "\n}")
          .withHeader("Content-Type", "application/json"))
    );

    provider-xResponse response = provider-xClient.fetchProducts();
    assertThat(response.getProducts(), hasSize(2));
    assertThat(response.getProducts().stream().map(Product::getId).collect(Collectors.toSet()),
      is(equalTo(new HashSet<>(Arrays.asList(9L, 10L)))));
  }

  @Test
  void getProductById(@Wiremock WireMockServer server, @WiremockUri String uri) {
    provider-xClient.setBaseUrl(uri);
    server.stubFor(
      get(urlPathEqualTo("/product/10"))
        .willReturn(aResponse()
          .withStatus(200)
          .withBody("{\n" +
            "            \"id\": 10,\n" +
            "            \"type\": \"CREDIT_CARD\",\n" +
            "            \"name\": \"28 Degrees\",\n" +
            "            \"version\": \"v1\"\n" +
            "        }\n")
          .withHeader("Content-Type", "application/json"))
    );

    Product product = provider-xClient.getProductById(10);
    assertThat(product, is(equalTo(new Product(10L, "28 Degrees", "CREDIT_CARD", "v1", null))));
  }
}
