package controller;


import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.PropertiesService;

@RestController
@RequestMapping("/properties")
@RequiredArgsConstructor
public class PropertiesController {
    private final PropertiesService propertiesService;

}
