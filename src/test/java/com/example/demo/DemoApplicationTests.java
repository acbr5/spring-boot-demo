package com.example.demo;

import com.example.demo.domainobject.AddressDO;
import com.example.demo.domainobject.CustomerDO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

// Birim testler, metotların beklendiği şekilde çalışıp çalışmadığını test etmek için kullanılır.
@RunWith(SpringRunner.class) // junit bağımlılığının içindeki test metotlarını kullanabilmeyi sağlar.
@SpringBootTest
@AutoConfigureMockMvc // MockMvc kütüphanesini kullanarak arka planda test için otomatik yapılandırmanın yapılması için kullanılır.
class DemoApplicationTests {
	@Autowired // MockMvc özelliği inject edildi
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext context;


	@Test
	void contextLoads() {
	}

	// Bu metot JSON verisini String tipine dönüştürmek için kullanıldı. İsmini değiştirebiliriz.
	public static String asJsonString(final Object object){
		try{
			return new ObjectMapper().writeValueAsString(object);
		}catch (Exception e){
			throw new RuntimeException();
		}
	}

	// GET isteğinin sonucunda JSON verisinin geldiğini doğrulamasını, sonra bu çıktıyı ekrana yazmasını sonra da "HTTP durumu 200 (OK) mu?" diye kontrol etmesini istedik.
	@Test // Testler yazılırken Test anotasyanu yazılır ve metot eklenir
	public void testGetAllCustomers() throws Exception{ // bütün müşterileri çeken metodu test ediyoruz
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers").accept(MediaType.APPLICATION_JSON)) // get sonucunda json verisi döndü mü?
				.andDo(MockMvcResultHandlers.print()) // sonucu yaz
				.andExpect(MockMvcResultMatchers.status().isOk()) // HTTP status kodu 200 döndü mü?
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].id").exists()) // dönen sonuçlardaki bütün kayıtların id bilgisi var mı?
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].username").exists()) // bütün kayıtları kontrol etmek için "$[*].id" şeklinde yapabiliriz.
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].name").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[*].surname").exists());
	}

	@Test
	public void testGetCustomerByID() throws Exception{
		String customerID = "1";
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers-by-id/{customerID}", customerID).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()) // sonucu ekrana yaz
				.andExpect(MockMvcResultMatchers.status().isOk()) // HTTP status kodu 200 döndü mü?
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()) // bu sorgu sonucunda sadece bir tane kayıt döndüğü için dizi olmayacak. Tek kayda ulaşmak içiin $ yeterli olacaktır.
				.andExpect(MockMvcResultMatchers.jsonPath("$.username").exists()) // bütün kayıtları kontrol etmek için "$[*].id" şeklinde yapabiliriz.
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.surname").exists());
	}

	@Test
	public void testGetCustomerByUsername() throws Exception{
		String customerUsername = "eslemm";
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customers-by-username/{customerUsername}", customerUsername).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print()) // sonucu ekrana yaz
				.andExpect(MockMvcResultMatchers.status().isOk()) // HTTP status kodu 200 döndü mü?
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()) // bu sorgu sonucunda sadece bir tane kayıt döndüğü için dizi olmayacak. Tek kayda ulaşmak içiin $ yeterli olacaktır.
				.andExpect(MockMvcResultMatchers.jsonPath("$.username").exists()) // bütün kayıtları kontrol etmek için "$[*].id" şeklinde yapabiliriz.
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.surname").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.address.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.address.city").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.address.street").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.address.zipCode").exists());
	}

	@Test
	public void testCreateCustomer() throws Exception{
		CustomerDO newCustomer = new CustomerDO();
		AddressDO address = new AddressDO();

		address.setStreet("Merkez");
		address.setCity("Sivas");
		address.setZipCode("58060");

		newCustomer.setUsername("eslemmm");
		newCustomer.setName("Eslem");
		newCustomer.setSurname("ÇİL");
		newCustomer.setAddress(address);

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customer")
											  .contentType(MediaType.APPLICATION_JSON)
										      .content(asJsonString(newCustomer))) // json türünde veri gönderildi mi?
				.andDo(MockMvcResultHandlers.print()) // sonucu ekrana yaz
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()) // bu sorgu sonucunda sadece bir tane kayıt döndüğü için dizi olmayacak. Tek kayda ulaşmak içiin $ yeterli olacaktır.
				.andExpect(MockMvcResultMatchers.jsonPath("$.username").exists()) // bütün kayıtları kontrol etmek için "$[*].id" şeklinde yapabiliriz.
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.surname").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.address.id").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.address.city").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.address.street").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.address.zipCode").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("eslemmm"));
	}

	@Test
	public void testUpdateCustomer() throws Exception{
		CustomerDO newCustomer = new CustomerDO();
		AddressDO address = new AddressDO();

		address.setStreet("Merkez");
		address.setCity("Sivas");
		address.setZipCode("772288");

		newCustomer.setId(1L);
		newCustomer.setName("Ayşenur");
		newCustomer.setSurname("BURAK");
		newCustomer.setAddress(address);

		mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customer")
											  .contentType(MediaType.APPLICATION_JSON)
											  .content(asJsonString(newCustomer)))
				.andDo(MockMvcResultHandlers.print()) // sonucu ekrana yaz
				.andExpect(MockMvcResultMatchers.status().isCreated())
			    .andExpect(MockMvcResultMatchers.jsonPath("$.ad").exists()) // CustomerDTO sınıfında JsonProperty ile tüm alanların başka bir ad ile görünmesini istediğim için o adları kullanıyorum.
				.andExpect(MockMvcResultMatchers.jsonPath("$.soyad").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.address.city").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.address.street").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.address.zipCode").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.['kullanıcı adı']").value("aysenurb")); // DTO objesi ile kullanıcının görebileceği bilgiyi kısıtladığımız için id'ye erişemeyiz
	}

	@Test
	public void testDeleteCustomer() throws Exception{
		String customerID = "15";
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customers/{customerID}", customerID).accept(MediaType.APPLICATION_JSON))
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}