/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B1;
import Tools.MyTools;
import java.util.ArrayList;
//cong cu sap xep
import java.util.Collections;
import java.util.Comparator;
//cong cu thao tac voi File
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import Tools.MyTools;
import java.util.Scanner;
/**
 *
 * @author admin
 */
public class VehicleList extends ArrayList <Vehicle> {
    //cong cu sap xep theo ten giam
    Comparator<Vehicle> cNameDesc = new Comparator<Vehicle>() {
        @Override
        public int compare(Vehicle o1, Vehicle o2) {
            return -o1.name.compareTo(o2.name);
        }
    };

    public VehicleList() {
    }
    
    public void loadFromFile(String fname){
        try{
            File f = new File(fname);
            if (!f.exists()) return;
            FileReader fr = new FileReader(f); // read()
            BufferedReader bf = new BufferedReader(fr); //readLine()
            String details;
            while ((details = bf.readLine()) !=null){
                //Splitting deatails into elements
                StringTokenizer stk = new StringTokenizer(details,",");
                String ID = stk.nextToken().toUpperCase();
                String name = stk.nextToken().toUpperCase();
                String color = stk.nextToken().toUpperCase();        
                double price = Double.parseDouble(stk.nextToken());
                String brand = stk.nextToken().toUpperCase();
                String type = stk.nextToken().toUpperCase();
                int productYear = Integer.parseInt(stk.nextToken());
                //Create a Vehicle
                Vehicle v = new Vehicle(ID, name, color, price, brand, type, productYear);
                this.add(v); //adding this vehicle to the list
            }
            bf.close(); fr.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void saveToFile(String fname){
        if (this.size()==0){
            System.out.println("Empty list");
            return;
        }
        try{
            //File f = new File(fname);                               //Dong 67-68 co the bo qua ->69
            //FileWriter fw = new FileWriter(f); // write();
            PrintWriter pw = new PrintWriter(fname); //pritnln();
            for (Vehicle x:this){
                pw.println(x.toString());
            }
            pw.close(); //fw.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    
    /*public  int searchID(String aID){
        for (int i=0; i<this.size(); i++){
            if (this.get(i).getID().equals(aID)) return i;
        }
        return -1;
    }*/
    
    //Them 1 xe
    public void addVehicle(){
        String ID;                  
        String name;
        String color;
        double price;
        String brand;
        String type;
        int productYear;
        //nhap data
        int pos;
        do{
            ID = MyTools.readStr(" ID ", "[vV][\\d]{4}").toUpperCase();     //readStr: doc dung format
            pos = this.indexOf(new Vehicle(ID));                            //[vV]: nhap v hay V deu dc
            if (pos>=0) System.out.println("ID dupplicated");               //[\\d]: 1 ki tu so {4}: lap 4 lan
        }while (pos>=0);                                                    //-> ID: VXXXX/vXXXX
        name = MyTools.readStr("Name", "[a-zA-Z].*").toUpperCase();       //. ki tu bat ki  *: bao nhiu cung dc
        color = MyTools.readStr("Color", "[a-zA-Z].*".toUpperCase());     
        price = Double.parseDouble(
                MyTools.readStr("Price", "[\\d]+[.]?[\\d]*"));                //?: khong co hoac chi co 1
        brand = MyTools.readStr("Brand", "[a-zA-Z].*").toUpperCase();     //+: co it nhat 1
        type = MyTools.readStr("Type", "[a-fA-F].*").toUpperCase();
        productYear = Integer.parseInt(
                MyTools.readStr("Year(XXXX)", "[\\d]{4}"));
        //Tao xe moi
        Vehicle v = new Vehicle(ID, name, color, price, brand, type, productYear);
        this.add(v);
        System.out.println("New vehicle has been added.");
    }
    public void search(){
        int choice;
        String ID, name;
        do{
            choice = Menu.getChoiceInt("Search by ID", "Search by name", "Return");
            switch(choice){
                case 1:
                    ID = MyTools.readStr("ID for search", ".*").toUpperCase();
                    int pos = this.indexOf(new Vehicle(ID));
                    if (pos<0)System.out.println("not found");
                    else System.out.println("Found: " + this.get(pos));
                    break;
                case 2:
                    name = MyTools.readStr("Name for search", ".*").toUpperCase();
                    boolean found = false;
                    for (Vehicle v: this){
                        if(v.name.contains(name)){
                            System.out.println(v);
                            found = true;
                        }
                    }
                    if (!found)System.out.println("Not found!");
                    break;
            }       
        }while (choice>0 && choice<3);
    }
    public void updateVehicle(){
        String ID;
        //Nhap ID
        ID = MyTools.readStr("ID for update", "[.]*").toUpperCase();
        //Tim xe
        int pos = this.indexOf(new Vehicle(ID));
        if (pos<0){
            System.out.println("Not found!");
            return;
        }
        //Cap nhat data
        Vehicle v = this.get(pos); //xe se cap nhat
        //Cap nhat name
        String newName = MyTools.readStr("New name, Enter for bypass", "[.]*");
        newName = newName.trim().toUpperCase();
        if (!newName.isEmpty()) v.name = newName;
        //Cap nhat 5 fields con lai
        String newColor = MyTools.readStr("New color, Enter for bypass", "[.]*");
        newColor = newColor.trim().toUpperCase();
        if (!newColor.isEmpty()) v.color = newColor;
        
        String input = Tools.MyTools.readStr("New price", "[.]*").trim();
        if (!input.isEmpty()) v.price = Double.parseDouble(input);
        
        String newBrand = MyTools.readStr("New brand, Enter for bypass", "[.]*");
        newBrand = newBrand.trim().toUpperCase();
        if (!newBrand.isEmpty()) v.brand = newBrand;
        
        String newType = MyTools.readStr("New type, Enter for bypass", "[.]*");
        newType = newType.trim().toUpperCase();
        if (!newType.isEmpty()) v.type = newType;
        
        String yearinput = Tools.MyTools.readStr("New product year, Enter for bypass", "[.]*").trim();
        if (!yearinput.isEmpty()) v.productYear = Integer.parseInt(yearinput);
    }
    public void removeVehicle(){
        String ID;
        //Nhap ID
        ID = MyTools.readStr("ID for remove", "[.]*").toUpperCase();
        //Tim xe
        int pos = this.indexOf(new Vehicle(ID));
        if(pos<0){
            System.out.println("Not found!");
            return;
        }
        //Xoa xe
        boolean respond = MyTools.readBooLean("Remove! Really?");
        if (respond == true){
            this.remove(pos);
            System.out.println("Remove!");
        }
    }
    //Trich lay ds cac xe co gia <=p
    private VehicleList filterPrice (double p){
        //Cong cu sap xep theo gia giam
        Comparator<Vehicle> priceCom = new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                double d = o1.price - o2.price;
                return (d>0)? -1 : (d<0)? 1 : 0;
            }
        };
        VehicleList L = new VehicleList();
        for (Vehicle v : this){
            if (v.price<=p) L.add(v);
        }
        Collections.sort(L, priceCom);
        return L;
    }
    //Trich lay ds cac xe co gia >=y
    private VehicleList filterYear (int y){
        Comparator<Vehicle> yearCom = new Comparator<Vehicle>() {
            @Override
            public int compare(Vehicle o1, Vehicle o2) {
                double d = o1.productYear - o2.productYear;
                return (d>0)? -1 : (d<0)? 1 : 0;
            }
        };
        VehicleList L = new VehicleList();
        for (Vehicle v : this){
            if (v.productYear>=y) L.add(v);
        }
        Collections.sort(L, yearCom);
        return L;
    }
    public void printAll(){
        for (Vehicle v : this){
            System.out.println(v);
        }
    }
    public void show(){
        Scanner sc = MyTools.sc;
        int choice;
        int year;
        double price;
        VehicleList L;
        do{
            choice = Menu.getChoiceInt("Show all", "Show by price", "Show by year", "Return");
            switch(choice){
                case 1: this.printAll(); break;
                case 2: 
                        System.out.print("Maximum price: ");
                        price = Double.parseDouble(sc.nextLine());
                        L = this.filterPrice(price);
                        L.printAll();
                        break;
                case 3: 
                        System.out.print("Minimum year: ");
                        year = Integer.parseInt(sc.nextLine());
                        L = this.filterYear(year);
                        L.printAll();
                        break;
            }
        }while (choice > 0 && choice < 4);
        
    }
}//Class VehicleList
