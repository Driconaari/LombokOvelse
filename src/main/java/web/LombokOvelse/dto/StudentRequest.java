package web.LombokOvelse.dto;


import java.time.LocalDate;
import java.time.LocalTime;

public record StudentRequest(String name, String password, LocalDate birthDate, LocalTime bornTime) {
}
