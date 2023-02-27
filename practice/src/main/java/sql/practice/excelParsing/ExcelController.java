package sql.practice.excelParsing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelRepository excelRepository;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @PostMapping("/upload/excel")
    public String uploadExcel(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<ExcelData> dataList = new ArrayList<>();
        String extension = FilenameUtils.getExtension(multipartFile.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요");
        }
        Workbook workbook = null;
        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(multipartFile.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(multipartFile.getInputStream());
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
//        excelRepository.saveAll(excelData);
        return "success";
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
