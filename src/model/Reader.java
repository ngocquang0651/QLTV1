package model;

public class Reader {
    public static int id=1000000000;
    private int readerID;
    private String fullName;
    private String address;
    private String phoneNumber;

    public Reader(int readerID) {
        this.readerID = readerID;
    }

    public Reader() {
    }

    public Reader(int readerID, String fullName, String address, String phoneNumber) {
        this.readerID = readerID;
        this.fullName = fullName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }


    public int getReaderID() {
        return readerID;
    }
    public void setReaderID() {
        this.readerID = id++;
    }

    public void setReaderID(int id) {
        this.readerID = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Reader{" +
                "readerID=" + readerID +
                ", fullName='" + fullName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

}
