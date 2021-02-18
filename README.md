# Spring Initializr ile Spring-Boot Projesi Oluşturma

- https://start.spring.io/ web sitesine gidilir.

- Maven ve Gradle, bağımlılıkları yöneten araçlardır.

- Uygulama bilgileri girilir:
  com.demo
  com.sirket_adi

- Dependencies, uygulamadaki bağımlılıkları, paketleri temsil eder.

- Dependencies kısmından Spring Web, Spring Data JPA, PostgreSQL Driver ve Lombok bağımlılıklarını ilk projemiz için ekledik. Daha sonra bunlar üzerinde düzenleme yapılabilir.

- Generate edilerek dosya indirilir.

- İndirilen sıkıştırılmış dosya dışarı aktarılarak derleyicide açılır. (spring-demo isimli repository'de kodlar bulunmaktadır.)


# Spring Hakkında

- .mvn bağımlıkları yöneten araçtır.

- src programın kaynağını temsili eder.

- target, program derendikten sonra oluşan sınıfları içerir fakat biz geliştirme yaparken src/main kısmı ile ilgileneceğiz.

- src/resources/application.properties kısmında genellikle uygulama için başlangıç ayarları yapılır.
  Örneğin uygulama için sabit olması istenilen belli özelliklerin tanımı, Spring'in kendi ayarları, veritabanının bağlantı tanımı gibi uygulamaya dair özellikler burada belirtilir.

- src/resources/templates kısmında görsel olarak yapılacak şeyler burada yer alır. Thymeleaf bağımlılığı kullanılarak ön-yüz geliştirmesi yapılabilir.

- pom.xml dosyasında bağımlılıklar bulunur.

**** Kodlarla ilgili açıklamalar her paketin içerisindeki kodlarda bulunmaktadır.*******

# Spring Katmanları:
Katmanlar takım olarak geliştirme kolaylığını ve katmanlar arasındaki soyutlamayı sağlar. SOLID prensibini uygulamayı yönlendirir.
Bu yapının tercih edilme sebebi kodlar karmaşıklaştıkça yönetmesinin kolay olması ve bug çıktığında daha kolay müdahale edilmesinden dolayıdır.
- controller: İstemci ile ilişki kurmak için kullanılır. "Bu URL'de bana istek at ben sana şunu döneyim" gibi.

- model (ya da do): Veritabanındaki tabloları temsil eder. İki türlü model yapısı vardır.
  Veritabanındaki modeli temsil eder. Veritabanında oluşturulan model yapısı, oluşturulan sınıf ile eşleşmeli. Veritabanındaki modeller burada oluşturulur. do, domain object olarak da isimlendirmeleri vardır.

- service: Veritabanı ile istemci arasındaki köprüdür. İstemci ile konuşan controller ve veritabanındaki işlemler arasındaki yapmak istenilenler burada yapılır.
  İstemci ile konuşan serviste veritabanı işlemleri yapmak hem güvenlik hem de tasarım hiyerarşisi açısından uygun olmaz.

- repository: Direkt veritabanına kod tarafında müdahale edilmesini sağlayan katmandır. Veri getirme, ekleme gibi işlemler JPA kullanılarak yapılır.
  Service tarafında soyutlama yapılır. Sadece erişilmesi istenilen işlemlerin controller üzerinden çağırılması için repository ve service kısımları ayrıdır.

- dto: data transfer object. Genellikle istemci tarafında nesne tanımlandığında dto modeli oluşturulur. Bu dto modeli, direkt veritabanı ile eşleşmek zorunda değil. Domain object ise veritabanının birebir aynısıdır. Veritabanının bir nesnesi gibi yani.
  DTO istemci tarafına, DO veritabanı tarafına daha yakındır.


# İlk Uygulama (Hello World)

- main/java/com/example/demo içerisine controller isimli bir klasör oluşturalım
  Controller kısmında uygulamaya istemci tarafından gelen istekler yönetilir. İstemciden böyle bir istek gelirse şunları yap vb.

- HelloController.java adlı bir dosya ekledik. Ekrana "Helloo World!" yazan bir sınıf.
  @RestController anatasyonu, RESTfull özellikleri taşıyan bir controller oluşturmak istenildiğinde eklenilir. Oluşturduğumuz dosyaya ekledik.

- Proje çalıştırılmadan önce spring-boot-starter-data-jpa ve postgresql bağımlılıkları pom.xml dosyasında yorum satırları içerisine alınır. Çünkü henüz veritabanı eklenmedi. Eklendiği zaman açılacak. (bunu ilk çalıştırdığımızda yaptık, sonrasında veritabanını ekledik)

- Projeyi çalıştırdıktan sonra Postman indirilir, kurulur ve hesap açılır.
  Postman, istekleri yönetmek ve istekler üzerinde işlem yapmak için kullanılır.

- Postman'de yeni bir Workspace ve Collections oluşturulur.
  Oluşturulan Collections'ın üzerine sağ tıklayarak Add Request seçeneği seçilir.
  İstek URL'i kısmına şu yazılır: http://localhost:8080/api/v1/hello
  (hello olan get isteğini çalıştırmak istediğimiz için)

  
# İkinci Uygulama (Veritabanı Bağlantısı Olan)
- controller/CustomerController sınıfı CustomerService'ini kullanarak bir kullanıcı ekler, siler, günceller, getirir ve tüm kullanıcıları getitirir.
- domainobject/CustomerDO sınıfında veritabanı için Customer adlı bir tablo ve kolonları otomatik oluşturacak olan kodlar var.
- domainobject/AddressDO sınıfında veritabanı için Address adlı bir tablo ve kolonları otomatik oluşturacak olan kodlar var.
- dto/CustomerDTO sınıfında istemciye gönderilecek olan bazı (güvenlik açısından) kısıtlanmış veritabanı bilgileri var.
- repository/CustomerRepository sınıfında veritabanı ile ilgili ekleme, silme, güncelleme işlemlerini yapan metotlar var. (Direkt veritabanında işlem yapar)
- services/CustomerService sınıfında veritabanı ile ilgili ekleme, silme, güncelleme işlemlerini yapan metotlar var. (Buradaki metotlar CustomerRepository'deki işlemlerin temsili gibidir. Repository sayesinde işlem yapar ve Controller'la direkt iletişimde olduğu için bir nevi güvenlik katmanı gibidir.)
- resources/application.properties dosyasını application.yml olarak değiştirdik (okuma açısından daha rahat olduğu için)
- datasource | url kısmında linkin en sonundaki isim veritabanının ismidir. Veritabanını PGAdmin'de oluşturduktan sonra bu Spring projesi çalıştırıldığında otomatik olarak tablolar ve kolonlar eklenecektir. 
- Application dosyasında gerekli ayarlamaları yaptık. Eğer veritabanını ilk kez oluşturacaksak ddl-auto değeri "create-drop" olarak ayarlanır. Fakat daha sonraki çalıştırmalarda "update" olarak değiştirilir.
- Postman üzerinden GET, POST, PUT, DELETE isteklerini gönderdik. Customers.postman_collection.json isimli belgede tüm istekler ve cevapları bulunmaktadır.
* Service, Repository işlemlerinin yapıldığı interface'lerin başına @Repository, @Service yazılır. Bunun nedeni programda repository ve service sınıflarını Spring'e tanıtmak içindir.


# UNIT Testleri
* junit, birim testi yapmayı sağlayan bağımlılıktır. RunWith() anotasyonunu kullanmak için pom.xml'e bu bağımlılık eklenir.
* ModelMapper, iki farklı türü aralarındaki özellikleri birbirini tanıyacak şekilde map etmek için kullanılır. pom.xml'e org.modelmapper bağımlılığı eklendi.

- test/java/com/example/demo/DemoApplicationTests sınıfında birim testleri gerçekleştirildi. 
- Birim testler için junit ve org.springframework bağımlılıkları pom.xml dosyasına eklendi.


# Swagger UI
Uygulamanın içerisinde dokümantasyon oluşturulmasını sağlar. Swagger UI sayesinde GET, POST gibi metotlar gözlemlenebiliyor ve oluşturulan doküman içerisinde kodlar çalıştırılabilir.
Oluşturulan metotları dokümante eder ve bunların gözlemlenip yönetilebileceği dinamik bir arayüz sunar.
springfox-swagger2 ve springfox-swagger-ui bağımlılıkları kullanılır.

- config isimli bir paket oluşturuldu. Bu klasör içerisinde SwaggerConfig isimli sınıf oluşturuldu.
- @Configuration anotasyonu, o sınıfın bir yapılandırma sınıfı olduğunu belirler.
- @EnableSwagger2 anotasyonu, ayarların swagger için kullanılacağını belirtir ve aktive eder.
- Yaplandırma ayarları bittikten sonra tarayıda http://localhost:8080/swagger-ui.html#/ adresi ziyaret edilir.
- 

![alt text](https://github.com/acbr5/spring-boot-demo/blob/main/Screenshot%20at%202021-02-18%2022-05-26.png)

# spring-boot-demo
