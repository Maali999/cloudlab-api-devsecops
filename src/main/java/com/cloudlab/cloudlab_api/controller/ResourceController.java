package com.cloudlab.cloudlab_api.controller;

import com.cloudlab.cloudlab_api.entity.Resource;
import com.cloudlab.cloudlab_api.service.ResourceService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    // CREATE - Add a new resource
    @PostMapping
    public Resource createResource(@RequestBody Resource resource) {
        return resourceService.saveResource(resource);
    }

    // READ - Get all resources
    @GetMapping
    public List<Resource> getAllResources() {
        return resourceService.getAllResources();
    }

    // READ - Get resource by ID
    @GetMapping("/{id}")
    public ResponseEntity<Resource> getResourceById(@PathVariable Long id) {
        return resourceService.getResourceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE - Update an existing resource
    @PutMapping("/{id}")
    public ResponseEntity<Resource> updateResource(
            @PathVariable Long id,
            @RequestBody Resource resource) {

        return resourceService.getResourceById(id)
                .map(existingResource -> {
                    existingResource.setName(resource.getName());
                    existingResource.setDescription(resource.getDescription());
                    existingResource.setQuantity(resource.getQuantity());

                    Resource updatedResource =
                            resourceService.saveResource(existingResource);

                    return ResponseEntity.ok(updatedResource);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE - Delete a resource
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {

        if (!resourceService.getResourceById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        resourceService.deleteResource(id);

        return ResponseEntity.noContent().build();
    }
}