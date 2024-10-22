package web.LombokOvelse.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private LocalDate bornDate;

    private LocalTime bornTime;

    public Student(String name, String password, LocalDate bornDate, LocalTime bornTime) {
        this.name = name;
        this.password = password;
        this.bornDate = bornDate;
        this.bornTime = bornTime;
    }


}

