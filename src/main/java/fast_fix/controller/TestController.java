package fast_fix.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Test controller")
@RestController
@RequestMapping("/api")
public class TestController {

    @Operation(summary = "Получить приветственное сообщение",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешный ответ")
            })
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World!";
    }
}