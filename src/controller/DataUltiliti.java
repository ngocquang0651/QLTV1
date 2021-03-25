package controller;
/**
 * Lớp này dùng để thao tác với file: ghi file, xoá file,...
 */
import model.Book;
import model.Reader;
import model.BookReaderManagerment;


import java.io.*;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class DataUltiliti {
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;

    //___________mo va dong file khi viet_____________

    void openFileToWrite (String fileName ){
        try {
            fileWriter = new FileWriter(fileName,true);
            bufferedWriter = new BufferedWriter(fileWriter);
            printWriter = new PrintWriter(bufferedWriter);
        }catch (IOException e){
            e.printStackTrace();
        }

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

    //______________Mo va dong file khi doc_____________________
    public void openFileToRead (String fileName){
        try{
            File file =new File(fileName);
            if(!file.exists()){// Neu file chua ton tai
                file.createNewFile();//tao file moi
            }
            scanner = new Scanner(Paths.get(fileName),"UTF-8");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void closeFileToRead(String fileName){
        try{
            scanner.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //_______________sach_______________
    public void writeBookToFile (Book book, String fileName){
        openFileToWrite(fileName);
        printWriter.println(book.getBookID()+"|"+book.getBookName()+"|"+book.getAuthor()+"|"+book.getSpecialization()
                +"|"+book.getPublishYear()+"|"+book.getQuantity());
        closeFileAfterWrite(fileName);
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
        Book book = new Book();
        String[] datas = data.split("\\|");
        //printWriter.println(book.getBookID()+"|"+book.getBookName()+"|"+book.getAuthor()+"|"+book.getSpecialization()
        //                +"|"+book.getPublishYear()+"|"+book.getQuantity());
        //Book book = new Book(Integer.parseInt(datas[0]),datas[1],datas[2],datas[3],
        //  Integer.parseInt(datas[4]),Integer.parseInt(datas[5]));
        book.setBookID(Integer.parseInt(datas[0]));
        book.setBookName(datas[1]);
        book.setSpecialization(datas[2]);
        book.setAuthor(datas[3]);
        book.setPublishYear(Integer.parseInt(datas[4]));
        book.setQuantity(Integer.parseInt(datas[5]));
        return book;
    }

    //_________________reader_____________________

    public void writeReaderToFile(Reader reader, String fileName){
        openFileToWrite(fileName);
        printWriter.println(reader.getReaderID()+"|"+reader.getFullName()+"|"+reader.getAddress()
                +"|"+reader.getPhoneNumber());
        closeFileAfterWrite(fileName);
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
        String[] datas = data.split("\\|");//Tách chuỗi thành mảng nếu gặp | thì tạo ra 1 phần tử
        //printWriter.println(reader.getReaderID()+"|"+reader.getFullName()+"|"+reader.getAddress()
        //                +"|"+reader.getPhoneNumber());
        Reader reader =new Reader();
        reader.setReaderID(Integer.parseInt(datas[0]));
        reader.setFullName(datas[1]);
        reader.setAddress(datas[2]);
        reader.setPhoneNumber(datas[3]);
        return reader;
    }

    //_______________BRM_______________

    public void writeBRMToFile(BookReaderManagerment brm, String fileName){
        openFileToWrite(fileName);
        printWriter.println(brm.getBooks().getBookID()+"|"+brm.getReaders().getReaderID()+"|"+brm.getNumberOfBorrow()+"|"
                +brm.getState()+"|"+brm.getTotal());
        closeFileAfterWrite(fileName);
    }


    public ArrayList<BookReaderManagerment> readBRMFromFile(String fileName){
        Object var;
        var books = readBooksFromFile("BOOK.DAT");
        var readers = readReadersFromFile("READER.DAT");
        openFileToRead(fileName);
        ArrayList<BookReaderManagerment> brms = new ArrayList<>();
        while(scanner.hasNextLine()){
            String data = scanner.nextLine();
            BookReaderManagerment brm = createBRMsFromData(data,readers,books);
            brms.add(brm);
        }


        closeFileToRead(fileName);
        return brms;
    }

    public BookReaderManagerment createBRMsFromData(String data,ArrayList<Reader>readers,ArrayList<Book>books   ) {
        //printWriter.println(brm.getBooks().getBookID()+"|"+brm.getReaders().getReaderID()+"|"+brm.getNumberOfBorrow()+"|"
        //                +brm.getState()+"|"+brm.getTotal());
        String[] datas = data.split("\\|");
        BookReaderManagerment brm =new BookReaderManagerment(getBook(books,Integer.parseInt(datas[0])),
                getReader(readers,Integer.parseInt(datas[1])),Integer.parseInt(datas[2]),datas[3],0);

        //brm.setBooks(new Book(Integer.parseInt(datas[0])));
        //brm.setReaders(new Reader(Integer.parseInt(datas[1])));
        //brm.setNumberOfBorrow(Integer.parseInt(datas[2]));
        //brm.setState(datas[3]);

        return brm;
    }

    /**
     * Phương thức trả về 1 đối tượng reader trong danh sách với ID cho trước
     * @param readers
     * @param readerID
     * @return
     */
    private static Reader getReader(ArrayList<Reader> readers, int readerID) {
        for(int i=0; i<readers.size();i++){
            if(readers.get(i).getReaderID()==readerID){
                return readers.get(i);
            }
        }
        return null;
    }

    private static Book getBook(ArrayList<Book> books, int bookID) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getBookID() == bookID) {
                return books.get(i);
            }
        }
        return null;

    }
    public void updateBRMFile (ArrayList<BookReaderManagerment> list, String fileName){
        //Delete file cu
    File file = new File(fileName);
    if(file.exists()){
        file.delete();
    }
    //ghi moi file nay
    openFileToWrite(fileName);
    for( var brm:list){
        printWriter.println(brm.getBooks().getBookID()+"|"+brm.getReaders().getReaderID()+"|"+brm.getNumberOfBorrow()+"|"
                +brm.getState());
    }
    closeFileAfterWrite(fileName);
}



}
