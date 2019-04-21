package br.com.aurindo.crud.controller;

import br.com.aurindo.crud.exception.CrudEntityNotFounException;
import br.com.aurindo.crud.model.User;
import br.com.aurindo.crud.model.resource.UserResourceMapper;
import br.com.aurindo.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@ExposesResourceFor(User.class)
@EnableHypermediaSupport(type = HypermediaType.HAL)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserResourceMapper userResourceMapper;

    @PostMapping
    public ResponseEntity<User> save(
            @RequestBody User user) throws CrudEntityNotFounException {
        User createdUser = userService.save(user);

        User resource = userResourceMapper.toResource(createdUser);
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(
            @PathVariable(value="id", required = true) Long id,
            @RequestBody User user) {
        try {
            user = userService.update(id, user);

            User resource = userResourceMapper.toResource(user);
            return new ResponseEntity<>(resource, HttpStatus.OK);
        } catch (CrudEntityNotFounException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(
            @PathVariable(value="id", required = true) Long id) throws CrudEntityNotFounException {
        Optional<User> userOptional = userService.findById(id);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userResourceMapper.toResource(userOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() throws CrudEntityNotFounException {
        List<User> resource = userResourceMapper.toResourceCollection(userService.findAll());
        return ResponseEntity.ok(resource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(
            @PathVariable(value="id", required = true) Long id) throws CrudEntityNotFounException {

        userService.delete(id);

        return ResponseEntity.ok().build();
    }

}
