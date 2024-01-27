import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Data
public class Customer {
    private String FIO;
    private LocalDate birsthday;
    private String phone;
    enum Genders {MALE, FEMALE};
    private Genders genders;

    public Customer(String FIO, LocalDate birsthday, String phone, Genders genders) {
        this.FIO = FIO;
        this.birsthday = birsthday;
        this.phone = phone;
        this.genders = genders;
    }


}
