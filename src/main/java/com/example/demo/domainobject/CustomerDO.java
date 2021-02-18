package com.example.demo.domainobject;

import lombok.Data;

import javax.persistence.*;

// @Data veritabanından bağımsızdır. Getter ve setter'ları otomatik oluşturur.
@Data

// @Entity anotasyonu, veritabanında bu şekilde var olacağı anlamına gelmektedir. Veritabanı bu sınıf içerisindeki ile aynı özelliklere sahip olmalı.
@Entity

// Tablo oluşturulur. @Entity("customers") yazılarak da tablo oluşturulabilirdi.
@Table(name = "customers")

// Bu sınıf içerisinde veritabanının olması istenilen formatta oluşturulur.
public class CustomerDO {
    // ID değerini belirtmek için kullanılır
    @Id
    // arka planda oluşturulması istenildiği için kullanılır. ID tipine göre generate etmek için aşağıdaki gibi kullanılır:
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Kullanıcı adı için boş geçilemeyen ve uniq olan kolon oluşturuldu:
    @Column(nullable = false, unique = true)
    private String username;

    private String name;

    private String surname;

    // tablolar arasında birebir ilişki
    @OneToOne(cascade = CascadeType.ALL) // adres tablosunda bir değişiklik yapldığında otomatik burada da yapılsın
    @JoinColumn(name = "address_id") // foreign key'in tutulduğu veritabanındaki değişken
    private AddressDO address;
}
