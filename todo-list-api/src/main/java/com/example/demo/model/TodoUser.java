package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class TodoUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String userName;
    private String name;
    private String password;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<TodoRole> roles;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<TodoGrantedAuthorities> grantedAuthorities;

    @OneToMany(mappedBy = "todoUser")
    @JsonManagedReference // To prevent Stackoverflow Error
    private List<TodoTask> tasks;

    @Override
    public Collection<TodoGrantedAuthorities> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }
}
