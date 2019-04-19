package org.soldiers.controller;

import org.soldiers.model.Address;
import org.soldiers.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/addresses")
public class AdminAddressesController {
    private AddressRepository addressRepository;

    @Autowired
    public AdminAddressesController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping("/city/{city}")
    public List<Address> getAddressByCity(@PathVariable String city) {
        return addressRepository.findByCity(city);
    }

    @GetMapping("/{id}")
    public Address getAddressById(@PathVariable Long id) {
        return addressRepository.findById(id).get();
    }

    @PostMapping("")
    public Object addAddress(@Valid Address address, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        try {
            return addressRepository.save(address);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/{id}")
    public Object updateAddress(@PathVariable Long id, @Valid Address address, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        try {
            Address a1 = addressRepository.findById(id).get();
            a1.setStreet(address.getStreet());
            a1.setCity(address.getCity());
            a1.setPostalCode(address.getPostalCode());
            return addressRepository.save(a1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        try {
            addressRepository.deleteById(id);
            return "Usunięto adres o id: " + id;
        } catch (Exception e) {
            e.printStackTrace();
            return "Coś poszło nie tak";
        }
    }
}
