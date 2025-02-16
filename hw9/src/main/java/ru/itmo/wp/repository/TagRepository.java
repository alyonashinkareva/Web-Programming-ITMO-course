package ru.itmo.wp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itmo.wp.domain.Role;
import ru.itmo.wp.domain.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    boolean existsByName(String name);
    Tag findByName(String name);
}
