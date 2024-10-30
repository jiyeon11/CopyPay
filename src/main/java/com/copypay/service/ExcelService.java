package com.copypay.service;

import com.copypay.dto.response.ContractProgressListResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.io.ByteArrayInputStream;

@Service
@RequiredArgsConstructor
public class ExcelService {
    public ByteArrayInputStream generateExcel(List<ContractProgressListResponse> contractProgressList) {
        try (Workbook workbook = new XSSFWorkbook()) { // workbook(엑셀 파일) 생성
            Sheet sheet = workbook.createSheet("Contract Progress"); // 시트 생성, 시트명 지정
            Row headerRow = sheet.createRow(0);

            // 엑셀 파일 헤더 생성
            String[] headers = {"계약일자", "사업자번호", "상호", "계약담당자", "영업담당자"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // 데이터가 있을 때만 추가....
            if (!contractProgressList.isEmpty()) {
                int rowIndex = 1;
                for (ContractProgressListResponse contract : contractProgressList) {
                    Row row = sheet.createRow(rowIndex++);
                    row.createCell(0).setCellValue(contract.getContractDate());
                    row.createCell(1).setCellValue(contract.getBusinessRegNumber());
                    row.createCell(2).setCellValue(contract.getBusinessName());
                    row.createCell(3).setCellValue(contract.getContractManager());
                    row.createCell(4).setCellValue(contract.getSalesManager());
                }
            }

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                workbook.write(out);
                return new ByteArrayInputStream(out.toByteArray());
            }
        } catch (IOException e) {
            throw new RuntimeException("파일 생성 실패", e);
        }
    }


}
