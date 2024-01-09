package backend.task.taskbackend.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CustomerController {
    @PostMapping
    ResponseEntity<Boolean> save(){
        return null;
    }
}
