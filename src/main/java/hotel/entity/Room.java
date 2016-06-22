package hotel.entity;

import hotel.entity.enums.RoomClass;

import javax.persistence.*;

/**
 * Created by kulabok on 06.05.2016.
 */
@Entity
@Table(name = "room")
public class Room {
    @Id
    @Column(name = "number", nullable = false)
    private int number;
    @Column(name = "roomclass", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomClass roomclass;
    @Column(name = "personsmax", nullable = false)
    private int personsMax;
    @Column(name = "costperperson", nullable = false)
    private int costPerPerson;
    @Column(name = "available", nullable = false)
    private int available;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public RoomClass getRoomclass() {
        return roomclass;
    }

    public void setRoomclass(RoomClass roomclass) {
        this.roomclass = roomclass;
    }

    public int getPersonsMax() {
        return personsMax;
    }

    public void setPersonsMax(int personsMax) {
        this.personsMax = personsMax;
    }

    public int getCostPerPerson() {
        return costPerPerson;
    }

    public void setCostPerPerson(int costPerPerson) {
        this.costPerPerson = costPerPerson;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
