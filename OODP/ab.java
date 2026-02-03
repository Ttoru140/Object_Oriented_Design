// ===================== PRODUCT INTERFACE =====================
interface Car {
    void showSpecs();
}

// ===================== CONCRETE PRODUCTS =====================
class SmallCar implements Car {
    String engine, color, fuel;

    public SmallCar(String engine, String color, String fuel) {
        this.engine = engine;
        this.color = color;
        this.fuel = fuel;
    }

    public void showSpecs() {
        System.out.println("Small Car -> Engine: " + engine +
                ", Color: " + color + ", Fuel: " + fuel);
    }
}

class SedanCar implements Car {
    String engine, color, fuel;

    public SedanCar(String engine, String color, String fuel) {
        this.engine = engine;
        this.color = color;
        this.fuel = fuel;
    }

    public void showSpecs() {
        System.out.println("Sedan Car -> Engine: " + engine +
                ", Color: " + color + ", Fuel: " + fuel);
    }
}

class LuxuryCar implements Car {
    String engine, color, fuel;

    public LuxuryCar(String engine, String color, String fuel) {
        this.engine = engine;
        this.color = color;
        this.fuel = fuel;
    }

    public void showSpecs() {
        System.out.println("Luxury Car -> Engine: " + engine +
                ", Color: " + color + ", Fuel: " + fuel);
    }
}

// ===================== ABSTRACT FACTORY =====================
interface CarFactory {
    Car createSmallCar();
    Car createSedanCar();
    Car createLuxuryCar();
}

// ===================== USA FACTORY =====================
class USACarFactory implements CarFactory {

    public Car createSmallCar() {
        return new SmallCar("1.0L", "Red", "Petrol");
    }

    public Car createSedanCar() {
        return new SedanCar("2.0L", "Blue", "Petrol");
    }

    public Car createLuxuryCar() {
        return new LuxuryCar("3.5L", "Black", "Petrol");
    }
}

// ===================== ASIA FACTORY =====================
class AsiaCarFactory implements CarFactory {

    public Car createSmallCar() {
        return new SmallCar("0.8L", "White", "Electric");
    }

    public Car createSedanCar() {
        return new SedanCar("1.5L", "Silver", "Hybrid");
    }

    public Car createLuxuryCar() {
        return new LuxuryCar("2.5L", "Yellow", "Hybrid");
    }
}

// ===================== CLIENT (MAIN CLASS) =====================
public class AbstractFactoryAllInOne {
    public static void main(String[] args) {

        // USA Factory
        CarFactory usaFactory = new USACarFactory();
        usaFactory.createSmallCar().showSpecs();
        usaFactory.createSedanCar().showSpecs();
        usaFactory.createLuxuryCar().showSpecs();

        System.out.println("--------------------");

        // Asia Factory
        CarFactory asiaFactory = new AsiaCarFactory();
        asiaFactory.createSmallCar().showSpecs();
        asiaFactory.createSedanCar().showSpecs();
        asiaFactory.createLuxuryCar().showSpecs();
    }
}
