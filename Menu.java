/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B1;
import java.util.Scanner;
import java.util.List;       
/**
 *
 * @author admin
 */
public class Menu {
    private final static Scanner sc = new Scanner(System.in);
    
    public static int getChoiceInt (Object... options){
        int L = options.length;
        for (int i=0; i<L; i++){
            System.out.println((i+1) + "-" + options[i]);
        }
        System.out.print("Choose (1.." + L + "): ");
        return Integer.parseInt(sc.nextLine());
    }
    
    public static Object getChoiceObject (List list){
        int choice;
        int L = list.size();
        int i;
        for (i=0; i<L; i++){
            System.out.println((i+1) + '-' + list.get(i).toString());
        }
        System.out.print("Choose (1.." + L + "): ");
        choice = Integer.parseInt(sc.nextLine());
        return (choice>0 && choice<=L)? list.get(choice-1): null;
    }
}
