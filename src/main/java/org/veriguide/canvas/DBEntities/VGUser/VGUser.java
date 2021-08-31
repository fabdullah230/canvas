package org.veriguide.canvas.DBEntities.VGUser;


import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table
public class VGUser {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;
    @CreationTimestamp
    private LocalDateTime createdAt;

    //from LTI
    @Column(unique = true)
    private String userId;
    private String userName;
    private VGUserRole role;
    private Boolean isAdmin;



    public VGUser() {
    }

    public VGUser(VGUserRole role, String userName, LocalDateTime createdAt, Boolean isAdmin, String userId) {
        this.role = role;
        this.userName = userName;
        this.createdAt = createdAt;
        this.isAdmin = isAdmin;
        this.userId = userId;
    }


    //boilerplate
    @Override
    public String toString() {
        return "VGUser{" +
                "id=" + id +
                ", role=" + role +
                ", isAdmin=" + isAdmin +
                ", userName='" + userName + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public VGUserRole getRole() {
        return role;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
