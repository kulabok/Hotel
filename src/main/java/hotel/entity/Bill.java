package hotel.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    private int id;
    @OneToOne
    @JoinColumn(name = "request")
    private Request request;
    @OneToOne
    @JoinColumn(name = "room")
    private Room room;
    @Column(name = "cost", nullable = false)
    private int cost;
    @Column(name = "paid", nullable = false)
    private boolean paid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
