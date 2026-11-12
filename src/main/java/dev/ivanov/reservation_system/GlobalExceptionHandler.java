package dev.ivanov.reservation_system;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice               //вешаем над классом @ControllerAdvice что бы спринг понял что класс является обработчиком ошибок
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

                                                            //(Exception.class)-означает что обрабатывает все исключения
    @ExceptionHandler(Exception.class)                     //@ExceptionHandler что бы спринг понял что метод обрабатывает ошибки
    public ResponseEntity<String> handleGenericException(    // <Void> означает что метод будет возвращать ответ без тела
            Exception e
    ){
        log.error("Handle exception", e);

        return ResponseEntity
                .status(500)
                .body(e.getMessage());
    }
}
