package controller;

import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class JTableRowAdapter implements XmlExportable{

    private JTable table;
    private int rowIndex;
    private String tagName;

    /**
     * @param table    JTable с данными
     * @param rowIndex индекс строки для экспорта
     * @param tagName  название XML-тега (student, teacher, course)
     */
    public JTableRowAdapter(JTable table, int rowIndex, String tagName) {
        this.table = table;
        this.rowIndex = rowIndex;
        this.tagName = tagName;
    }

    @Override
    public String getXmlTagName() {
        return tagName;
    }

    @Override
    public Map<String, String> getXmlAttributes() {
      return new LinkedHashMap<>();
    }

    @Override
    public Map<String, String> getXmlElements() {
        Map<String, String> elements = new LinkedHashMap<>();

        // Получаем имена колонок и значения
        for (int col = 0; col < table.getColumnCount(); col++) {
            String columnName = table.getColumnName(col);
            Object value = table.getValueAt(rowIndex, col);

            // Пропускаем ID-колонку, если она уже добавлена как атрибут
            if (col == 0 && columnName.equalsIgnoreCase("id")) {
                continue;
            }

            elements.put(columnName, value != null ? value.toString() : "");
        }

        return elements;
    }
}
