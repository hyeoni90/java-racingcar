package racing.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

public class RacingCars {
    private static final int INITIAL_LOCATION = 0;

    private final List<Car> cars;

    public RacingCars(final List<Car> cars) {
        this.cars = cars;
    }

    public static RacingCars of(final List<String> carNames) {
        return carNames.stream()
                .map(Car::of)
                .collect(collectingAndThen(toList(), RacingCars::new));
    }

    public List<Car> getCars() {
        return cars;
    }

    public int getMaxLocation() {
        return this.cars.stream()
                .max(Comparator.comparingInt(Car::getLocation))
                .map(Car::getLocation)
                .orElse(INITIAL_LOCATION);
    }

    public List<String> getWinners() {
        final int maxLocation = getMaxLocation();

        return this.cars.stream()
                .filter(car -> car.isCorrectMaxLocation(maxLocation))
                .map(Car::getName)
                .collect(toList());
    }

    public List<Car> raceOfCars(final List<Car> cars) {
        for (final Car car : cars) {
            car.move();
        }
        return cars;
    }
}
