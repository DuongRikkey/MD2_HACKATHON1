package ra.Run;


import ra.Business.Book;

import java.util.Scanner;

public class ManagementBook {
   public static Book[] books = new Book[100];
   public static int currentBook=0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("****************MENU******************");
            System.out.println("1.Nhập số lượng sách thêm mới và nhập thông tin cho từng cuốn sách");
            System.out.println("2.Hiển thị thông tin tất cả sách trong thư viện ");
            System.out.println("3.Sắp xếp sách theo lợi nhuận tăng dần ");
            System.out.println("4.Xóa sách theo mã sách ");
            System.out.println("5.Tìm kiếm tương đối sách theo tên sách hoặc mô tả ");
            System.out.println("6.Thay đổi thông tin sách theo mã sách");
            System.out.println("7.Exit");
            byte choice = Byte.parseByte(sc.nextLine());
            switch (choice) {
                case 1:
                    addNewBook(sc);
                    break;
                case 2:
                    displayAllBooks();
                    break;
                case 3:
                    sortByInterest(sc);
                    break;
                case 4:
                    deleteBook(sc);
                    break;
                case 5:
                    searchNewBookBookOrDescrip(sc);
                    break;
                case 6:
                    updateBook(sc);
                    break;
                case 7:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again");
            }
        } while (true);
    }

    private static void sortByInterest(Scanner sc) {
        for (int i = 0; i < currentBook-1; i++) {
            for (int j = i+1; j < currentBook; j++) {
                if(books[i].getInterest()>books[j].getInterest()) {
                    Book temp = books[i];
                    books[i] = books[j];
                    books[j] = temp;
                }
            }
        }displayAllBooks();
    }

    private static void searchNewBookBookOrDescrip(Scanner sc) {
        if(currentBook==0) {
            System.err.println("List danh sách trống");
            return;
        }
        System.out.println("Mời bạn nhập tên sách hoặc miêu tả bạn muốn tìm");
        String bookSerch = sc.nextLine();
        boolean check=false;
        for (int i = 0; i < currentBook; i++) {
            if(books[i].getBookName().contains(bookSerch)||books[i].getDescriptions().contains(bookSerch)) {
                books[i].displayData();
                check=true;
                break;
            }

        }if(!check){
            System.err.println("Không tìm thấy "+bookSerch);
        }


    }



    private static void deleteBook(Scanner sc) {
        System.out.println("Mời bạn nhập ID muốn xoa");
        int idDeleted = Integer.parseInt(sc.nextLine());
        int indexDeleted = findIndexByID(idDeleted);
        if(indexDeleted!=-1){
            for (int i = indexDeleted; i < currentBook; i++){
                books[i]=books[i+1];
                currentBook--;
            }
        }
        else {
            System.err.println("Không tim thấy ID muốn xóa");
        }
    }

    private static void updateBook(Scanner sc) {
        System.out.println("Lựa chọn ID update");
        int choice = Integer.parseInt(sc.nextLine());
        int index=findIndexByID(choice);
        if(index!=-1){
            books[index].inputUpdate(sc,books,currentBook);
        }
        else {
            System.out.println("Không tìm thấy id xóa"+choice);
        }
    }

    private static void addNewBook(Scanner sc) {
        System.out.println("Mời bạn nhập số sách muốn thêm");
        do {
            try {


            int bookCode = Integer.parseInt(sc.nextLine());
            if(bookCode>0) {
                for (int i = 0; i < bookCode; i++) {
                    System.out.println("Mời bạn nhập sách thứ"+(i+1));
                    books[currentBook] = new Book();
                    books[currentBook].inputData(sc, books, currentBook);
                    currentBook++;

                }
                break;
            } else {
                System.err.println("số lượng phải lớn hơn ");
            }
            }
            catch (NumberFormatException e) {
                System.err.println("Vui lòng nhập số nguyên hợp lê");
            }
            catch (Exception e){
                System.err.println("đã xảy ra lỗi"+ e.getMessage());
            }


        }while (true);
    }

    private static void displayAllBooks() {
        if (currentBook==0){
            System.err.println("Danh sách trống");;
        }
        for (int i = 0; i < currentBook; i++) {
            books[i].displayData();
        }
    }
    public static int findIndexByID(int id){
         for (int i = 0; i < currentBook; i++) {
             if(books[i].getBookId()==id){
                 return i;
             }
         }
         return -1;
    };


}
