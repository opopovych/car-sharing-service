package mate.academy.carservice.service;

import java.util.List;
import mate.academy.carservice.dto.car.CarDto;
import mate.academy.carservice.dto.car.CreateCarDto;
import mate.academy.carservice.dto.car.UpdateCarInfoRequestDto;
import mate.academy.carservice.model.Car;

public interface CarService {
    CarDto createCar(CreateCarDto carDto);

    List<CarDto> getAllCars();

    Car getCarById(Long id);

    CarDto updateCarInfo(Long id, UpdateCarInfoRequestDto requestDto);

    void deleteCar(Long id);
}
