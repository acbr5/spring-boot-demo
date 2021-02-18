package com.example.demo.service;

import com.example.demo.domainobject.AddressDO;
import com.example.demo.domainobject.CustomerDO;
import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.repository.ICustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService{

    // bağımlılığı başka bir yere nesne üretmeden kullanmak için Autowired kullanılır
    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public CustomerDO createCustomer(CustomerDO customer) {
        return customerRepository.save(customer);
    }

    @Override
    public CustomerDO updateCustomerWithDO(CustomerDO customer) {
        long customerID = customer.getId();
        // Optional sınıfında bulunan isPresent() metodu, nesne veritabanında mevcutsa true döner
        Optional<CustomerDO> currentCustomer = customerRepository.findById(customerID);
        if(currentCustomer.isPresent()){ // kayıt veritabanında mevcut mu?
            currentCustomer.get().setName(customer.getName());
            currentCustomer.get().setSurname(customer.getSurname());

            AddressDO customerAddress = currentCustomer.get().getAddress();
            if(customerAddress == null){
                customerAddress = new AddressDO();
            }

            customerAddress.setCity(customer.getAddress().getCity());
            customerAddress.setStreet(customer.getAddress().getStreet());
            customerAddress.setZipCode(customer.getAddress().getZipCode());


            CustomerDO savedCustomer = customerRepository.save(currentCustomer.get());
            customerAddress.setId(savedCustomer.getAddress().getId());
            currentCustomer.get().setAddress(customerAddress);
            return savedCustomer; // optional olduğundan get() ile CustomerDO nesnesini aldık
        }
        return null;
    }

    @Override
    public CustomerDTO updateCustomerWithDTO(CustomerDO customer) {
        long customerID = customer.getId();
        // Optional sınıfında bulunan isPresent() metodu, nesne veritabanında mevcutsa true döner
        Optional<CustomerDO> currentCustomer = customerRepository.findById(customerID);
        if(currentCustomer.isPresent()){ // kayıt veritabanında mevcut mu?
            currentCustomer.get().setName(customer.getName());
            currentCustomer.get().setSurname(customer.getSurname());

            AddressDO customerAddress = currentCustomer.get().getAddress();
            if(customerAddress == null){
                customerAddress = new AddressDO();
            }

            customerAddress.setCity(customer.getAddress().getCity());
            customerAddress.setStreet(customer.getAddress().getStreet());
            customerAddress.setZipCode(customer.getAddress().getZipCode());

            currentCustomer.get().setAddress(customerAddress);

            customerRepository.save(currentCustomer.get());

            // iki farklı türü aralarındaki özellikleri birbirini tanıyacak şekilde map ediyoruz. currentCustomer'ı CustomerDTO.class'ının türüne çeviriyoruz.
            CustomerDTO customerDTO = new ModelMapper().map(currentCustomer.get(), CustomerDTO.class);
            AddressDTO addressDTO = new ModelMapper().map(customerAddress, AddressDTO.class);
            customerDTO.setAddress(addressDTO);
            return customerDTO;
        }
        return null;
    }

    @Override
    public void deleteCustomer(Long customerID) {
        Optional<CustomerDO> currentCustomer = customerRepository.findById(customerID);
        if(currentCustomer.isPresent()){
            customerRepository.deleteById(customerID);
        }
    }

    @Override
    public CustomerDO getCustomer(Long customerID) {
        Optional<CustomerDO> currentCustomer = customerRepository.findById(customerID);
        if(currentCustomer.isPresent()){
            return currentCustomer.get();
        }
        return null;
    }

    @Override
    public CustomerDO getCustomer(String customerUsername) {
        Optional<CustomerDO> currentCustomer = customerRepository.findByUsername(customerUsername);
        if(currentCustomer.isPresent()){
            return currentCustomer.get();
        }
        return null;
    }

    @Override
    public List<CustomerDO> getAllCustomers() {
        return customerRepository.findAll();
    }
}