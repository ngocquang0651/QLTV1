package controller;

import model.Book;
import model.BookReaderManagerment;

import java.util.ArrayList;

/**
 * Chức năng của lớp này là tìm kiếm, cập nhật chỉnh sủa,xoá, xắp xếp
 **/
public class controlerUltiliti {
    //cap nhat lai danh sach quan ly muon
    public ArrayList<BookReaderManagerment> updateBRMInfo(ArrayList<BookReaderManagerment> list,
                                                          BookReaderManagerment brm){
        boolean isUpdate=false;
        for(int i=0;i<list.size();i++){
            BookReaderManagerment b = list.get(i);
            if((b.getBooks().getBookID()==brm.getBooks().getBookID())
                    && (b.getReaders().getReaderID()==brm.getReaders().getReaderID())){
                list.set(i,brm);//cap nhat lai so luong quan ly muon
                isUpdate = true;
                break;
            }
        }
        if(!isUpdate){
            list.add(brm);
        }
        return list;
    }

    public ArrayList<BookReaderManagerment> updateTotalOfBorrow(ArrayList<BookReaderManagerment>list){
        for(int i=0;i<list.size();i++){
            BookReaderManagerment b=list.get(i);
            int count =0;
            for(int j=0;j<list.size();j++){
                if(list.get(j).getReaders().getReaderID()==b.getReaders().getReaderID()){
                    count+=list.get(j).getNumberOfBorrow();
                }
            }
            b.setTotal(count);//update totalofborrow
            list.set(i,b);
        }
        return list;
    }
    public ArrayList<BookReaderManagerment> sortbyReaderName(ArrayList<BookReaderManagerment>list){
        for(int i=0;i<list.size();i++){
            for(int j=list.size()-1;j>i;j--){
                BookReaderManagerment a= list.get(j);
                BookReaderManagerment b=list.get(j-1);
                String[] name1=a.getReaders().getFullName().split(" ");
                String[] name2=b.getReaders().getFullName().split(" ");
                if(name1[name1.length-1].compareTo((name2[name2.length-1]))<0){
                    list.set(j,b);
                    list.set(j-1,a);
                }
            }
        }
        return list;
    }
    public ArrayList<BookReaderManagerment> sortByNumberOfBorrow(ArrayList<BookReaderManagerment>list){
        for(int i=0;i<list.size();i++){

            for(int j= list.size()-1;j>i;j--){
                BookReaderManagerment a= list.get(j-1);
                BookReaderManagerment b=list.get(j);
                if(a.getTotal()< b.getTotal()){
                    list.set(j,a);
                    list.set(j-1,b);
                }
            }
        }
        return list;
    }
/*
    public BookReaderManagerment reseachReader(ArrayList<BookReaderManagerment>list, String name){
        for (var a:list){
            if(a.getReaders().getFullName()==name){
                return a;
            }
        }
        return 0;

    }

 */
}


