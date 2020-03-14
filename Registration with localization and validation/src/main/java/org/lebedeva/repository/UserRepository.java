package org.lebedeva.repository;

import org.lebedeva.model.User;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository implements org.lebedeva.repository.Repository<User, Integer> {

    private static final List<User> users = new CopyOnWriteArrayList<>();
    private static final AtomicInteger id = new AtomicInteger(0);

    public Integer setUserId() {
        return id.incrementAndGet();
    }

    @Override
    public Integer save(User data) {
        data.setId(setUserId());
        users.add(data);
        return data.getId();
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return users.stream()
                .filter(user -> user.getId().compareTo(id) == 0)
                .findFirst();
    }

    @Override
    public boolean userExists(String email) {
        return users.stream()
                .anyMatch(u -> email.equals(u.getEmail()));
    }
}
