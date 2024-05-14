package com.flavowcase.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String email;

  @NotNull
  private String password;

  @NotNull
  private String name;

  @NotNull
  private String phone;
}
//
//public class Member {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Long id;
//
//  @Column(nullable = false, unique = true)
//  private String email;
//
//  @Column(nullable = false)
//  private String password;
//
//  @Column(nullable = false)
//  private String name;
//
//  @Column(nullable = false)
//  private String phone;
//}