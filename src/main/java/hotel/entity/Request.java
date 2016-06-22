package hotel.entity;

import hotel.entity.enums.RoomClass;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by kulabok on 06.05.2016.
 */
@Entity
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    private int id;
    @ManyToOne (cascade = {CascadeType.ALL})
    @JoinColumn(name = "user")
    private User user;
    @Column(name = "roomclass", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomClass roomClass;
    @Column(name = "personsquantity", nullable = false)
    private int personQuantity;
    @Column(name = "start", nullable = false)
    private Date start;
    @Column(name = "end", nullable = false)
    private Date end;
    @Column(name = "processed", nullable = false)
    private boolean processed;


    public int getId() {
        return id;
    }

    public void setId(int request_id) {
        this.id = request_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RoomClass getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(RoomClass roomClass) {
        this.roomClass = roomClass;
    }

    public int getPersonQuantity() {
        return personQuantity;
    }

    public void setPersonQuantity(int personQuantity) {
        this.personQuantity = personQuantity;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}
