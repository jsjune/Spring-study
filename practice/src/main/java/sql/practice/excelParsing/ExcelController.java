package sql.practice.excelParsing;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

@RestController
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelRepository excelRepository;
    private final FileEntityRepository fileEntityRepository;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//    @Value("${file.dir}")
    private String fileDir = System.getProperty("user.dir")+"\\file\\";

    @PostMapping("/upload/excel")
    public String uploadExcel(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        String origName = multipartFile.getOriginalFilename();
        String uuid = UUID.randomUUID().toString();
        String extension2 = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        String savedName = uuid + "."+extension2;
        String savePath = fileDir + savedName;
        FileEntity fileEntity = FileEntity.builder()
                .orgNm(origName)
                .savedNm(savedName)
                .savedPath(savePath)
                .build();
        fileEntityRepository.save(fileEntity);
        List<ExcelData> dataList = new ArrayList<>();
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요");
        }
        InputStream fileInputStream = multipartFile.getInputStream();
        multipartFile.transferTo(new File(savePath));
        Workbook workbook = null;
        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(fileInputStream);
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(fileInputStream);
        }

        Sheet worksheet = workbook.getSheetAt(0);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
//            int num = (int) row.getCell(0).getNumericCellValue();
            String name = row.getCell(1).getStringCellValue();
            int age = (int) row.getCell(2).getNumericCellValue();
            ExcelData data = ExcelData.builder()
//                .num(num)
                .age(age)
                .name(name)
                .build();
            dataList.add(data);

        }
        List<ExcelEntity> excelData = dataList.stream().map(ExcelEntity::new).collect(
            Collectors.toList());

        String sql = String.format("""
                INSERT INTO `%s` (age, name, id)
                VALUES (:age, :name, :id)
                """, "excel_entity");
        SqlParameterSource[] params = excelData.stream().map(BeanPropertySqlParameterSource::new)
            .toArray(SqlParameterSource[]::new);
        namedParameterJdbcTemplate.batchUpdate(sql, params);
        File file = new File(fileDir);
        if (!file.exists()) {
            try {
                file.mkdirs();
                System.out.println("폴더가 생성되었습니다.");
            } catch (Exception e) {
                e.getStackTrace();
            }
        } else {
            System.out.println("이미 폴더가 생성되어 있습니다.");
        }
//        excelRepository.saveAll(excelData);
        return "success";
    }

    @GetMapping("/download/excel/{id}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long id) throws MalformedURLException {

        FileEntity file = fileEntityRepository.findById(id).orElse(null);

        UrlResource resource = new UrlResource("file:" + file.getSavedPath());

        String encodedFileName = UriUtils.encode(file.getOrgNm(), StandardCharsets.UTF_8);

        // 파일 다운로드 대화상자가 뜨도록 하는 헤더를 설정해주는 것
        // Content-Disposition 헤더에 attachment; filename="업로드 파일명" 값을 준다.
        String contentDisposition = "attachment; filename=\"" + encodedFileName + "\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,contentDisposition).body(resource);
    }

    @Getter
    @Setter
    public static class ExcelData {
        private int num;
        private String name;
        private int age;

        @Builder
        public ExcelData(int num, String name, int age) {
            this.num = num;
            this.name = name;
            this.age = age;
        }
    }

}
