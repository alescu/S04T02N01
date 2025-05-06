package cat.itacademy.s04.t02.n01.exception;

import cat.itacademy.s04.t02.n01.model.CustomError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = "El mètode HTTP " + ex.getMethod() + " no està permès per a aquesta URL. Els mètodes permesos són: " + ex.getSupportedHttpMethods();
        CustomError errorResposta = new CustomError(HttpStatus.METHOD_NOT_ALLOWED.value(), message);
        return new ResponseEntity<>(errorResposta, HttpStatus.METHOD_NOT_ALLOWED);
    }

}
