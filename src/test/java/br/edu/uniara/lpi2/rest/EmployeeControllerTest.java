package br.edu.uniara.lpi2.rest;

import br.edu.uniara.lpi2.rest.controller.EmployeeController;
import br.edu.uniara.lpi2.rest.model.Employee;
import br.edu.uniara.lpi2.rest.model.EmployeePagingRepository;
import br.edu.uniara.lpi2.rest.model.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class EmployeeControllerTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private EmployeePagingRepository employeePagingRepository;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOneEmployeeFound() {
        var employee = new Employee("Vitor Faccio", "Programador");
        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(employee));

        var response = employeeController.one(1L);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(response.getBody()).isEqualTo(employee);
    }

    @Test
    public void testOneEmployeeNotFound() {
        Long employeeId = 1L;
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        var response = employeeController.one(employeeId);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    public void testAllEmployees() {
        var mockEmployeeList = Arrays.asList(
                new Employee("Vitor Faccio", "Programador"),
                new Employee("Vitor Ravenna", "Desenvolvedor")
        );

        when(employeePagingRepository.findAll(PageRequest.of(0, 10)))
                .thenReturn(new PageImpl<>(mockEmployeeList, PageRequest.of(0, 10), mockEmployeeList.size()));

        var response = employeeController.all(0, 10);

        assertThat(response.getStatusCode()).isEqualTo(ResponseEntity.ok().build().getStatusCode());
    }

    @Test
    public void testInsertEmployee() {
        var employee = new Employee("Vitor Faccio", "Desenvolvedor");
        var expectedResponse = ResponseEntity.ok().body(employee);
        when(employeeRepository.save(employee)).thenReturn(employee);

        var response = employeeController.insert(employee);

        assertThat(response.getStatusCode()).isEqualTo(expectedResponse.getStatusCode());
        assertThat(response.getBody()).isEqualTo(expectedResponse.getBody());
    }

    @Test
    public void testDeleteEmployeeFound() {
        var employeeId = 1L;
        when(employeeRepository.existsById(employeeId)).thenReturn(true);

        var response = employeeController.delete(employeeId);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void testDeleteEmployeeNotFound() {
        var employeeId = 1L;
        when(employeeRepository.existsById(employeeId)).thenReturn(false);

        var response = employeeController.delete(employeeId);

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }

    @Test
    public void testUpdateEmployeeFound() {
        var employee = new Employee("Vitor Faccio", "Desenvolvedor");
        var newEmployeeData = new Employee("Vitor Ravenna", "Programador");
        when(employeeRepository.findById(1L)).thenReturn(java.util.Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(employee);

        var response = employeeController.update(1L, newEmployeeData);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
        assertThat(((Employee) response.getBody()).getName()).isEqualTo(newEmployeeData.getName());
        assertThat(((Employee) response.getBody()).getRole()).isEqualTo(newEmployeeData.getRole());
    }

    @Test
    public void testUpdateEmployeeNotFound() {
        var employeeId = 1L;
        when(employeeRepository.findById(employeeId)).thenReturn(java.util.Optional.empty());

        var response = employeeController.update(employeeId, new Employee("Vitor Faccio", "Desenvolvedor"));

        assertThat(response.getStatusCodeValue()).isEqualTo(404);
    }
}