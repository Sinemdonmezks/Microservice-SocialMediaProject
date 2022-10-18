package com.sinem.repository.entity;

import com.sinem.repository.enums.Roles;
import com.sinem.repository.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name= "tblauth")
@Entity
public class Auth {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 16,nullable = false,unique = true)
    private String namesurname;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Roles role=Roles.USER;
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status=Status.PENDING;

    private String activatedCode;
}
