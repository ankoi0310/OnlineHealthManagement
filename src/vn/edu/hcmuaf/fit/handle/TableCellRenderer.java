package vn.edu.hcmuaf.fit.handle;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class TableCellRenderer extends DefaultTableCellRenderer {
    public TableCellRenderer(int alignment, Font font) {
        setHorizontalAlignment(alignment);
        setFont(font);
        setHorizontalAlignment(SwingConstants.CENTER);
        setBorder(new CompoundBorder(new EmptyBorder(new Insets(1,4,1,4)), getBorder()));
    }
}
