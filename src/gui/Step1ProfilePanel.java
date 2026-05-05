package gui;

import model.AppSession;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class Step1ProfilePanel extends JPanel {

    private JTextField txtUsername;
    private JTextField txtSchool;
    private JTextField txtSession;
    private JButton btnNext;

    private final AppSession session;
    private final MainFrame mainFrame;

    public Step1ProfilePanel(AppSession session, MainFrame mainFrame) {
        this.session   = session;
        this.mainFrame = mainFrame;
        buildUI();
    }

    private void buildUI() {
        setLayout(new BorderLayout());
        setBackground(new Color(24, 24, 37));

        JLabel title = new JLabel("Step 1 — User Profile", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(new Color(99, 102, 241));
        title.setBorder(new EmptyBorder(30, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(new Color(30, 30, 46));
        form.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(60, 60, 80), 1),
            new EmptyBorder(30, 40, 30, 40)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets  = new Insets(10, 10, 10, 10);
        gbc.fill    = GridBagConstraints.HORIZONTAL;
        gbc.anchor  = GridBagConstraints.WEST;

        txtUsername = addField(form, gbc, 0, "Username", "Enter your username");
        txtSchool   = addField(form, gbc, 1, "School",   "Enter your school name");
        txtSession  = addField(form, gbc, 2, "Session Name", "Enter a session name");

        btnNext = new JButton("Next →");
        styleButton(btnNext, new Color(99, 102, 241));
        btnNext.addActionListener(e -> onNext());

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill   = GridBagConstraints.NONE;
        form.add(btnNext, gbc);

        JPanel center = new JPanel(new GridBagLayout());
        center.setBackground(new Color(24, 24, 37));
        center.add(form);
        add(center, BorderLayout.CENTER);
    }

    private JTextField addField(JPanel panel, GridBagConstraints gbc,
                                 int row, String label, String placeholder) {
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 1; gbc.weightx = 0;
        JLabel lbl = new JLabel(label + ":");
        lbl.setForeground(new Color(200, 200, 220));
        lbl.setFont(new Font("SansSerif", Font.BOLD, 13));
        panel.add(lbl, gbc);

        JTextField field = new JTextField(22);
        field.setBackground(new Color(45, 45, 65));
        field.setForeground(Color.WHITE);
        field.setCaretColor(Color.WHITE);
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(80, 80, 110), 1),
            new EmptyBorder(6, 8, 6, 8)
        ));
        field.setFont(new Font("SansSerif", Font.PLAIN, 13));

        field.setText(placeholder);
        field.setForeground(new Color(120, 120, 150));
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.WHITE);
                }
            }
            public void focusLost(java.awt.event.FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(new Color(120, 120, 150));
                }
            }
        });

        gbc.gridx = 1; gbc.weightx = 1.0;
        panel.add(field, gbc);
        return field;
    }

    private void onNext() {
        String username = getRealText(txtUsername, "Enter your username");
        String school   = getRealText(txtSchool,   "Enter your school name");
        String sName    = getRealText(txtSession,  "Enter a session name");

        if (username.isEmpty()) {
            showWarning("Please enter your username to continue.");
            txtUsername.requestFocus();
            return;
        }
        if (school.isEmpty()) {
            showWarning("Please enter your school name to continue.");
            txtSchool.requestFocus();
            return;
        }
        if (sName.isEmpty()) {
            showWarning("Please enter a session name to continue.");
            txtSession.requestFocus();
            return;
        }

        session.getProfile().setUsername(username);
        session.getProfile().setSchool(school);
        session.getProfile().setSessionName(sName);

        mainFrame.goToStep(2);
    }


    private String getRealText(JTextField field, String placeholder) {
        String t = field.getText().trim();
        return t.equals(placeholder) ? "" : t;
    }

    private void showWarning(String msg) {
        JOptionPane.showMessageDialog(this, msg, "Missing Information",
                JOptionPane.WARNING_MESSAGE);
    }

    private void styleButton(JButton btn, Color bg) {
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(10, 30, 10, 30));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
