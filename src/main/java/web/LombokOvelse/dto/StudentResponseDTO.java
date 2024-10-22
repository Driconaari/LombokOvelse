package web.LombokOvelse.dto;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalTime;

public record StudentResponseDTO(Long id, String name, LocalDate bornDate, LocalTime bornTime) {
}
