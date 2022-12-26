package mesh.e.web_note_phone.models;

import javax.persistence.*;


@Entity
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    private String name;
    private Long number_phone;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User userId;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNumber_phone() {
        return number_phone;
    }

    public void setNumber_phone(Long number_phone) {
        this.number_phone = number_phone;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Phone() {
    }

    public Phone(String name, Long number_phone, User userId) {
        this.userId = userId;
        this.name = name;
        this.number_phone = number_phone;
    }

}
