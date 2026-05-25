package controller;


import javax.swing.JTable;
import java.util.ArrayList;
import java.util.List;

public class JTableXmlExporter {

    /**
     * Экспортирует все строки JTable в XML
     */
    public static void exportAll(JTable table, String rootName,
                                 String tagName, String filePath) {

        List<XmlExportable> items = new ArrayList<>();

        for (int row = 0; row < table.getRowCount(); row++) {
            items.add(new JTableRowAdapter(table, row, tagName));
        }

        SaveXML.export(items, rootName, filePath);
    }

    /**
     * Экспортирует только выбранные строки JTable в XML
     */
    public static void exportSelected(JTable table, String rootName,
                                      String tagName, String filePath) {

        List<XmlExportable> items = new ArrayList<>();
        int[] selectedRows = table.getSelectedRows();

        for (int row : selectedRows) {
            items.add(new JTableRowAdapter(table, row, tagName));
        }

        if (items.isEmpty()) {
            System.out.println("Не выбрано ни одной строки для экспорта");
            return;
        }

        SaveXML.export(items, rootName, filePath);
    }
}