package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.repository.TagRepository;

@Service
public class TagService {
    private final TagRepository tagRepository;
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag getAndAdd(String tag) {
        Tag tag_exists = tagRepository.findByName(tag);
        if (tag_exists == null) {
            tag_exists = tagRepository.save(new Tag(tag));
        }
        return tag_exists;
    }
}
