package ru.skypro.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class EmployeeStoragelsFullException extends RuntimeException {
    public  EmployeeStoragelsFullException(){
        super("Хранилище сотрудников заполнено");
    }
}
