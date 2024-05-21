package br.edu.uniara.lpi2.rest.controller;

import br.edu.uniara.lpi2.rest.model.Employee;
import br.edu.uniara.lpi2.rest.model.EmployeePagingRepository;
import br.edu.uniara.lpi2.rest.model.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository repository;

    @Autowired
    EmployeePagingRepository employeePagingRepository;

    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        return repository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<?> all(@RequestParam int page, @RequestParam int size) {
        if (page < 0) {
            return ResponseEntity.badRequest().body("page deve ser > 0");
        }
        if (size < 1 || size > 500) {
            return ResponseEntity.badRequest().body("size deve ser entre 1 e 500");
        }

        var pageable = PageRequest.of(page, size);
        var listEmployee = employeePagingRepository.findAll(pageable);

        return ResponseEntity.ok(listEmployee.stream().toList());
    }

    @PostMapping
    public ResponseEntity<Employee> insert(@RequestBody Employee employee) {
        var employeeSalvo = repository.save(employee);
        return ResponseEntity.ok(employeeSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        var employeeFound = repository.existsById(id);
        if (!employeeFound) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.ok(id + " foi removido");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Employee newEmployeeData) {
        return repository.findById(id).map(employee -> {
            employee.setName(newEmployeeData.getName());
            employee.setRole(newEmployeeData.getRole());
            var updatedEmployee = repository.save(employee);
            return ResponseEntity.ok(updatedEmployee);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}