package ra.Business;

import java.util.Scanner;



public class Book {
    private int bookId;
    private String bookName, author, descriptions;
    private double importPrice, exportPrice;
    private float interest;
    private boolean bookStatus = true;

    public Book() {
    }

    public Book(int bookId, String bookName, String author, String descriptions, double importPrice, double exportPrice, float interest, boolean bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.descriptions = descriptions;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.interest = interest;
        this.bookStatus = bookStatus;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public float getInterest() {
        return interest;
    }

    public void setInterest(float interest) {
        this.interest = interest;
    }

    public boolean isBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(boolean bookStatus) {
        this.bookStatus = bookStatus;
    }
    public void inputData(Scanner scanner,Book[] books,int currentBook){
        this.bookId = inputBookID(scanner,books,currentBook);
        this.bookName=inputName(scanner);
        this.author=inputAuthor(scanner);
        this.descriptions=inputDes(scanner);
        this.importPrice=inputImportPrice(scanner);
        this.exportPrice=inputExportPrice(scanner,importPrice);
        this.interest=(float) exportPrice-(float) importPrice;
        this.bookStatus=true;
    }
    public void inputUpdate(Scanner scanner,Book[] books,int currentBook){
        this.bookName=inputName(scanner);
        this.author=inputAuthor(scanner);
        this.descriptions=inputDes(scanner);
        this.importPrice=inputImportPrice(scanner);
        this.exportPrice=inputExportPrice(scanner,importPrice);
        this.interest=(float) exportPrice-(float) importPrice;
        this.bookStatus=inputStatus(scanner);
    }

    private boolean inputStatus(Scanner scanner) {
        System.out.println("Mời bạn nhập trang thại true or false");
        do {
            String status = scanner.nextLine();
            if(status.equalsIgnoreCase("true") || status.equalsIgnoreCase("false")){
                return Boolean.parseBoolean(status);
            }else {
                System.out.println("Mời bạn nhập true hoặc false");
            }
        }while(true);
    }

    private double inputExportPrice(Scanner scanner,double importPrice) {
        System.out.println("Mời bạn nhập giá tiền xuất khẩu ");
        do {
            try {
            double exportPrice = Double.parseDouble(scanner.nextLine());
            if (exportPrice > 1.2*importPrice) {
                return  exportPrice;

            }
            else {
                System.err.println("Số tiền xuất khẩu không dc bé hơn số tiền nhập khẩu");
            }
            } catch (NumberFormatException e) {
                System.err.println("Giá tiền xuất khẩu không hợp lệ. Vui lòng nhập số thực.");
            } catch (Exception e) {
                System.err.println("Đã xảy ra lỗi: " + e.getMessage());
            }
        }while (true);
    }

    private double inputImportPrice(Scanner scanner) {
        System.out.println("Mời bạn nhập giá tiền với mệnh giá lớn hơn 0");
        do {
            try {
                Double importPrice = Double.parseDouble(scanner.nextLine());
                if (importPrice <= 0) {
                    System.err.println("Mời ban nhap lai menh gia lon 0");
                }else {
                    return importPrice;
                }
            }catch (NumberFormatException e){
                System.err.println("Dữ liệu nhập vào ko hợp lê yêu cầu nhập số thực");
            }
        }while (true);
    }

    private String inputDes(Scanner scanner) {
        System.out.println("Mời bạn nhập mô tả");
        do {
            String des = scanner.nextLine();
            if (des.trim().isEmpty()){
                System.err.println("Không ẻể trống tên nhé");
            }
            else {
                if(des.length()<10){
                    System.err.println("Mới ban nhap mo ta voi it nhat 10 ky tu");
                }else {
                    return des;
                }
            }
        }while (true);
    }

    private String inputAuthor(Scanner scanner) {
        System.out.println("Mời bạn nhập tên tác giả");
        do {
            String author=scanner.nextLine();
            if(author.trim().isEmpty()){
                System.err.println("Không được để trống tên");
            }
            else {
                return author;
            }
        }while (true);
    }

    private String inputName(Scanner scanner) {
        System.out.println("Mời bạn nhập tên");
        do {
            String name=scanner.nextLine();
            if(name.trim().isEmpty()){
                System.err.println("Không được để trống tên");
            }
            else {
                return name;
            }
        }while (true);
    }

    private int inputBookID(Scanner scanner, Book[] books, int currentBook) {
        int maxBookId = 0;
        for(int i = 0; i < currentBook; i++) {
            if(books[i].getBookId() > maxBookId) {
                maxBookId = books[i].getBookId();
            }
        }return maxBookId +1;
    }
    public void displayData() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------");

            System.out.printf("[ID: %d|Name:%s|Author:%s|Des:%s|ImportPrice:%.2f|ExportPrice:%.2f|Interest:%.2f|Status:%b]\n",
                    this.bookId, this.bookName, this.author, this.descriptions,
                    this.importPrice, this.exportPrice, this.interest, this.bookStatus);
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }


}
