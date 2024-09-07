package transacoes.financeiras.producer;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class LeitorArquivo {
	
	private static final Logger logger = Logger.getLogger(LeitorArquivo.class.getName());

	
	public List<Transacao> lerArquivo() {
	    List<Transacao> transacoes = new ArrayList<>();
	    try {
	        Reader leitorArquivo = new FileReader("transacoes.csv");
	        CSVFormat configCSV = CSVFormat.Builder.create().setHeader("codigo", "cedente", "pagador", "valor", "vencimento").build();
	        CSVParser interpretadorCSV = configCSV.parse(leitorArquivo);
	        List<CSVRecord> records = interpretadorCSV.getRecords();
	        
	        boolean isHeader = true;

	        for (CSVRecord record : records) {
	            if (isHeader) {
	                isHeader = false;
	                continue;
	            }
	            
	            String codigo = record.get("codigo");
	            String cedente = record.get("cedente");
	            String pagador = record.get("pagador");
	            String valorStr = record.get("valor");
	            String vencimento = record.get("vencimento");
	            
	            Double valor = null;
	            try {
	                valor = Double.valueOf(valorStr);
	            } catch (NumberFormatException e) {
	            	logger.log(Level.SEVERE, "Valor inválido encontrado: {0}", valorStr);
	                continue; 
	            }
	            
	            Transacao t = new Transacao(codigo, cedente, pagador, valor, vencimento);
	            transacoes.add(t);
	        }
	        
	    } catch (IOException e) {
	        logger.log(Level.SEVERE, "Erro ao abrir o arquivo CSV: {0}",  e.getMessage());
	    }
	    return transacoes;
	}

	
}
