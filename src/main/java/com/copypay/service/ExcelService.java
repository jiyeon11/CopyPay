package com.copypay.service;

import com.copypay.dto.response.ContractProgressListResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
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
                CellStyle cellStyle = createHeaderStyle(workbook);
                sheet.setColumnWidth(i, 6000); // 셀 가로폭 지정
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
                cell.setCellStyle(cellStyle);
            }

            // 데이터가 있을 때만 추가
            if (!contractProgressList.isEmpty()) {
                CellStyle cellStyle = createStyle(workbook);
                int rowIndex = 1;
                for (ContractProgressListResponse contract : contractProgressList) {
                    Row row = sheet.createRow(rowIndex++);
                    Cell dateCell = row.createCell(0);
                    dateCell.setCellValue(contract.getContractDate());
                    dateCell.setCellStyle(cellStyle);
                    Cell businessRegNumber = row.createCell(1);
                    businessRegNumber.setCellValue(contract.getBusinessRegNumber());
                    businessRegNumber.setCellStyle(cellStyle);
                    Cell businessName = row.createCell(2);
                    businessName.setCellValue(contract.getBusinessName());
                    businessName.setCellStyle(cellStyle);
                    Cell contractManager = row.createCell(3);
                    contractManager.setCellValue(contract.getContractManager());
                    contractManager.setCellStyle(cellStyle);
                    Cell salesManager = row.createCell(4);
                    salesManager.setCellValue(contract.getSalesManager());
                    salesManager.setCellStyle(cellStyle);
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

    // 헤더 스타일 지정
    private CellStyle createHeaderStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        return cellStyle;
    }

    // 데이터 목록 스타일 지정
    private CellStyle createStyle(Workbook workbook) {
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        return cellStyle;
    }

}
