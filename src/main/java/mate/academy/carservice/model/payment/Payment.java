package mate.academy.carservice.model.payment;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.net.URL;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import mate.academy.carservice.model.Rental;

@Entity
@Getter
@Setter
@Table(name = "payments")
@Accessors(chain = true)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "payment_status", columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "payment_type", columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    private Type type;
    @OneToOne
    @JoinColumn(name = "rental_id", nullable = false)
    private Rental rental;
    private URL sessionUrl;
    private String sessionId;
    @Column(name = "total_price")
    private BigDecimal amountToPay;
}
