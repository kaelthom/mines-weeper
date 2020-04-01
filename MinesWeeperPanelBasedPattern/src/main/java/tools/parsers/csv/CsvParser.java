package tools.parsers.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tools.parsers.AbstractGenericParser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvParser<T> extends AbstractGenericParser<List<T>, List<String>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvParser.class);
    private String separator;

    private List<Field> fields;

    private Class<T> objClass;

    public CsvParser(Class<T> objClass, String separator) throws Exception {
        super();
        this.objClass = objClass;
        this.separator = separator;
        this.fields = Arrays.asList(objClass.getFields());
        this.fields.sort((field1, field2) -> {
            int f1Column = field1.getAnnotation(CsvColumn.class).column();
            int f2Column = field2.getAnnotation(CsvColumn.class).column();
            return Integer.compare(f1Column, f2Column);
        });
        LOGGER.info("fields size of class {} : {}", objClass.getName(), fields.size());
        if (!isClassCompatible())
            throw new Exception();
    }

    public List<T> parse(File csvFile) {
        List<T> listObj = new ArrayList<>();

        if (isFileCompatible()) {

            String fileName = csvFile.getPath();
            List<String> lines;

            // read file into stream, try-with-resources
            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                lines = stream.collect(Collectors.toList());
                listObj = parse(lines);
            } catch (IOException | SecurityException | InstantiationException | IllegalAccessException e) {
                LOGGER.error("Error while parsing CSV file", e);
            }
        }
        return listObj;
    }

    @Override
    public List<T> parse(List<String> lines) throws InstantiationException, IllegalAccessException {
        LOGGER.info("lines : {}", lines);
        List<T> listObj = new ArrayList<>();
        for (String line : lines) {
            T obj = objClass.newInstance();
            String[] propertiesString = line.split(this.separator);
            for (int iField = 0; iField < fields.size(); iField++) {
                Field field = fields.get(iField);
                CsvColumn csvColumnAnnotation = field.getAnnotation(CsvColumn.class);
                if (csvColumnAnnotation != null) {
                    int column = field.getAnnotation(CsvColumn.class).column();
                    LOGGER.info("column for {} : {}", field, column);
                    LOGGER.info("value : {}", propertiesString[column]);
                    Class<?> type = field.getType();
                    LOGGER.info("type : {}", type);
                    if (column >= 0 && column < fields.size() && column < propertiesString.length) {
                        if (type.toString().equals("int")) {
                            field.setInt(obj, Integer.parseInt(propertiesString[column]));
                        } else if (type.toString().equals("long")) {
                            field.setLong(obj, Long.parseLong(propertiesString[column]));
                        } else if (type.equals(Long.class)) {
                            field.set(obj, Long.valueOf(propertiesString[column]));
                        } else if (type.equals(Integer.class)) {
                            field.set(obj, Integer.valueOf(propertiesString[column]));
                        } else if (type.equals(String.class)) {
                            field.set(obj, propertiesString[column]);
                        } else {
                            LOGGER.info("type not supported");
                        }
                    }
                }
            }
            listObj.add(obj);
        }

        return listObj;
    }

    private boolean isFileCompatible() {
        // TODO Auto-generated method stub
        return true;
    }

    private boolean isClassCompatible() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public List<String> unParse(List<T> objs) throws IllegalArgumentException, IllegalAccessException {
        List<String> lines = new ArrayList<>();
        for (T obj : objs) {
            StringBuilder sb = new StringBuilder();
            for (Field field : fields) {
                String stringValue = "";
                Class<?> type = field.getType();
                if (type.toString().equals("int")) {
                    stringValue = Integer.toString(field.getInt(obj));
                } else if (type.toString().equals("long")) {
                    stringValue = Long.toString(field.getLong(obj));
                } else if (type.equals(Long.class)) {
                    stringValue = Long.toString((Long) field.get(obj));
                } else if (type.equals(Integer.class)) {
                    stringValue = Integer.toString((Integer) field.get(obj));
                } else if (type.equals(String.class)) {
                    stringValue = (String) field.get(obj);
                } else {
                    LOGGER.info("type not supported");
                }
                sb.append(stringValue);
                sb.append(this.separator);
            }
            String line = sb.toString();
            LOGGER.info("line: {}", line);
            lines.add(line);
        }
        return lines;
    }

    public File unParse(List<T> objs, String filePath) {
        File file = new File(filePath);
        Path outputPath = null;
        try {
            List<String> lines = unParse(objs);
            outputPath = Files.write(file.toPath(), lines);
        } catch (IllegalArgumentException | IllegalAccessException | IOException e) {
            LOGGER.error("Error while unparsing file", e);
        }

        return outputPath == null ? null : outputPath.toFile();
    }

}
