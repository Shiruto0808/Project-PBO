import java.util.HashMap;
import java.util.Map;

class Customer extends User {
    Map<Saham, Integer> sahamDimiliki = new HashMap<>();
    Map<SBN, Double> sbnDimiliki = new HashMap<>();

    public Customer(String username, String password) {
        super(username, password);
    }
}
