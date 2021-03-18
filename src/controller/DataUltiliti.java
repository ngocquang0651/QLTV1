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
    void writeBookToFile (Book book, String fileName){
        openFileToWrite(fileName);
        printWriter.println(book.getBookID()+"|"+book.getBookName()+"|"+book.getAuthor()+"|"+book.getSpecialization()
                +"|"+book.getPublishYear()+"|"+book.getQuantity());
        closeFileAfterWrite(fileName);
    }

    void writeReaderToFile(Reader reader, String fileName){
        openFileToWrite(fileName);
        printWriter.println(reader.getReaderID()+"|"+reader.getFullName()+"|"+reader.getAddress()
                +"|"+reader.getPhoneNumber());
        closeFileAfterWrite(fileName);
    }
    void writeBRMToFile(BookReaderManagerment brm, String fileName){
        openFileToWrite(fileName);
        printWriter.println(brm.getBooks().getBookID()+"|"+brm.getReaders().getReaderID()+"|"+brm.getNumberOfBorrow()+"|"
                +brm.getState()+"|"+brm.getTotal());
        closeFileAfterWrite(fileName);
    }

    void closeFileAfterWrite (String fileName){
        try {
            printWriter.close();
            bufferedWriter.close();
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    void openFileToRead (String fileName){

    }

}
