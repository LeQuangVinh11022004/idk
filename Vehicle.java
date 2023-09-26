/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B1;

/**
 *
 * @author admin
 */
public class Vehicle implements Comparable<Vehicle> {
    String ID;                  
    String name;
    String color;
    double price;
    String brand;
    String type;
    int productYear;

    public Vehicle(String ID) {
        this.ID = ID;
    }

    public Vehicle(String ID, String name, String color, double price, String brand, String type, int productYear) {
        this.ID = ID;
        this.name = name;
        this.color = color;
        this.price = price;
        this.brand = brand;
        this.type = type;
        this.productYear = productYear;
    }

    @Override
    public int compareTo(Vehicle o) {
        return this.ID.compareTo(o.ID);
    }

    @Override
    public boolean equals(Object obj) {
        Vehicle v = (Vehicle)obj;
        return this.ID.equals(v.ID);
    }

    @Override
    public String toString() {
        return ID + ", " + name + ", " + color + ", " + price + ", " + brand + ", " + type + ", " + productYear;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getProductYear() {
        return productYear;
    }

    public void setProductYear(int productYear) {
        this.productYear = productYear;
    }
    
    
    
    
    
}
