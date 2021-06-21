package tech.degree.demo.test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Testv2 {

    @Id
    @Column(nullable = false, updatable = false)
    private Integer id;
    private String datee;
    private String timee;
    private String temperature;
    private String humidity;

    public Testv2(Integer id,
                String datee,
                String timee,
                String temperature,
                String humidity) {
        this.id = id;
        this.datee = datee;
        this.timee = timee;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public Testv2() {
    }

    public Integer getId() {
        return id;
    }

    public String getDatee() {
        return datee;
    }

    public String getTimee() {
        return timee;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setId(Integer entry_id) {
        this.id = entry_id;
    }

    public void setDatee(String entry_date) {
        this.datee = entry_date;
    }

    public void setTimee(String entry_time) {
        this.timee = entry_time;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    @Override
    public String toString() {
        return "Testv2{" +
                "id=" + id +
                ", datee='" + datee + '\'' +
                ", timee='" + timee + '\'' +
                ", temperature='" + temperature + '\'' +
                ", humidity='" + humidity + '\'' +
                '}';
    }
}
