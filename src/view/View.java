package view;

import controller.DataUltiliti;
import model.Book;

import java.sql.SQLOutput;
import java.util.Scanner;

public class View {
    public static void main(String[] args) {
        Object var;
        var bookFileName ="BOOK.DAT";
        var controller = new DataUltiliti();
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("___________MENU___________");
            System.out.println("1. thêm 1 đầu sách vào trong file.");
            System.out.println("2. Hiện thị sách có trong file.");
            System.out.println("0. Thoát khỏi chương trình");

            choice = scanner.nextInt();
            scanner.nextLine();//bo dong chua lua chon
            switch (choice){
                case 0:{
                    System.out.println("Thank you bạn đã sử dụng dịch vụ :");
                    break;
                }
                case 1:{
//int bookID, String bookName, String author, String specialization, int publishYear, int quantity
                    String[] specs={"Science","Art","Economic","IT"};
                    String bookName,author,spec;
                    int year, quanlity,a;
                    System.out.println("Nhap ten sach");
                    bookName=scanner.nextLine();
                    System.out.println("nhap ten tac gia");
                    author = scanner.nextLine();
                    do {
                        System.out.println("nhap the loai sach: 1.Science,2.Art,3.Economic,4.IT");
                        a = scanner.nextInt();
                    }while(a<1||a>4);
                        spec = specs[a-1];
                    System.out.println("nhap nam phat hanh");

                    Book book = new Book();
                    controller.writeBookToFile(book,bookFileName);
                }
            }
        }while (choice !=0);
    }
}
