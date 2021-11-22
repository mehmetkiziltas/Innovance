package com.innovance.controller;

import com.innovance.entity.User;
import com.innovance.entity.vm.UpdateUserVM;
import com.innovance.entity.vm.UserVM;
import com.innovance.service.UserService;
import com.innovance.shared.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/save")
    public GenericResponse save(@RequestBody User user) {
        userService.createUser(user);
        return new GenericResponse("User saved successfully");
    }

    @GetMapping("/getall")
    public ResponseEntity<List<UserVM>> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/update/{identityNumber}")
    public GenericResponse update(@Valid @RequestBody UpdateUserVM updateUserVM,
                                  @PathVariable String identityNumber) {
        final User user = userService.updateUser(identityNumber, updateUserVM);
        return new GenericResponse(user + " User updated successfully");
    }

    @DeleteMapping("/delete/{identityNumber}")
    public GenericResponse delete(@PathVariable String identityNumber) {
        userService.deleteUser(identityNumber);
        return new GenericResponse("User deleted successfully");
    }
}
