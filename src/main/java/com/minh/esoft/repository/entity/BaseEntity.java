package com.minh.esoft.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.time.Instant;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "deleted_at")
    private Instant deletedAt;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

//    @PrePersist
//    public void prePersist() {
//        createdBy = this.getValue(createdBy);
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        updatedBy = this.getValue(updatedBy);
//    }
//
//    private String getValue(String var) {
//        if (var == null) {
//            // for internal call without jwt (no user)
////            try {
////                Long id = UserUtils.id();
////                if (id == null || id == 0L) {
////                    var = Constants.INTERNAL_NAME;
////                } else {
////                    var = id.toString();
////                }
////            } catch (Exception e) {
////                var = Constants.INTERNAL_NAME;
////            }
//        }
//       return SecurityContextHolder.getContext().getAuthentication().getName();
////        return "MINH_KUDO";
//    }
}