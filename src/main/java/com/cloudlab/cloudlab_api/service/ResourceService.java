package com.cloudlab.cloudlab_api.service;

import com.cloudlab.cloudlab_api.entity.Resource;
import com.cloudlab.cloudlab_api.repository.ResourceRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;

    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    // Create or update a resource
    public Resource saveResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    // Get all resources
    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    // Get resource by ID
    public Optional<Resource> getResourceById(Long id) {
        return resourceRepository.findById(id);
    }

    // Delete resource
    public void deleteResource(Long id) {
        resourceRepository.deleteById(id);
    }
}