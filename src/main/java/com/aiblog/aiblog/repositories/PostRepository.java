package com.aiblog.aiblog.repositories;

import com.aiblog.aiblog.models.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostModel, UUID> {
}
