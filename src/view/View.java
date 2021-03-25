package view;

import controller.DataUltiliti;
import model.Book;
import model.BookReaderManagerment;
import model.Reader;
import controller.controlerUltiliti;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class View {
    public static void main(String[] args) {
        Object var;
        var bookFileName = "BOOK.DAT";
        var readerFileName = "READER.DAT";
        var bRMFileName = "BRM.DAT";
        var controller = new DataUltiliti();
        var ultiliti= new controlerUltiliti();
        var books = new ArrayList<Book>();
        var readers = new ArrayList<Reader>();
        var brms = new ArrayList<BookReaderManagerment>();
        var isBookChecked = false;
        var isReaderChecked = false;
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("___________MENU___________");
            System.out.println("1. thêm 1 đầu sách vào trong file.");
            System.out.println("2. Hiện thị sách có trong file.");
            System.out.println("3. Them nguoi doc vo file. ");
            System.out.println("4. Hiện thị nguoi doc có trong file.");
            System.out.println("5. Tao ra danh sach brm va in ra man hinh ");
            System.out.println("0. Thoát khỏi chương trình");

            choice = scanner.nextInt();
            scanner.nextLine();//bo dong chua lua chon
            switch (choice) {
                case 0: {
                    System.out.println("Thank you bạn đã sử dụng dịch vụ :");
                    break;
                }
                case 1: {
//int bookID, String bookName, String author, String specialization, int publishYear, int quantity
                    if (!isBookChecked) {
                        checkBookTD(controller, bookFileName);//Check IDbị trùng.
                        isBookChecked = true;
                    }
                    String[] specs = {"Science", "Art", "Economic", "IT"};
                    String bookName, author, spec;
                    int year, quanlity, a;
                    System.out.println("Nhap ten sach");
                    bookName = scanner.nextLine();
                    System.out.println("nhap ten tac gia");
                    author = scanner.nextLine();
                    do {
                        System.out.println("nhap the loai sach: 1.Science,2.Art,3.Economic,4.IT");
                        a = scanner.nextInt();
                    } while (a < 1 || a > 4);
                    spec = specs[a - 1];
                    System.out.println("nhap nam phat hanh");
                    year = scanner.nextInt();
                    System.out.println("nhap so luong");
                    quanlity = scanner.nextInt();
                    Book book = new Book(bookName, author, spec, year, quanlity);
                    controller.writeBookToFile(book, bookFileName);
                    break;
                }
                case 2: {
                    books = controller.readBooksFromFile(bookFileName);
                    showBookinfo(books);
                    break;
                }
                case 3: {
//public Reader(  String fullName, String address, String phoneNumber) {
                    if (!isReaderChecked) {
                        checkReaderTD(controller, readerFileName);//Check IDbị trùng.
                        isReaderChecked = true;
                    }
                    String fullname, address, phoneNumber;
                    System.out.println("Nhap ten nguoi muon: ");
                    fullname = scanner.nextLine();
                    System.out.println("Nhap dia chi: ");
                    address = scanner.nextLine();
                    do {
                        System.out.println("Nhap so dt bao gom 10 so va ky tu");
                        phoneNumber = scanner.nextLine();
                    } while (phoneNumber.length() != 3);
                    Reader reader = new Reader(fullname, address, phoneNumber);
                    controller.writeReaderToFile(reader, readerFileName);
                    break;
                }
                case 4: {
                    brms = controller.readBRMFromFile(bRMFileName);
                    for (var a:brms) {
                        System.out.println(a);
                    }
                    readers = controller.readReadersFromFile(readerFileName);
                    showReaderinfo(readers);


                    break;
                }
                case 5: {

                    //B0: khoi t danh sach
                    books = controller.readBooksFromFile(bookFileName);
                    readers = controller.readReadersFromFile(readerFileName);
                    brms = controller.readBRMFromFile(bRMFileName);
                    //B1:

                    int readerID, bookID;
                    boolean isBorrowable = false;


                    do {
                        showReaderinfo(readers);
                        System.out.println("nhap ma ban doc");
                        readerID = scanner.nextInt();
                        if (readerID == 0) {
                            break;//tat ca ban doc dax muon du sach quy dinh
                        }
                        isBorrowable = checkBorrowed(brms, readerID);
                        if (isBorrowable) {
                            break;
                        } else {
                            System.out.println("ban doc da muon du so sach cho phep");
                            break;
                        }
                    } while (true);

                    //B1
                    boolean isFull = false;
                    do {
                        showBookinfo(books);
                        System.out.println("nhap ma sach, nhap 0 de bo qua");
                        bookID = scanner.nextInt();
                        if(bookID == 0) {
                            break;
                        }
                        isFull = checkFull(brms, readerID, bookID);//true neu muon du 3
                        if (isFull) {
                            System.out.println("vui long chon dau sach khac!!");
                        } else {
                            break;
                        }

                    } while (true);
                    //B2 nhap so luong muon

                    int toltal1 = getTotal1(brms,readerID,bookID);
                    do {
                        System.out.println("nhap so luong muon (toi da 3 cuon)(da muon " + toltal1 + "): ");
                        int x= scanner.nextInt();

                        if ((x + toltal1) >= 1 &&  (x + toltal1) <= 3) {
                            toltal1 = toltal1 +x;
                            break;
                        } else {
                            System.out.println("nhap qua so luong sach duoc muon, vui long nhap laij!!");
                        }
                    } while (true);

                    scanner.nextLine();//bo dong co chua so
                    System.out.println("nhap tinh trang");
                    String status = "";
                    status = scanner.nextLine();

                    //B3: Cap nhat laij file BRM.Dat
                    //Book books, Reader readers, int numberOfBorrow, String state, int total
                    Book currentBook = getBook(books,bookID);
                    Reader currentReader = getReader(readers,readerID);
                    BookReaderManagerment b =new BookReaderManagerment(currentBook,currentReader,toltal1,status,0);
                    controller.writeBRMToFile(b,bRMFileName);
                    //B4
                    brms = ultiliti.updateBRMInfo(brms,b);//cap nhat danh sach quan ly muon
                    controller.updateBRMFile(brms,bRMFileName);// cap nhat danh sach
                    showBRMInfo(brms);
                    break;
                }
                case 6:{
                    int x=0;
                    brms=controller.readBRMFromFile(bRMFileName);//doc danh sach trong file
                    //update tong so luong muon
                    brms = ultiliti.updateTotalOfBorrow(brms);
                    for (var a:brms) {
                        System.out.println(a);
                    }
                    do{
                        System.out.println("__________cac lua chon xap xep__________");
                        System.out.println("1. xap xep theo ban doc tu A-->z");
                        System.out.println("2. xap xep theo tong so luong muon(giam dan)");
                        System.out.println("0. thoat khoir mennu");
                        System.out.println("m chon?");
                        x= scanner.nextInt();
                        if(x==0){
                            break;
                        }
                        switch (x){
                            case 1:{
                                    //xap xep theo ten
                                brms= ultiliti.sortbyReaderName(brms);

                                showBRMInfo(brms);
                                break;
                            }
                            case 2:{
                                //xap xep theo so luong muon
                                brms = ultiliti.sortByNumberOfBorrow(brms);
                                showBRMInfo(brms);
                                break;
                            }
                        }
                    }while(true);
                    break;
                }
                case 7:{
                    brms= controller.readBRMFromFile(bRMFileName);
                    String name;
                    System.out.println("nhap ten ban muon tim kiem");
                    name=scanner.nextLine();
                    for (var a:brms) {
                        if(a.getReaders().getFullName().equals(name)==true){
                            System.out.println(a);
                        }
                    }
                    break;
                }


            }

        } while (choice != 0);

    }

    private static void showBRMInfo(ArrayList<BookReaderManagerment> brms) {
        System.out.println("xuat ra danh sach file");
        for(var b:brms){
            System.out.println(b);
        }
    }

    private static Reader getReader(ArrayList<Reader> readers, int readerID) {
        for(int i=0; i<readers.size();i++){
            if(readers.get(i).getReaderID()==readerID){
                return readers.get(i);
            }
        }
        return null;
    }

    private static Book getBook(ArrayList<Book> books, int bookID) {
        for(int i=0;i<books.size();i++){
            if(books.get(i).getBookID()==bookID){
                return books.get(i);
            }
        }
        return null;
    }
