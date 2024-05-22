package mate.academy.carservice.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mate.academy.carservice.dto.car.CarDto;
import mate.academy.carservice.dto.car.CreateCarDto;
import mate.academy.carservice.dto.car.UpdateCarInfoRequestDto;
import mate.academy.carservice.mapper.CarMapper;
import mate.academy.carservice.model.Car;
import mate.academy.carservice.repo.CarRepository;
import mate.academy.carservice.service.CarService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public CarDto createCar(CreateCarDto carDto) {
        Car car = carMapper.createCarDtoToCar(carDto);
        Car savedCar = carRepository.save(car);
        return carMapper.entityToCarDto(savedCar);
    }

    @Override
    public List<CarDto> getAllCars() {
        List<Car> cars = carRepository.findAll();
        List<CarDto> carDtos = cars.stream()
                .map(carMapper::entityToCarDto)
                .toList();
        return carDtos;
    }

    @Override
    public Car getCarById(Long id) {
        return findById(id);
    }

    @Override
    public CarDto updateCarInfo(Long id, UpdateCarInfoRequestDto requestDto) {
        Car car = findById(id);
        Car updatedCar = carMapper.updateCarInfo(car,requestDto);
        Car savedCar = carRepository.save(updatedCar);
        return carMapper.entityToCarDto(savedCar);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    private Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Cannot find car"));
    }
}
