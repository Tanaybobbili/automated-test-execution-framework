package com.infosys.automated_test_execution_framework.controller.api;

import com.infosys.automated_test_execution_framework.entity.MockResourceEntity;
import com.infosys.automated_test_execution_framework.repository.MockResourceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mock/resources")
@RequiredArgsConstructor
public class MockResourceController {

    private final MockResourceRepository repo;

    @PostMapping
    public MockResourceEntity create(@RequestBody MockResourceEntity r) {
        return repo.save(r);
    }

    @GetMapping
    public List<MockResourceEntity> all() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public MockResourceEntity one(@PathVariable Long id) {
        return repo.findById(id).orElseThrow();
    }

    @PutMapping("/{id}")
    public MockResourceEntity update(
            @PathVariable Long id,
            @RequestBody MockResourceEntity updated
    ) {
        MockResourceEntity existing = repo.findById(id).orElseThrow();
        existing.setName(updated.getName());
        existing.setDescription(updated.getDescription());
        return repo.save(existing);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
