package at.htl.entities.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GardenerDTO {

    private String name;


    public GardenerDTO(String name) {
        this.name = name;
    }

    public GardenerDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GardenerDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
