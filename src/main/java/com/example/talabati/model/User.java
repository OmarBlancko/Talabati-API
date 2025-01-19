package com.example.talabati.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message="Username cannot be  empty")
    @Size(min =3 ,max = 30,message="Username must be between 3 and 30 charachters !")
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    @Email
    private String email;
    @Column(nullable = false)
    @NotNull(message = "Password cannot be null")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
    @Column(nullable = false)

    private String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_settings_id", referencedColumnName = "id")
    private ProfileSettings profileSettings;
    @JsonIgnore // Prevents circular references when serializing
    @ManyToMany(fetch = FetchType.LAZY)

    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ProfileSettings getProfileSettings() {
        return this.profileSettings;
    }

    public void setProfileSettings(ProfileSettings profileSettings) {
        this.profileSettings = profileSettings;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void updateFrom(User other) {
        if (other == null) {
            throw new IllegalArgumentException("The provided user is null");
        }

        // Update basic fields
        if (other.getUsername() != null) {
            this.username = other.getUsername();
        }
        if (other.getEmail() != null) {
            this.email = other.getEmail();
        }
        if (other.getPhoneNumber() != null) {
            this.phoneNumber = other.getPhoneNumber();
        }

        // Update profile settings if provided
        if (other.getProfileSettings() != null) {
            if (this.profileSettings == null) {
                this.profileSettings = new ProfileSettings(id, false, false, email, other);
            }
            this.profileSettings.updateFrom(other.getProfileSettings()); // Assume ProfileSettings has an updateFrom method
        }

        // Update roles if provided
        if (other.getRoles() != null) {
            this.roles.clear();
            this.roles.addAll(other.getRoles());
        }

        // Update password if provided (hash it before saving, but you can skip this step if not updating here)
        if (other.getPassword() != null && !other.getPassword().isEmpty()) {
            this.password = other.getPassword(); // Ensure password is hashed before saving
        }
    }

}
