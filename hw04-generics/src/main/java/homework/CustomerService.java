package homework;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    //todo: 3. надо реализовать методы этого класса
    //важно подобрать подходящую Map-у, посмотрите на редко используемые методы, они тут полезны

    private final TreeMap<Customer, String> customerService = new TreeMap<>(Comparator.comparingLong(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
       //Возможно, чтобы реализовать этот метод, потребуется посмотреть как Map.Entry сделан в jdk
       return customerClone(customerService.firstEntry());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        var entry = customerService.higherEntry(customer);
        if (entry != null) {
            return customerClone(entry);
        }
        else {
            return customerClone(customerService.higherEntry(new Customer(customer.getId(), customer.getName(), customer.getScores() - 1)));
        }
    }

    public void add(Customer customer, String data) {
       customerService.put(customer, data);
    }

    private Map.Entry<Customer, String> customerClone(Map.Entry<Customer, String> origin) {
        try {
            return new AbstractMap.SimpleEntry<Customer, String>(origin.getKey().clone(), origin.getValue());
        } catch (NullPointerException | CloneNotSupportedException e) {
            return null;
        }
    }
}
