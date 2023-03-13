//package sql.practice.excelParsing;
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.UUID;
//
//@Component
//public class FileStore {
//    private final String rootPath = System.getProperty("user.id");
//    private final String fileDir = rootPath + "/files/";
//    public String getFullPath(String filename) { return fileDir + filename; }
//
//    public void storeFile(MultipartFile multipartFile) throws IOException {
//
//
//        String originalFilename = multipartFile.getOriginalFilename();
//        // 작성자가 업로드한 파일명 -> 서버 내부에서 관리하는 파일명
//        // 파일명을 중복되지 않게끔 UUID로 정하고 ".확장자"는 그대로
//        String storeFilename = UUID.randomUUID() + "." + extractExt(originalFilename);
//        System.out.println(storeFilename);
//        // 파일을 저장하는 부분 -> 파일경로 + storeFilename 에 저장
//        multipartFile.transferTo(new File(getFullPath(storeFilename)));
//
//    }
//
//    private String extractExt(String originalFilename) {
//        int pos = originalFilename.lastIndexOf(".");
//        return originalFilename.substring(pos + 1);
//    }
//}
