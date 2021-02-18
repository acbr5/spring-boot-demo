package com.example.demo.controller;

import com.example.demo.domainobject.CustomerDO;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.service.ICustomerService;
import com.example.demo.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController anatasyonu, RESTfull özellikleri taşıyan bir controller oluşturmak istenildiğinde eklenilir.
@RestController

// RequestMapping anotasyonu, Controller'ın URL'ine genel bir isim koymak için kullanılır.
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    private ICustomerService customerService = new CustomerService();

    // ApiOperation anotasyonu, aşağısındaki metodun yapacağı işi belirtmeyi sağlar.
    @ApiOperation(value = "Create A New Customer")
    // Veri tabanına kayıt girmek için POST işlemi yapılacaksa PostMapping anotasyonu kullanılır.
    @PostMapping(path = "/customer", consumes = "application/json", produces = "application/json")
    //İstemci tarafına verilerin HTTP olarak döndürülmesi için Response Entity kullanılır.
    //Kullanıcı oluşturduktan sonra kullanıcıya ait her şeyin gelmesi için: ResponseEntity<CustomerDO>
    //Request olarak CustomerDO tipinde customerDO isimlii bir obje json olarak gönderilir. @RequestBody CustomerDO customerDO
    ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDO customerDO){
        CustomerDO createdCustomer = customerService.createCustomer(customerDO);
        return new ResponseEntity(createdCustomer, HttpStatus.CREATED); // 201 (başarılı bir şekilde eklendi) cevabı döndürüldü
    }

/*
    @ApiOperation(value = "Update Customer")
    // HTTP protokolü için güncelleme isteği (DO kullanarak)
    @PutMapping(path = "/customer", consumes = "application/json", produces = "application/json")
    ResponseEntity<CustomerDO> updateCustomerWithDO(@RequestBody CustomerDO customerDO){
        CustomerDO updatedCustomerWithDO = customerService.updateCustomerWithDO(customerDO);
        return new ResponseEntity(updatedCustomerWithDO, HttpStatus.CREATED);
    }
*/

    @ApiOperation(value = "Updates An Existing Customer")
    // HTTP protokolü için güncelleme isteği (DTO kullanarak)
    @PutMapping(path = "/customer", consumes = "application/json", produces = "application/json")
    ResponseEntity<CustomerDTO> updateCustomerWithDTO(@RequestBody CustomerDO customerDO){
        CustomerDTO updatedCustomerWithDTO = customerService.updateCustomerWithDTO(customerDO);
        return new ResponseEntity<>(updatedCustomerWithDTO, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get Customer by Customer ID")
    // Belirli bir çalışan bilgisini getirme (id'ye göre)
    // @PathVariable, URL tarafında bir değişken belirtilmek istendiğinde kullanılır
    @GetMapping(path = "/customers-by-id/{customerID}")
    ResponseEntity<CustomerDO> getCustomer(@PathVariable(value = "customerID") Long customerID){
        CustomerDO customer = customerService.getCustomer(customerID);
        return new ResponseEntity<>(customer, HttpStatus.OK); // 200 (işlem başarılı) cevabı döndürüldü.
    }

    @ApiOperation(value = "Get Customer by Customer Username")
    // Belirli bir çalışan bilgisini getirme (username'e göre)
    @GetMapping(path = "/customers-by-username/{customerUsername}")
    ResponseEntity<CustomerDO> getCustomer(@PathVariable(value = "customerUsername") String customerUsername){
        CustomerDO customer = customerService.getCustomer(customerUsername);
        return new ResponseEntity<>(customer, HttpStatus.OK); // 200 (işlem başarılı) cevabı döndürüldü.
    }

    @ApiOperation(value = "Get All Customers")
    // Tüm çalışanların bilgilerini getirme
    @GetMapping(path = "/customers")
    ResponseEntity<List<CustomerDO>> getAllCustomers(){
        List<CustomerDO> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    @ApiOperation(value = "Delete Customer by Customer ID")
    // Belirli bir çalışanın bilgilerini silme
    @DeleteMapping(path = "/customers/{customerID}")
    ResponseEntity<String> deleteCustomer(@PathVariable(value = "customerID") Long customerID){
        customerService.deleteCustomer(customerID);
        return new ResponseEntity<>("Customer with id: "+customerID + " is DELETED.", HttpStatus.OK);
    }
}