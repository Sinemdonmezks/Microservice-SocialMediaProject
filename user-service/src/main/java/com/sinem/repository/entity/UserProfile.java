package com.sinem.repository.entity;

import com.sinem.repository.enums.Status;
import jdk.jshell.Snippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Type;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="tbluserprofile")
@Entity


public class UserProfile implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long authid;
    String username;
    String name;
    String email;
    String phone;
    String photo;
    String address;
    String about;
    Long created;
    Long updated;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    Status status=Status.PENDING;
}
