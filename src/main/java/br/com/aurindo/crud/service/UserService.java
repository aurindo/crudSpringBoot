package br.com.aurindo.crud.service;

import br.com.aurindo.crud.exception.CrudEntityNotFounException;
import br.com.aurindo.crud.model.User;
import br.com.aurindo.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public void delete(Long id) {
        userRepository.delete(new User(id));
    }

    public User update(Long id, User user) throws CrudEntityNotFounException {
        Optional<User> userOptional = findById(id);

        if (userOptional.isPresent()) {
            user.setId(id);
            return userRepository.save(user);
        }

        throw new CrudEntityNotFounException(User.class, user.getResourceId());
    }
}
