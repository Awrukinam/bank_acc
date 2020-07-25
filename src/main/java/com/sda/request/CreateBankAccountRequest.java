package com.sda.request;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;

import java.math.BigDecimal;

@Value
@AllArgsConstructor


// zmienne sa stale niezmienialne - ktworzy nam klase immutable + dodaje nam konstruktor, getter i pola staja sie finalne
// jest wrapperem (opakowaniem) na nasze zmienne, kod jest podatny na rozszezanie, jak dodam kolejne pola nie musze atworzyc nowych konstruktorow
public class CreateBankAccountRequest {

    @NonNull // adnotacja z lomboka
            String pesel;

    @NonNull // adnotacja lomboka - zmienna nie moze byc noolem
            BigDecimal initValue;

}
