package com.platform.blogmanagement.blog.user.model;

import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
@Document(collection = "Users") @Builder
@ToString
public class UserModel implements UserDetails {
        @Id
        String userId;
        String userFullName;
        String userPassword;
        Set<String> userRoles;


        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();

                for (String role: this.userRoles){
                        list.add(new SimpleGrantedAuthority(UserRoles.ROLE_PREFIX + role));
                }
                return list;
        }

        @Override
        public String getPassword() {
                return this.userPassword;
        }

        @Override
        public String getUsername() {
                return this.userId;
        }

        @Override
        public boolean isAccountNonExpired() {
                return false;
        }

        @Override
        public boolean isAccountNonLocked() {
                return false;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return false;
        }

        @Override
        public boolean isEnabled() {
                return true;
        }
}
