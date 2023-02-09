package nttdata.bootcamp.quarkus.creditcard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Cacheable
@Table(name = "CREDITCARD")
public class CreditCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCreditCard;
    private String cardNumber;
    private int pin;
    private String expirationDate;
    private String validationCode;
    private String cutoffDate;
    private String monthlyPaymentDate;
    private double currentBalance;
    private double creditLimit;
}