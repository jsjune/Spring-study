//package sql.practice.test;
//
//import java.io.File;
//
//public class TestMain {
//    public static void main(String[] args) {
//        System.out.println(System.getProperty("user.dir")+"\\");
//        String path = System.getProperty("user.dir") + "\\file\\";
//        File file = new File(path);
//        if (!file.exists()) {
//            try {
//                file.mkdirs();
//                System.out.println("폴더가 생성되었습니다.");
//            } catch (Exception e) {
//                e.getStackTrace();
//            }
//        } else {
//            System.out.println("이미 폴더가 생성되어 있습니다.");
//        }
//
//    }
//}
