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
  private Long id; // 카카오 ID

  private String email;

  private String password;

  private String name;

  @NotNull
  private String nickname;

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