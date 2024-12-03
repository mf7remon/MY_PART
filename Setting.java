private JPanel createSettingsPanel() {
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBackground(new Color(249, 250, 251));

    // Title Panel
    JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    titlePanel.setOpaque(false);
    JLabel titleLabel = new JLabel("App Settings", SwingConstants.LEFT);
    titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
    titleLabel.setForeground(new Color(17, 24, 39));
    titlePanel.add(titleLabel);

    // Settings Categories Panel
    JPanel settingsCategoriesPanel = new JPanel();
    settingsCategoriesPanel.setLayout(new BoxLayout(settingsCategoriesPanel, BoxLayout.Y_AXIS));
    settingsCategoriesPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
    settingsCategoriesPanel.setBackground(new Color(249, 250, 251));

    // Create individual setting sections
    settingsCategoriesPanel.add(createSettingsSection("Budget Preferences", 
        new String[]{"Default Budget Allocation", "Spending Alerts", "Budget Rollover"}));
    settingsCategoriesPanel.add(Box.createVerticalStrut(10));
    settingsCategoriesPanel.add(createSettingsSection("Appearance", 
        new String[]{"Theme Selection", "Color Scheme", "Font Size"}));
    settingsCategoriesPanel.add(Box.createVerticalStrut(10));
    settingsCategoriesPanel.add(createSettingsSection("Privacy & Security", 
        new String[]{"Data Export Settings", "Backup & Restore", "Reset Application"}));
    settingsCategoriesPanel.add(Box.createVerticalStrut(10));
    settingsCategoriesPanel.add(createSettingsSection("Notifications", 
        new String[]{"Budget Alerts", "Expense Reminders", "Monthly Summary"}));

    // Action Buttons Panel
    JPanel actionButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    actionButtonsPanel.setOpaque(false);

    JButton saveButton = createStyledButton("Save Settings", Color.WHITE, new Color(79, 70, 229));
    JButton resetButton = createStyledButton("Reset to Default", Color.WHITE, new Color(220, 38, 38));

    saveButton.addActionListener(e -> saveApplicationSettings());
    resetButton.addActionListener(e -> resetToDefaultSettings());

    actionButtonsPanel.add(saveButton);
    actionButtonsPanel.add(resetButton);

    // Assemble the entire panel
    panel.add(titlePanel, BorderLayout.NORTH);
    panel.add(new JScrollPane(settingsCategoriesPanel), BorderLayout.CENTER);
    panel.add(actionButtonsPanel, BorderLayout.SOUTH);

    return panel;
}

private JPanel createSettingsSection(String sectionTitle, String[] settings) {
    JPanel sectionPanel = new JPanel(new BorderLayout(10, 10));
    sectionPanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(229, 231, 235), 1),
        new EmptyBorder(15, 15, 15, 15)
    ));
    sectionPanel.setBackground(Color.WHITE);

    // Section Title
    JLabel sectionTitleLabel = new JLabel(sectionTitle);
    sectionTitleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
    sectionTitleLabel.setForeground(new Color(17, 24, 39));
    sectionPanel.add(sectionTitleLabel, BorderLayout.NORTH);

    // Settings List
    JPanel settingsListPanel = new JPanel();
    settingsListPanel.setLayout(new BoxLayout(settingsListPanel, BoxLayout.Y_AXIS));
    settingsListPanel.setBackground(Color.WHITE);

    for (String setting : settings) {
        JPanel settingRowPanel = new JPanel(new BorderLayout());
        settingRowPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        settingRowPanel.setBackground(Color.WHITE);

        JLabel settingLabel = new JLabel(setting);
        settingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        JToggleButton toggleButton = new JToggleButton("Off");
        




        toggleButton.setPreferredSize(new Dimension(70, 30));
            toggleButton.setBackground(Color.LIGHT_GRAY);
            toggleButton.setForeground(Color.WHITE);
    
            toggleButton.addActionListener(e -> {
                if (toggleButton.isSelected()) {
                    toggleButton.setText("On");
                    toggleButton.setBackground(new Color(79, 70, 229));
                } else {
                    toggleButton.setText("Off");
                    toggleButton.setBackground(Color.LIGHT_GRAY);
                }
            });
    
            settingRowPanel.add(settingLabel, BorderLayout.WEST);
            settingRowPanel.add(toggleButton, BorderLayout.EAST);
    
            settingsListPanel.add(settingRowPanel);
            settingsListPanel.add(Box.createVerticalStrut(10));
        }
    
        sectionPanel.add(settingsListPanel, BorderLayout.CENTER);
    
        return sectionPanel;
    }
    
    private JButton createStyledButton(String text, Color bgColor, Color fgColor) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isPressed()) {
                    g.setColor(getBackground().darker());
                } else {
                    g.setColor(getBackground());
                }
                g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 40, 40);
                super.paintComponent(g);
            }
        };
    
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(fgColor);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);
        button.setPreferredSize(new Dimension(150, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    
        // Hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(29, 255, 236)); // Hover color
            }
    
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(bgColor); // Original color
            }
        });
    
        return button;
    }
    
    private void saveApplicationSettings() {
        JOptionPane.showMessageDialog(this, 
            "Settings saved successfully!", 
            "Save Settings", 
            JOptionPane.INFORMATION_MESSAGE);
        // Placeholder for actual settings saving logic
    }
    
    private void resetToDefaultSettings() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to reset all settings to default?", 
            "Reset Settings", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, 
                "All settings have been reset to default.", 
                "Reset Complete", 
                JOptionPane.INFORMATION_MESSAGE);
            // Placeholder for actual reset logic
        }
    }