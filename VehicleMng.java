/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B1;

import Tools.MyTools;

/**
 *
 * @author admin
 */
public class VehicleMng {
    public static void main(String[] args){
        String filename = "./data/vehicles.dat";
        VehicleList list = new VehicleList();
        //nap ngay du lieu tu file
        list.loadFromFile(filename);
        //quan li menu
        int choice;
        String[] opts = {"Add vehicles", "Update vehicles", "Delete vehicles", "Search vehicles", "Show vehicles", "Save to file", "Quit the program"};
        boolean changed = false; //data da bi thay doi chua
        do{
            choice = Menu.getChoiceInt((Object[]) opts);
            switch(choice){
                case 1:
                    list.addVehicle();
                    changed = true;
                    break;
                case 2:
                    list.updateVehicle();
                    changed = true;
                    break;
                case 3:
                    list.removeVehicle();
                    changed = true;
                    break;
                case 4:
                    list.search();
                    break;
                case 5:
                    list.show();
                    break;
                case 6:
                    list.saveToFile(filename);
                    changed = false;
                    break;
                default:
                    if (changed){
                        boolean b = MyTools.readBooLean("Data changed. Save to file? Y/N");
                        if (b==true) list.saveToFile(filename);
                    }
                    System.out.println("BYE!");
            }
        }while (choice > 0 && choice <= opts.length);
    }
}
