package com.example.demo.service;

import com.example.demo.domainobject.CustomerDO;
import com.example.demo.dto.CustomerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICustomerService {

    // bir çalışan oluşturmak için
    CustomerDO createCustomer(CustomerDO customer);

    // belirli bir çalışanın bilgilerini güncellemek için
    CustomerDTO updateCustomerWithDTO(CustomerDO customer);

    CustomerDO updateCustomerWithDO(CustomerDO customer);

    // belirli bir çalışanı silmek için
    void deleteCustomer(Long customerID);

    // belirli bir çalışanın bilgilerini çekmek için
    CustomerDO getCustomer(Long customerID);

    // belirli bir çalışanın bilgilerini çekmek için
    CustomerDO getCustomer(String customerUsername);

    // tüm çalışan bilgilerini getirmek için
    List<CustomerDO> getAllCustomers();
}
