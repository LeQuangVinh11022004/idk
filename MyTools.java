/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;
import java.util.Date;                      //Lop mo ta ngay thang
import java.text.SimpleDateFormat;          //lop giup dinh dang ngay thang
import java.text.DateFormat;                //lop giup dinh dang ngay thang
import java.text.ParseException;            //lop mo ta loi khi phan tich String
import java.util.Calendar;                  //lop mo ta cho lich noi chung
import java.util.GregorianCalendar;         //lop mo ta cho duong lich ngay nay
import java.util.Scanner;                   //lop nhap du lieu

/**
 *
 * @author admin
 */
public class MyTools {
    public static final Scanner sc = new Scanner(System.in);
    
    /** Parsing the input String to get a boolean data (true/false)
     * @param input: input string
     * @return true if the first character in input is 'T' or '1' or 'Y'
     */
    public static boolean parseBooLean(String input){
        input = input.trim().toUpperCase();// Chuan hoa chuoi nhap
        char c = input.charAt(0);// Lay ky tu dau cua chuoi nhap
        return c=='T' || c=='1' || c=='Y';// tra gia tri true neu ky tu nay la T, 1, Y
    }
    
    public static String normaLizeDateStr(String dateStr){
        
        
        String result = dateStr.replaceAll("[\\s]+", "");
        
        
        result= result.replaceAll("[./-]+" , "-");
        return result;
    }
    
    public static Date parseDate(String inputStr, String dateFormat){
        inputStr=normaLizeDateStr(inputStr);
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        try{
            return formatter.parse(inputStr);
        }
        catch (ParseException e){
            System.err.println(e);
        }
        return null;
    }
    
    
    public static String toString(Date date, String dateFormat){
        if (date==null) return "";
        
        DateFormat formatter = new SimpleDateFormat(dateFormat);
        
        return formatter.format(date);
    }
    
    
    public static int getPart(Date d, int calendarPart){
        Calendar cal = new GregorianCalendar();
        cal.setTime(d);
        return cal.get(calendarPart);
    }
    
    
    public static boolean readBooLean (String prompt){
        System.out.print(prompt + ": ");
        return parseBooLean(sc.nextLine());
    }
    
    
    public static String readStr (String prompt, String pattern){
        String input;
        boolean valid = false;
        do{
            System.out.print(prompt + ": ");
            input = sc.nextLine().trim();
            valid = input.matches(pattern);
        }
        while (valid == false);
        return input;
    }
    
    
    
    public static Date readDate (String prompt, String dateFormat){
        String input;
        Date d;
        do{
            System.out.print(prompt + ": ");
            input = sc.nextLine().trim();
            d = parseDate(input, dateFormat);
            if ( d==null) System.out.println("Date data is not valid");
        }
        while (d==null);
        return d;
    }
    
    public static Date readDateAfter (String prompt, String dateFormat, Date markerDate){
        String input;
        Date d;
        boolean ok = false;
        do{
            System.out.print(prompt + ": ");
            input = sc.nextLine().trim();
            d = parseDate(input, dateFormat);
            ok = (d!=null && d.after(markerDate));
            if (d==null) System.out.println("Date data is not valid");
        }
        while (!ok);
        return d;
    }
    
    public static Date readDateBefore (String prompt, String dateFormat, Date markerDate){
        String input;
        Date d;
        boolean ok = false;
        do{
            System.out.print(prompt + ": ");
            input = sc.nextLine().trim();
            d = parseDate(input, dateFormat);
            ok = (d!=null && d.before(markerDate));
            if (d==null) System.out.println("Date data is not valid");
        }
        while (!ok);
        return d;
    }
    
    
    public static String generateCode (String prefix, int length, int curNumber){
        String formatStr = "%0" + length + "d"; // -> %07d
        return prefix + String.format(formatStr, curNumber);
    }
    
    public static void main (String[] args){
        System.out.println("Test boolean string:"); // Test boolean string
        System.out.println(parseBooLean("    TrUE  "));
        System.out.println(parseBooLean("    fALSe  "));
        System.out.println(parseBooLean("    1234  "));
        System.out.println(parseBooLean("  01 23  "));
        System.out.println(parseBooLean("    total "));
        System.out.println(parseBooLean("   cosine "));
        System.out.println("Test normalizeDateStr(String):");
        String S = " 7 ... --- 2 / 2023  ";
        System.out.println(S + "--> " + normaLizeDateStr(S));
        S= " 7 ... 2 //// 2023  ";
        System.out.println(S + "--> " + normaLizeDateStr(S));
        
        //Tests date <--> String
        System.out.println("\nTest Date <--> String:");
        String[] formats = {"yyyy-MM-dd", "MM-dd-yyyy", "dd-MM-yyyy"};
        String[] dStrs = { "  2023-02-21 ","  12.--25 - 2023 ","  7 .. 2// 2023"};
        Date d= null;
        for (int i=0; i<3; i++){
            System.out.println (dStrs[i] + "(" + formats[i] + ")");
            d=parseDate(dStrs[i], formats[i]);
            if (d!=null) {
                System.out.println("Year: " + getPart(d, Calendar.YEAR));
                System.out.println("---> Result: " + d);
                System.out.println("---> In the format " + formats[i] + ": " + toString(d, formats[i]));
                
            }
            else System.out.println("Parsing error!");
        }
       
        // Test reading a boolean
        System.out.println("Test reading a boolean data");
        boolean b = readBooLean("Input a boolean data (T/F, 1/0, Y/N)");
        System.out.println(b + " inputted");
        
        //Tests input a date data
        System.out.println("Test input a date data");
        d = readDate("Input a data data (dd-mm-yyyy)", "dd-MM-yyyy");
        System.out.println("Inputted date:");
        System.out.println("In format dd-MM-yyyy: " + toString(d, "dd-MM-yyyy"));
        System.out.println("In format MM-dd-yyyy: " + toString(d, "MM-dd-yyyy"));
        System.out.println("In format yyyy-MM-dd: " + toString(d, "yyyy-MM-dd"));
        
        //Test inputting a phone number including 9..11 digits
        String phoneNo = readStr("Phone number(9..11 digits", "[\\d]{9,11}");
        System.out.println("Inputted phone no. :" + phoneNo);
        
        //Testing for generating an automatic code
        System.out.println("Testing for generating an automactic code");
        String prefix = "P";
        int curNumber = 25;
        int len = 7;
        String code = generateCode(prefix, len, curNumber);
        curNumber++;
        System.out.println("Generated code: " + code);
        code = generateCode(prefix, len, curNumber);
        System.out.println("Generated code: " + code);
       
        //Test reading date data before and after today
        System.out.println("Testing reading date data before and after today");
        Date today = new Date();
        System.out.println("Today: " + MyTools.toString(d, "dd-MM-yyyy"));
        Date dBefore = MyTools.readDateBefore("Date before today", "dd-MM-yyyy", today);
        System.out.println(MyTools.toString(dBefore, "dd-MM-yyyy"));
        Date dAfter = MyTools.readDateAfter("Date after today", "dd-MM-yyyy", today);
        System.out.println(MyTools.toString(dAfter, "dd-MM-yyyy"));
        
    }//main()
    
} //class MyTools
