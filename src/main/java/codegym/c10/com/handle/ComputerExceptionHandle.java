package codegym.c10.com.handle;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


@ControllerAdvice
public class ComputerExceptionHandle {
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(Exception ex, HttpServletRequest request) {
        System.out.println(ex.getMessage());
        ModelAndView modelAndView;
        modelAndView = new ModelAndView("error");
        return modelAndView;
    }
}