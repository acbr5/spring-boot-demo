package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;

// HelloController.java adlı bir dosya ekledik.
//@RestController anatasyonu, RESTfull özellikleri taşıyan bir controller oluşturmak istenildiğinde eklenilir.
@RestController

// RequestMapping anotasyonu, Controller'ın URL'ine genel bir isim koymak için kullanılır.
@RequestMapping("/api/v1")
public class HelloController {

    // bir sunucuya get isteğinde bulunulduğunda GetMapping anotasyonu kullanılır. Sunucudan geri değer döndürülür.
    // Url tarafında sayfa ismi yazması için "/" (anasayfa için), "/hello" (hello sayfası için) gibi ifadeler kullanılır.
    // GET isteği yapmak için GetMapping, POST isteği yapmak için PostMapping
    @GetMapping("/hello")
    String helloo(){
        return "Helloo World!";
    }
}



/*
Proje çalıştırılmadan önce spring-boot-starter-data-jpa ve postgresql bağımlılıkları pom.xml dosyasında
yorum satırları içerisine alınır. Çünkü henüz veritabanı eklenmedi. Veritabanı ile işlem yapıldığında açılmalı.

- Projeyi çalıştırdıktan sonra Postman indirilir, kurulur ve hesap açılır.
Postman, istekleri yönetmek, istekler üzerinde işlem yapmak için kullanılır.

- Postman'de yeni bir Workspace ve Collections oluşturulur.
  Oluşturulan Collections'ın üzerine sağ tıklayarak Add Request seçeneği seçilir.
  İstek URL'i kısmına şu yazılır: http://localhost:8080/api/v1/hello
  (hello olan get isteğini çalıştırmak istediğimiz için)
 */