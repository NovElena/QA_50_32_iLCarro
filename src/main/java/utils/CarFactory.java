package utils;

import dto.Car;
import net.datafaker.Faker;
import utils.enums.Fuel;

public class CarFactory {
    static Faker faker = new Faker();

    public static Car positiveCar(){
        Car car = Car.builder()
                .city("Rehovot")
                .manufacture(faker.vehicle().manufacturer())
                .model(faker.vehicle().model())
                .year(Integer.toString(faker.number()
                        .numberBetween(0, 2026)))
                .fuel(faker.options().option(Fuel.values()))
                .seats(faker.number().numberBetween(2, 20))
                .carClass(faker.vehicle().carType())
                .serialNumber(faker.vehicle().licensePlate())
                .pricePerDay(faker.number()
                        .randomDouble(2, 1, 999))
                .about(faker.text().text(0, 500))
                .build();
        return car;
    }
}
