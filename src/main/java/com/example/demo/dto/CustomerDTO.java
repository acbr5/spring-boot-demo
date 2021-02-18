package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data

// değerleri kayıp olmadan kullanabilmek için Serializable kullanılır
// genellikle isimlendirme bu şekilde yapılır: TabloAdiDTO
public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = 1L; // serileştirme numarası oluşturuldu. B unu yapmak zorunlu değil ama yapılmalı.

   // @JsonIgnore // bu anotasyon ile istek atan kullanıcıya kullanıcı adının görünmesi engellenebilir. Kullanıcı, username'i göremez.
    @JsonProperty("kullanıcı adı") // görüntülenme şeklini değiştirmek için
    private String username;

    @JsonProperty("ad")
    private String name;

    @JsonProperty("soyad")
    private String surname;

    @JsonProperty ("address")
    private AddressDTO address;
}
