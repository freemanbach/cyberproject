public class Program {
    public static void Main(String[] args) {
        Cars cars = new Cars();
        String ans = "";
        ans = cars.gto() + cars.corvette() + cars.mustang() + cars.challenger() + cars.charger() + cars.camaro() + cars.firebird();
    }
}
