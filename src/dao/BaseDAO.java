package dao;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

public class BaseDAO {
	
	protected static Path diretorio = Paths.get("bd");
	
	protected static Stream<String> lerCsv(Path path) throws IOException {
		if (!Files.exists(path)) {
            return null;
        }
		return Files.lines(path);
	}
	
	protected static void incluirNoCsv(Path path, String csv) {
		if (Files.notExists(diretorio)) {
			try {
				Files.createDirectories(diretorio);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE,
				StandardOpenOption.APPEND)) {
			writer.write(csv);
			writer.newLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected static void escreverNoCsv(Path path, String csv) {
		if (Files.notExists(diretorio)) {
			try {
				Files.createDirectories(diretorio);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.write(csv);
			writer.newLine();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected static String escapeCsv(String texto) {
		if (texto == null) {
			return "";
		}
		if (texto.contains(",") || texto.contains("\"") || texto.contains("\n") || texto.contains("\r")) {
			return "\"" + texto.replace("\"", "\"\"") + "\"";
		}
		return texto;
	}
	
	protected static String unquote(String texto) {
        if (texto == null) return "";
        texto = texto.trim();
        if (texto.startsWith("\"") && texto.endsWith("\"")) {
            texto = texto.substring(1, texto.length() - 1).replace("\"\"", "\"");
        }
        return texto;
    }

}
