package web.LombokOvelse.service;

import org.springframework.stereotype.Service;
import web.LombokOvelse.dto.StudentRequestDTO;
import web.LombokOvelse.dto.StudentResponseDTO;
import web.LombokOvelse.model.Student;
import web.LombokOvelse.repository.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

    private final StudentRepository studentRepository;

    // Constructor injection
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentResponseDTO> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentResponseDTO> studentResponseDTOs = new ArrayList<>();

        // Using a for-loop to convert each Student to a StudentResponseDTO
        for (Student student : students) {
            StudentResponseDTO studentResponseDTO = new StudentResponseDTO(student.getId(), student.getName(), student.getBornDate(), student.getBornTime());
            studentResponseDTOs.add(studentResponseDTO);
        }

        return studentResponseDTOs;
    }

    public StudentResponseDTO getStudentById(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        // Throw RuntimeException if student is not found
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Student not found with id " + id);
        }

        Student student = optionalStudent.get();

        return new StudentResponseDTO(student.getId(), student.getName(), student.getBornDate(), student.getBornTime());

    }

    public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
        Student newStudent = new Student(studentRequestDTO.name(), studentRequestDTO.password(), studentRequestDTO.bornDate(), studentRequestDTO.bornTime());
        Student studentResponse = studentRepository.save(newStudent);

        return new StudentResponseDTO(studentResponse.getId(), studentResponse.getName(), studentResponse.getBornDate(), studentResponse.getBornTime());
    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        // Throw RuntimeException if student is not found
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Student not found with id " + id);
        }

        Student student = optionalStudent.get();

        student.setName(studentRequestDTO.name());
        student.setPassword(studentRequestDTO.password());
        student.setBornDate(studentRequestDTO.bornDate());
        student.setBornTime(studentRequestDTO.bornTime());

        Student studentResponse = studentRepository.save(student);
        return new StudentResponseDTO(studentResponse.getId(), studentResponse.getName(), studentResponse.getBornDate(), studentResponse.getBornTime());
    }

    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            // Throw RuntimeException if student is not found
            throw new RuntimeException("Student not found with id " + id);
        }
        studentRepository.deleteById(id);
    }
}