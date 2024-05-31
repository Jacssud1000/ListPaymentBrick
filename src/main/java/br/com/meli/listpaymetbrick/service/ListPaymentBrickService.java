package br.com.meli.listpaymetbrick.service;

import br.com.meli.listpaymetbrick.dtos.ListPayment;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ListPaymentBrickService {

  public String getList(@RequestBody ListPayment listPayment) {

    try {

      MercadoPagoConfig.setAccessToken("PROD_ACCESS_TOKEN");

      PreferenceClient client = new PreferenceClient();

      List<PreferenceItemRequest> items = new ArrayList<>();

      PreferenceItemRequest item =
          PreferenceItemRequest.builder()
              .title(listPayment.getTitle())
              .quantity(listPayment.getQuantity())
              .unitPrice(listPayment.getUnitPrice())
              .build();

      items.add(item);

      MPRequestOptions mpRequestOptions = MPRequestOptions.builder()
          .accessToken("PROD_ACCESS_TOKEN")
          .build();

      PreferenceRequest request = PreferenceRequest.builder()
          .items(items).build();

      Preference preference = client.create(request, mpRequestOptions);

      return preference.getId();

    } catch (MPException | MPApiException e) {
      return e.toString();
    }
  }
}
