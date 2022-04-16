package com.altinsoy.customerarchive.repository;

import com.altinsoy.customerarchive.model.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, String> {
    List<Attachment> findAttachmentByCustomerId(Long id);
}
