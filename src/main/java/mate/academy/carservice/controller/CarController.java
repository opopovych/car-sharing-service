package mate.academy.carservice.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.carservice.dto.car.CarDto;
import mate.academy.carservice.dto.car.CreateCarDto;
import mate.academy.carservice.dto.car.UpdateCarInfoRequestDto;
import mate.academy.carservice.model.Car;
import mate.academy.carservice.service.CarService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public CarDto createCar(@RequestBody @Valid CreateCarDto createCarDto) {
        return carService.createCar(createCarDto);
    }

    @GetMapping
    public List<CarDto> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    Car getCarById(@PathVariable Long id) {
        return carService.getCarById(id);
    }

    @PutMapping("/{id}")
    CarDto updateCarInfo(@PathVariable Long id, @RequestBody UpdateCarInfoRequestDto requestDto) {
        return carService.updateCarInfo(id, requestDto);
    }

    @DeleteMapping("/{id}")
    void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}
