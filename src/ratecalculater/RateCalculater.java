/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ratecalculater;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RateCalculater {
    public static void main(String[] args) {
        // إعداد النافذة
        JFrame frame = new JFrame("حساب معدل التقييم - كلية هندسة وعلوم الحاسب");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // عناصر الواجهة
        JLabel titleLabel = new JLabel("حساب معدل التقييم (RATE)");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.BLUE);

        JLabel gpaLabel = new JLabel("المعدل التراكمي (GPA):");
        JTextField gpaField = new JTextField(10);

        JLabel saLabel = new JLabel("المعدل الفصلي (SA):");
        JTextField saField = new JTextField(10);

        JLabel logicDesignLabel = new JLabel("التصميم المنطقي:");
        JComboBox<String> logicDesignCombo = new JComboBox<>(new String[]{"اختر الدرجة", "أ+", "أ", "ب+", "ب", "ج+", "ج", "د+", "د", "هـ"});

        JLabel discreteMathLabel = new JLabel("الرياضيات المتقطعة:");
        JComboBox<String> discreteMathCombo = new JComboBox<>(new String[]{"اختر الدرجة", "أ+", "أ", "ب+", "ب", "ج+", "ج", "د+", "د", "هـ"});

        JLabel programmingLabel = new JLabel("برمجة الحاسب:");
        JComboBox<String> programmingCombo = new JComboBox<>(new String[]{"اختر الدرجة", "أ+", "أ", "ب+", "ب", "ج+", "ج", "د+", "د", "هـ"});

        JButton calculateButton = new JButton("حساب");
        JTextArea resultsArea = new JTextArea(5, 30);
        resultsArea.setEditable(false);

        // إضافة العناصر إلى النافذة
        frame.add(titleLabel);
        frame.add(gpaLabel);
        frame.add(gpaField);
        frame.add(saLabel);
        frame.add(saField);
        frame.add(logicDesignLabel);
        frame.add(logicDesignCombo);
        frame.add(discreteMathLabel);
        frame.add(discreteMathCombo);
        frame.add(programmingLabel);
        frame.add(programmingCombo);
        frame.add(calculateButton);
        frame.add(new JScrollPane(resultsArea));

        // حدث زر الحساب
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // قراءة المدخلات
                    double gpa = Double.parseDouble(gpaField.getText());
                    double sa = Double.parseDouble(saField.getText());
                    double logicDesign = getGrade(logicDesignCombo);
                    double discreteMath = getGrade(discreteMathCombo);
                    double programming = getGrade(programmingCombo);

                    // تحقق من المدخلات
                    if (gpa < 0 || gpa > 5 || sa < 0 || sa > 5 || logicDesign == -1 || discreteMath == -1 || programming == -1) {
                        JOptionPane.showMessageDialog(frame, "يرجى إدخال جميع القيم بشكل صحيح.");
                        return;
                    }

                    // أوزان التخصصات
                    double rateLogicDesign = (gpa * sa + logicDesign * 0.75 + discreteMath * 0.25) / 3;
                    double rateDiscreteMath = (gpa * sa + programming * 0.75 + discreteMath * 0.25) / 3;
                    double rateProgramming = (gpa * sa + programming * 0.75 + logicDesign * 0.25) / 3;
                    double rateSoftwareEngineering = (gpa * sa + programming * 0.5 + logicDesign * 0.5) / 3;

                    // عرض النتائج
                    resultsArea.setText("");
                    resultsArea.append("هندسة الحاسب: " + String.format("%.3f", rateLogicDesign) + "\n");
                    resultsArea.append("علوم الحاسب: " + String.format("%.3f", rateDiscreteMath) + "\n");
                    resultsArea.append("نظم المعلومات: " + String.format("%.3f", rateProgramming) + "\n");
                    resultsArea.append("هندسة البرمجيات: " + String.format("%.3f", rateSoftwareEngineering) + "\n");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "يرجى إدخال القيم بشكل صحيح.");
                }
            }
        });

        // جعل النافذة مرئية
        frame.setVisible(true);
    }

    // تحويل الدرجات النصية إلى القيم الرقمية
    private static double getGrade(JComboBox<String> comboBox) {
        String grade = (String) comboBox.getSelectedItem();
        switch (grade) {
            case "أ+":
                return 5;
            case "أ":
                return 4.75;
            case "ب+":
                return 4.5;
            case "ب":
                return 4;
            case "ج+":
                return 3.5;
            case "ج":
                return 3;
            case "د+":
                return 2.5;
            case "د":
                return 2;
            case "هـ":
                return 0;
            default:
                return -1; // خطأ في اختيار الدرجة
        }
    }}