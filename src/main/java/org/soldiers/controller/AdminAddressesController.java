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
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/searchByCity/{city}")
    public List<Address> getByCity(@PathVariable String city) {
        return addressRepository.findByCity(city);
    }

    @GetMapping("/{id}")
    public Address getById(@PathVariable Long id) {
        return addressRepository.findById(id).get();
    }

    @PostMapping("")
    public Object addAddress(@Valid Address address, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        try {
            addressRepository.save(address);
            return address;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
            addressRepository.save(a1);
            return a1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
        try {
            addressRepository.deleteById(id);
            return "Usunięto adres o id: " + id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Coś poszło nie tak";
    }
}
