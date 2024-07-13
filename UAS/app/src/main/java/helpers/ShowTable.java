package helpers;

import entities.Book;
import entities.Member;
import entities.Transaction;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableColumnModel;

public class ShowTable<AllType> {

    public ShowTable(JTable table, List<AllType> dataList, String[] header, boolean editableColumns) {
        CustomTableModel tableModel = new CustomTableModel(new Object[][]{}, header, editableColumns);

        tableModel.setRowCount(0);

        for (AllType data : dataList) {
            Object[] rowData = DataConversion(data);

            tableModel.addRow(rowData);
        }

        int rowsToAdd = Math.max(20 - dataList.size(), 0);
        for (int i = 0; i < rowsToAdd; i++) {
            tableModel.addRow(new Object[header.length]);
        }

        table.setModel(tableModel);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(30);
        columnModel.getColumn(0).setMinWidth(30);
        columnModel.getColumn(0).setMaxWidth(40);
    }

    private Object[] DataConversion(AllType data) {
        if (data instanceof Book book) {
            return new Object[]{
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getYear(),
                book.getIsAvailable()};
        } else if (data instanceof Member member) {
            return new Object[]{
                member.getId(),
                member.getName(),
                member.getAddress(),
                member.getPhoneNumber(),
                member.getEmail()};
        } else if (data instanceof Transaction transaction) {
            return new Object[]{
                transaction.getId(),
                transaction.getBookId(),
                transaction.getMemberId(),
                transaction.getBorrowDate(),
                transaction.getDueDate(),
                transaction.getReturnDate(),
                transaction.getFine()};
        }

        return new Object[0];
    }
}
