package com.yunsu.fitmotion.user.infrastructure;

import com.yunsu.fitmotion.springjwt.common.Role;
import com.yunsu.fitmotion.user.domain.User;
import com.yunsu.fitmotion.user.service.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public Optional<User> findById(Long id) {
        return jpaUserRepository.findById(id).map(UserEntity::toModel);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpaUserRepository.findByUsername(username).map(UserEntity::toModel);
    }

    @Override
    public List<User> findByRole(Role role) {
        return jpaUserRepository.findByRole(role)
                .stream()
                .map(UserEntity::toModel)
                .toList();
    }
}
