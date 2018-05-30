package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.UploadCsv;
import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.service.RelatorioGestaoVendaService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class CsvFileDownloadController {

    @Autowired
    private RelatorioGestaoVendaService relatorioGestaoVendaService;

    @RequestMapping(value = "/downloadModelo", method = {RequestMethod.GET})
    public void downloadModelo(HttpSession session, HttpServletResponse response) throws IOException {

        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

        byte[] file = relatorioGestaoVendaService.gerarCSV(usuario.getDocumento());

        response.setContentType(String.valueOf(MediaType.TEXT_PLAIN_VALUE));

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", "relatorio-gestao-vendas.csv");
        response.setHeader(headerKey, headerValue);
        response.getWriter().write(new String(file, "UTF-8"));
        response.getWriter().flush();

        //return "redirect:" + "/corretora/home";
    }


    /*
    @RequestMapping(value = "/downloadXLS", method = {RequestMethod.GET})
    @ResponseBody
    public void downloadXLS(HttpServletResponse response) throws IOException {

        response.setContentType("application/vnd.ms-excel");
        String fileName = "modelo.xls";
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

        String[] col = {"NOME", "CPF", "DATANASCIMENTO", "CELULAR", "EMAIL", "DEPARTAMENTO", "CARGO"};
        List<UploadCsv> model = new ArrayList<>();

        model.add(new UploadCsv("NOME\t", "CPF\t", "DATANASCIMENTO\t", "CELULAR\t", "EMAIL\t", "DEPARTAMENTO\t", "CARGO"));

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Modelo");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < col.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(col[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;

        Row row = sheet.createRow(rowNum++);
        for (UploadCsv csv : model) {
            row.createCell(0).setCellValue(csv.getNome());
            row.createCell(1).setCellValue(csv.getCpf());
            row.createCell(2).setCellValue(csv.getDataNascimento());
            row.createCell(3).setCellValue(csv.getCelular());
            row.createCell(4).setCellValue(csv.getEmail());
            row.createCell(5).setCellValue(csv.getDepartamento());
            row.createCell(6).setCellValue(csv.getCargo());
        }

        for (int i = 0; i < col.length; i++) {
            sheet.autoSizeColumn(i);
        }

        Iterator<Cell> iter = row.iterator();
        while (iter.hasNext()) {
            String outputString = String.valueOf(iter.next());
            response.getOutputStream().print(outputString);
        }
    }
    */

    @RequestMapping(value = "/downloadCSV")
    public void downloadCSV(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        String reportName = "modelo.csv";
        response.setHeader("Content-disposition", "attachment;filename=" + reportName);

        ArrayList<String> rows = new ArrayList<String>();
        rows.add("NOME,CPF,DATANASCIMENTO,CELULAR,EMAIL,DEPARTAMENTO,CARGO");
        rows.add("\n");

        for (int i = 0; i < 10; i++) {
            rows.add("Jack Sparrow,2365428549,06/07/1987,11987654321,jacksparrow@pirata.com,7 Mares,Capitão");
            rows.add("\n");
        }

        Iterator<String> iter = rows.iterator();
        while (iter.hasNext()) {
            String outputString = iter.next();
            response.getOutputStream().print(outputString);
        }

        response.getOutputStream().flush();
    }
}