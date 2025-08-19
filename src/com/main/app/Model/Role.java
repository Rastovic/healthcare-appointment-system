package com.main.app.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Table(name = "person_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")   // lowercase, matches DB
    private String roleName;

    @Column(name = "security_level")
    private BigInteger securityLevel;

    @Column(name = "permission_id")
    private Long permissionId;
}
