package org.soldiers.controller.rest;

import org.soldiers.model.Address;
import org.soldiers.model.Soldier;
import org.soldiers.repository.AddressRepository;
import org.soldiers.repository.SoldierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin/addresses")
public class AdminAddressesController {
    private AddressRepository addressRepository;
    private SoldierRepository soldierRepository;

    @Autowired
    public AdminAddressesController(AddressRepository addressRepository, SoldierRepository soldierRepository) {
        this.addressRepository = addressRepository;
        this.soldierRepository = soldierRepository;
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
    public Object addAddress(@Valid Address address, BindingResult bindingResult, HttpServletResponse httpServletResponse) {
        if (bindingResult.hasErrors()) {
            return bindingResult.getAllErrors();
        }
        try {
            httpServletResponse.setStatus(201);
            return addressRepository.save(address);
        } catch (Exception e) {
            httpServletResponse.setStatus(409);
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/{id}")
    public Object updateAddress(@PathVariable Long id, @Valid Address address, BindingResult bindingResult, HttpServletResponse httpServletResponse) {
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
            httpServletResponse.setStatus(409);
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id, HttpServletResponse httpServletResponse) {
        try {
            for (Soldier s : soldierRepository.findByAddress(addressRepository.findById(id).get())) {
                s.setAddress(null);
            }
            addressRepository.deleteById(id);
            return "Usunięto adres o id: " + id;
        } catch (Exception e) {
            httpServletResponse.setStatus(409);
            e.printStackTrace();
            return "Coś poszło nie tak";
        }
    }
}
