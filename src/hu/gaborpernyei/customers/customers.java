package hu.gaborpernyei.customers;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class customers {

    public static void main(String[] args) {
        AllCustomers allCustomers;

        allCustomers = new AllCustomers();
        allCustomers.loadCustomers();
        allCustomers.sortByAccDate();
        allCustomers.saveCustomers("szamlanyitas.csv");
        allCustomers.sortByNames();
        allCustomers.saveCustomers("nevek.csv");
        allCustomers.sortBySum();
        allCustomers.saveCustomers("penzosszegek.csv");
    }

}

    //az összes ügyfél osztálya
    class AllCustomers{
        private List<OneCustomer> customers = new ArrayList<OneCustomer>();
        private String headerOfCSV ;

        //Ügyfelek kiírása
        public boolean saveCustomers(String fileName){
            BufferedWriter bw = null;
            String oneLine;

            File outputFile = new File(fileName);
            try {
                bw = new BufferedWriter( new FileWriter(outputFile));
                bw.write(headerOfCSV); bw.newLine();
                for (OneCustomer oc: customers ) {
                    oneLine = oc.getName()+","+oc.getMotherName()+","+oc.getDayOfBirth()+","+oc.getPlaceOfBirth()+","+
                              oc.getAccDate()+","+oc.getAccNo()+","+oc.getBalance()+","+oc.getDeposit();
                    bw.write( oneLine ); bw.newLine();
                }
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }

        //Ügyfelek beolvasása
        public boolean loadCustomers(){
            BufferedReader br = null;
            String oneLine;
            String[] rawValues;
            Integer balance;
            Integer deposit;
            OneCustomer aCustomer;

            //File beolvasása
            File inputFile = new File("ugyfelek.csv");
            try {
                br = new BufferedReader( new FileReader(inputFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            }
            try {
                //A fejléc sor beolvasása és eltárolása
                headerOfCSV = br.readLine();
                //egy sor beolvasása, amíg vannak sorok.
                while ((oneLine = br.readLine()) != null) {
                    rawValues = oneLine.split(",");
                    balance = Integer.parseInt(rawValues[6]);
                    deposit = Integer.parseInt(rawValues[7]);
                    aCustomer = new OneCustomer( rawValues[0], rawValues[1], rawValues[2], rawValues[3],
                                                 rawValues[4], rawValues[5], balance, deposit );
                    customers.add(aCustomer);
                }
                br.close();
            } catch (IOException e ){
                e.printStackTrace();
                return false;
            }
            System.out.println("Count of loaded customers:" + customers.size());

            return true;
        }

        //Ügyfelek rendezése számlanyitás dátuma szerint
        public void sortByAccDate(){
            Collections.sort( customers , (c1, c2) -> c1.getAccDate().compareTo(c2.getAccDate()));
        }
        //Ügyfelek rendezése név és anyja neve szerint
        public void sortByNames(){
            Collections.sort( customers , (c1, c2) -> c1.getNames().compareTo(c2.getNames()));
        }
        //Ügyfelek rendezése balance+deposit szerint
        public void sortBySum(){
            Collections.sort( customers , (c1, c2) -> c1.getSum().compareTo(c2.getSum()));
        }
    }

    //Egy ügyfél osztálya.
    class OneCustomer{
        private String name;                //0
        private String motherName;          //1
        private String dayOfBirth;          //2 - Konkrét Date típusként kezelése a feladat szempontjából indifferens.
        private String placeOfBirth;        //3
        private String accDate;             //4 - Konkrét Date típusként kezelése a feladat szempontjából indifferens, mivel pont megfelelő formátumban van a rendezéshez is.
        private String accNo;               //5
        private Integer balance;            //6
        private Integer deposit;            //7

        public OneCustomer( String name, String motherName, String dayOfBirth, String placeOfBirth, String accDate,
                          String accNo, Integer balance, Integer deposit )
        {
            setName( name );
            setMotherName( motherName);
            setDayOfBirth(dayOfBirth);
            setPlaceOfBirth(placeOfBirth);
            setAccDate(accDate);
            setAccNo(accNo);
            setBalance(balance);
            setDeposit(deposit);
        }

        public OneCustomer(){}

        public void setName(String name) {
            this.name = name;
        }

        public void setMotherName(String motherName) {
            this.motherName = motherName;
        }

        public void setDayOfBirth(String dayOfBirth) {
            this.dayOfBirth = dayOfBirth;
        }

        public void setPlaceOfBirth(String placeOfBirth) {
            this.placeOfBirth = placeOfBirth;
        }

        public void setAccDate(String accDate) {
            this.accDate = accDate;
        }

        public void setAccNo(String accNo) {
            this.accNo = accNo;
        }

        public void setBalance(Integer balance) {
            this.balance = balance;
        }

        public void setDeposit(Integer deposit) {
            this.deposit = deposit;
        }

        public String getName() {
            return name;
        }

        public String getMotherName() {
            return motherName;
        }

        public String getDayOfBirth() {
            return dayOfBirth;
        }

        public String getPlaceOfBirth() {
            return placeOfBirth;
        }

        public String getAccDate() {
            return accDate;
        }

        public String getAccNo() {
            return accNo;
        }

        public Integer getBalance() {
            return balance;
        }

        public Integer getDeposit() {
            return deposit;
        }

        public String getNames(){ return name + motherName; }

        public Integer getSum(){ return balance + deposit; }
    }

