package com.example.restapimvc.post.command.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@AutoConfigureMockMvc
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Test
    @Transactional
    public void findTest() {
        Post post = postRepository.findById(1453l).get();
    }
}