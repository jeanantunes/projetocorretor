package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.service.RelatorioGestaoVendaService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

@Controller
public class CsvFileDownloadController {

    @Autowired
    private RelatorioGestaoVendaService relatorioGestaoVendaService;

    @RequestMapping(value = "/downloadPME", method = {RequestMethod.GET})
    public void downloadPME(HttpSession session, HttpServletResponse response) throws IOException {

        String dataInicio = "0";
        String dataFim = "0";
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

        byte[] file = relatorioGestaoVendaService.gerarCsvPME(dataInicio, dataFim, usuario.getDocumento());

        response.setContentType(String.valueOf(MediaType.TEXT_PLAIN_VALUE));

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", "relatorio-pme.xls");
        response.setHeader(headerKey, headerValue);
        response.getWriter().write(new String(file, "UTF-8"));
        response.getWriter().flush();
    }

    /*
    @RequestMapping(value = "/downloadPF", method = {RequestMethod.GET})
    public void downloadPF(HttpSession session, HttpServletResponse response) throws IOException {

        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

        byte[] file = relatorioGestaoVendaService.gerarCsvPF(usuario.getDocumento());

        response.setContentType(String.valueOf(MediaType.TEXT_PLAIN_VALUE));

        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", "relatorio-pf.xls");
        response.setHeader(headerKey, headerValue);
        response.getWriter().write(new String(file, "UTF-8"));
        response.getWriter().flush();
    }
    */

    @RequestMapping(value = "/downloadXLS", method = {RequestMethod.GET})
    @ResponseBody
    public void downloadXLS(HttpServletResponse response) throws IOException {

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();

        HSSFRow row = sheet.createRow(0);
        HSSFCell cellNome = row.createCell(0);
        cellNome.setCellValue("NOME");

        HSSFCell cellCpf = row.createCell(1);
        cellCpf.setCellValue("CPF");

        HSSFCell cellDataNasc = row.createCell(2);
        cellDataNasc.setCellValue("DATA NASCIMENTO");

        HSSFCell cellCelular = row.createCell(3);
        cellCelular.setCellValue("CELULAR");

        HSSFCell cellEmail = row.createCell(4);
        cellEmail.setCellValue("E-MAIL");

        HSSFCell cellDpto = row.createCell(5);
        cellDpto.setCellValue("DEPARTAMENTO");

        HSSFCell cellCargo = row.createCell(6);
        cellCargo.setCellValue("CARGO");

        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        workbook.write(outByteStream);
        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0");
        response.setHeader("Content-Disposition", "attachment; filename=relatorio-modelo.xls");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(outArray);
        outputStream.flush();
    }


    @RequestMapping(value = "/downloadCSV")
    public void downloadCSV(HttpServletResponse response) throws IOException {

        response.setContentType("text/csv");
        String reportName = "modelo.csv";
        response.setHeader("Content-disposition", "attachment;filename=" + reportName);

        ArrayList<String> rows = new ArrayList<String>();
        rows.add("NOME;CPF;DATANASCIMENTO;CELULAR;EMAIL;DEPARTAMENTO;CARGO");
        rows.add("\n");

        for (int i = 0; i < 10; i++) {
            rows.add("Jack Sparrow;2365428549;06/07/1987;11987654321;jacksparrow@pirata.com;7 Mares;Capitao");
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