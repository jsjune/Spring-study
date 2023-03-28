package sql.practice.prac;


import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Prac8 {
    @Test
    void test() throws IOException {
        String fileName = "test";
        String contentType = "xls";
        String filePath = "src/test/resources/excel/test.xls";
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));
        MockMultipartFile mockMultipartFile = new MockMultipartFile(fileName, fileName + "." + contentType, contentType, fileInputStream);

        String getFileName = mockMultipartFile.getOriginalFilename().toLowerCase();
        System.out.println(getFileName);
    }

}
