package controller;

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
            if(b.getBooks().getBookID()==brm.getBooks().getBookID()
                    && b.getReaders().getReaderID()==brm.getReaders().getReaderID()){
                list.set(i,brm);//cap nhat lai so luong quan ly muon
            }
        }

        if(!isUpdate){
            list.add(brm);
        }

        return list;
    }

}
