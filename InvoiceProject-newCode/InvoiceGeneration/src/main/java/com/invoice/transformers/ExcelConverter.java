package com.invoice.transformers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.invoice.request.CustomerRequest;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class ExcelConverter {

	public List<CustomerRequest> exceltoUserModelList(InputStream excelFileInputStream) {

		List<CustomerRequest> userRequestList = new ArrayList<>();
		
		List<CustomerRequest> duplicates = new ArrayList<>();

		try (XSSFWorkbook workbook = new XSSFWorkbook(excelFileInputStream)) {

			XSSFSheet sheet = workbook.getSheet("sheet1");

			log.info("last cell number{}", sheet.getLastRowNum());
			Iterator<Row> rowIterator = sheet.rowIterator();

			int rowNumber = 0;

			while (rowIterator.hasNext()) {

				Row row = rowIterator.next();
				log.info("last rowNO number{}", row.getLastCellNum());

				// Code to check for empty row;

				Iterator<Cell> cellCheck = row.cellIterator();

				Boolean isRowEmpty = true;

				while (cellCheck.hasNext()) {
					Cell flagCell = cellCheck.next();
					if (flagCell.getCellType() != CellType.BLANK) {
						isRowEmpty = false;
						break;
					}
				}

				if (isRowEmpty)
					continue;

				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				// Running loop till we get the end flag in excel sheet

				if ("end".equals(row.getCell(1).getStringCellValue())) {
					break;
				}

				Iterator<Cell> cellIterator = row.cellIterator();
				Integer cellId = 0;

				CustomerRequest userRequest = new CustomerRequest();
				
				Long id =null;
			
				
				Long customerId = null;
				String firstName = null;
				String lastName = null;
				String customerEmail = null;
				String customerPhoneNumber = null;
				String city = null;
				String country = null;
				
				
				
				
				

				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();

					switch (cellId) {

					case 0: {

						firstName = cell.getStringCellValue();

						break;

					}
					case 1: {
						
						lastName=cell.getStringCellValue();
						log.info("executed");
						break;
						
 
					}
					case 2: {

						customerEmail = cell.getStringCellValue();
						log.info("executed");
						break;
					}
					case 3: {

						customerPhoneNumber = cell.getStringCellValue();
						log.info("executed");
						break;
					}
					case 4: {
						customerId = (long) cell.getNumericCellValue();
						log.info("executed");
						break;

					}

					case 5: {

						city = cell.getStringCellValue();
						log.info("executed");
						break;

					}
					case 6: {

						country = cell.getStringCellValue();
						log.info("executed");
						break;

					}
//					
//					case 7: {
//						inFileCompanyId = (long) cell.getNumericCellValue();
//						try {
//                            CompanyModel companyModel = companyRepository.findById(inFileCompanyId).get();
//							userRequest.setCompany(companyModel);
//							log.info("Added company id");
//
//						} catch (Exception e) {
//							userRequest = null;
//							log.error("Company of id {} doesnt exist in the CompanyModel", inFileCompanyId);
//						}
//						break;
//					}

					default: {
						break;
					}

					}
					cellId++;

				}

				if (customerId != null &&firstName != null && lastName != null && customerEmail != null && customerPhoneNumber != null && city != null && country != null) {
					
					userRequest.setCustomerId(customerId);
					userRequest.setFirstName(firstName);
					userRequest.setLastName(lastName);
					userRequest.setCustomerEmail(customerEmail);
					userRequest.setCustomerPhoneNumber(customerPhoneNumber);
					userRequest.setCity(city);
					userRequest.setCountry(country);
					
					userRequestList.add(userRequest);

				}
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userRequestList;

	}
	
}
