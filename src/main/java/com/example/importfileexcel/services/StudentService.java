package com.example.importfileexcel.services;

import com.example.importfileexcel.models.Student;
import com.example.importfileexcel.repositories.StudentRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public void importExcelFile(MultipartFile files) throws IOException, ParseException {

        List<Student> students = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(files.getInputStream());
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        DataFormatter dataFormatter = new DataFormatter(new java.util.Locale("en", "US"));
        // Read student data form excel file sheet1.
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int index = 5; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 0) {
                XSSFRow row = worksheet.getRow(index);
                Student student = new Student();
                student.setStudentCode(getCellValue(row, 3));
                student.setClassName(getCellValue(row, 4));
                student.setFullName(getCellValue(row, 5));
                SimpleDateFormat DateFor = new SimpleDateFormat("yyyy/MM/dd");
                Date birthday = DateFor.parse(getCellValue(row, 8) + "/" + getCellValue(row, 7) + "/" + getCellValue(row, 6));
                student.setBirthday(birthday);
                student.setSex(getCellValue(row, 9));
                student.setPlaceOfBirth(getCellValue(row, 10));
                student.setNation(getCellValue(row, 11));
                student.setHousehold(getCellValue(row, 12));
                student.setPhone(getCellValue(row, 13));
                student.setTotalPointFirstGrade(getCellValue(row, 14));
                student.setTotalPointSecondGrade(getCellValue(row, 15));
                student.setTotalPointThirdGrade(getCellValue(row, 16));
                student.setTotalPointFourthGrade(getCellValue(row, 17));
                student.setTotalPointFifthGrade(getCellValue(row, 18));
                student.setTotalPointGrade(dataFormatter.formatCellValue(row.getCell(19), evaluator));
                student.setPriorityPoint(getCellValue(row, 20));
                student.setTotalPoint(dataFormatter.formatCellValue(row.getCell(21), evaluator));
                student.setNote(getCellValue(row, 22));
                students.add(student);
            }
        }

        studentRepository.saveAll(students);
    }


    private int convertStringToInt(String str) {
        int result = 0;
        if (str == null || str.isEmpty() || str.trim().isEmpty()) {
            return result;
        }
        result = Integer.parseInt(str);
        return result;
    }

    private String getCellValue(Row row, int cellNo) {
        DataFormatter formatter = new DataFormatter();
        Cell cell = row.getCell(cellNo);
        return formatter.formatCellValue(cell);
    }

    public List<Student> getStudents() {
        List<Student> result = studentRepository.findAll();
        return result;
    }

    public List<Student> searchStudentBy(String studentCode, String fullName) {
        studentCode = studentCode.replace(" ", "%");
        List<Student> result;

        if (studentCode.equals("")) {
            result = studentRepository.findByFullName(fullName);
        } else {
            if (studentRepository.findByStudentCode(studentCode).isEmpty()) {
                result = studentRepository.findByFullName(fullName);
            } else {
                result = studentRepository.findByStudentCode(studentCode);
            }
        }
        return result;

    }

}
