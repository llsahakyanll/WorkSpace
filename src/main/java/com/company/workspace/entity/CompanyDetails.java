package com.company.workspace.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "company_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDetails {
    @Id
    private Long id;

    @NotBlank(message = "This input cannot be empty.")
    @Size(min = 3, max = 30)
    @Column(name = "name", nullable = false)
    private String name;


    @NotBlank(message = "This input cannot be empty.")
    @Column(name = "address", nullable = false)
    private String address;

    @NotBlank(message = "This input cannot be empty.")
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank(message = "This input cannot be empty.")
    @Column(name = "country", nullable = false)
    private String country;

    @Pattern(regexp = "\\+\\d{3}-\\d{2}-\\d{3}-\\d{3}", message = "Please use pattern +XXX-XX-XXX-XXX")
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @NotBlank(message = "This input cannot be empty.")
    @Column(name = "website", nullable = false)
    private String website;

    @Lob
    @Column(name = "logo", nullable = false)
    private byte[] logo;

    @NotBlank(message = "This input cannot be empty.")
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotBlank(message = "This input cannot be empty.")
    @Column(name = "founded_year", nullable = false)
    private String foundedYear;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;
}
