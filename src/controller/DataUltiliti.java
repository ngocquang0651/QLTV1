package controller;
/**
 * Lớp này dùng để thao tác với file: ghi file, xoá file,...
 */
import model.Book;
import model.Reader;
import model.BookReaderManagerment;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Scanner;
public class DataUltiliti {
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;
    void openFileToWrite (String fileName ){
        try {
            fileWriter = new FileWriter(fileName,true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void writeBookToFile (Book book, String fileName){
        openFileToWrite(fileName);
        printWriter.println(book.getBookID()+"|"+book.getBookName()+"|"+book.getAuthor()+"|"+book.getSpecialization()
                +"|"+book.getPublishYear()+"|"+book.getQuantity());
        closeFileAfterWrite(fileName);
    }

    public void writeReaderToFile(Reader reader, String fileName){
        openFileToWrite(fileName);
        printWriter.println(reader.getReaderID()+"|"+reader.getFullName()+"|"+reader.getAddress()
                +"|"+reader.getPhoneNumber());
        closeFileAfterWrite(fileName);
    }
    public void writeBRMToFile(BookReaderManagerment brm, String fileName){
        openFileToWrite(fileName);
        printWriter.println(brm.getBooks().getBookID()+"|"+brm.getReaders().getReaderID()+"|"+brm.getNumberOfBorrow()+"|"
                +brm.getState()+"|"+brm.getTotal());
        closeFileAfterWrite(fileName);
    }

    public void closeFileAfterWrite (String fileName){
        try {
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void openFileToRead (String fileName){
        try{
            scanner = new Scanner(Paths.get(fileName),"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<Book> readBooksFromFile(String fileName){
        openFileToRead(fileName);
        ArrayList<Book> books = new ArrayList<>();//kiểu dữ liệu mảng động giống vector trong c++ tự động mở rộng mảng khi có phàn tử thêm vào
        while (scanner.hasNextLine()){ // Nếu còn dòng thì đọc dữ liệu trong file và in vào books
            String data = scanner.nextLine();
            Book book = createBookFromData(data);
            books.add(book);
        }
        closeFileToRead(fileName);
        return books;
    }

    public Book createBookFromData(String data) {
        String[] datas = data.split("//|");
        //printWriter.println(book.getBookID()+"|"+book.getBookName()+"|"+book.getAuthor()+"|"+book.getSpecialization()
        //                +"|"+book.getPublishYear()+"|"+book.getQuantity());
        Book book = new Book(Integer.parseInt(datas[0]),datas[1],datas[2],datas[3],
                Integer.parseInt(datas[4]),Integer.parseInt(datas[5]));
        return book;
    }
    public ArrayList<Reader> readReadersFromFile(String fileName){
        openFileToRead(fileName);
        ArrayList<Reader> readers = new ArrayList<>();
        while (scanner.hasNextLine()){
            String data = scanner.nextLine();
            Reader reader = createReaderFromData(data);
            readers.add(reader);
        }
        closeFileToRead(fileName);
        return readers;
    }

    public Reader createReaderFromData(String data) {
        String[] datas = data.split("//|");//Tách chuỗi thành mảng nếu gặp | thì tạo ra 1 phần tử
        //printWriter.println(reader.getReaderID()+"|"+reader.getFullName()+"|"+reader.getAddress()
        //                +"|"+reader.getPhoneNumber());
        Reader reader =new Reader(Integer.parseInt(datas[0]),datas[1],datas[2],datas[3]);
        return reader;
    }

    public void closeFileToRead(String fileName){
        try{
            scanner.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