//tra ve so luong da muon
    private static int getTotal1(ArrayList<BookReaderManagerment> brms,
                                 int readerID, int bookID) {


        for(var b : brms){
            if(b.getReaders().getReaderID()==readerID
                    && b.getBooks().getBookID()==bookID){
                return b.getNumberOfBorrow();
            }
        }
        return 0;

    }

    //xem coi soluong ban doc dax muon toi da hay chua
    private static boolean checkFull(ArrayList<BookReaderManagerment> brms, int readerID, int bookID){
            for (var r : brms) {
                if (r.getReaders().getReaderID() == readerID
                        && r.getBooks().getBookID() == bookID
                        && r.getNumberOfBorrow() == 3){
                    return true;//khong duoc muon dau sach nay
                }
            }
            return false;// duoc muon tiep dau sach nay
        }

            private static boolean checkBorrowed (ArrayList < BookReaderManagerment > brms,int readerID){
                int count = 0;
                for (var r:brms) {

                    if (r.getReaders().getReaderID() == readerID) {
                        count =count + r.getNumberOfBorrow();
                    }
                }
                if (count == 15) {
                    return false;//khong muon duoc nua
                }
                return true;//duoc phep muon
            }

            private static void showReaderinfo (ArrayList < Reader > readers) {
                System.out.println("xuat ra ng doc co trogn file");
                for (var c : readers) {
                    System.out.println(c);
                }
            }


            public static void checkBookTD (DataUltiliti controller, String fileName){
                var listBook = controller.readBooksFromFile(fileName);
                if (listBook.size() != 0) {
                    Book.setId(listBook.get(listBook.size() - 1).getBookID() + 1);
                }
            }
            public static void checkReaderTD (DataUltiliti controller, String fileName){
                var listReader = controller.readReadersFromFile(fileName);
                if (listReader.size() != 0) {
                    Reader.setId(listReader.get(listReader.size() - 1).getReaderID() + 1);
                }
            }

            private static void showBookinfo (ArrayList < Book > books) {
                System.out.println("xuat ra thong tin sach trong file");
                for (var b : books) {
                    System.out.println(b);
                }
            }

}
