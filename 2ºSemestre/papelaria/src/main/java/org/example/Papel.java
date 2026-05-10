package org.example;

public class Papel {
    private String marca;
    private String id;

    public Papel(String marca, String id){
        this.marca = marca;
        this.id = id;
    }
    public String getMarca(){
        return marca;
    }
    public void setMarca(String marca){
        this.marca = marca;
    }
    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
