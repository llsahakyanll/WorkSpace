package com.company.workspace.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.*;

@Entity
@Table(name = "user_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "This input cannot be empty.")
    @Size(min = 3, max = 20)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotBlank(message = "This input cannot be empty.")
    @Size(min = 3, max = 20)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @EqualsAndHashCode.Exclude
    @Column(name = "registration_date", nullable = false)
    private String registrationDate;

    @NotBlank
    @EqualsAndHashCode.Exclude
    @Column(name = "birth_date", nullable = false)
    private String birthDate;

    @Size(max = 255, message = "CV PDF must be less than or equal to 255 characters")
    @Column(name = "cv_pdf", nullable = false)
    private String cvPdf;

    @NotBlank
    @Pattern(regexp = "\\+\\d{3}-\\d{2}-\\d{3}-\\d{3}", message = "Please use pattern +XXX-XX-XXX-XXX")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @NotBlank
    @Column(name = "gender", nullable = false)
    private String gender;

    @Lob
    @Column(name = "image", nullable = false)
    private byte[] image;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;
}