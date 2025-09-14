import javax.swing.*;

import java.awt.*;

import java.awt.event.ActionEvent;

import java.io.BufferedReader;

import java.io.InputStreamReader;


public class FakeNewsDetector{

    
public static void main(String[] args) {
        // Create frame
        
JFrame frame = new JFrame("Fake News Detector");
        
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
frame.setSize(600, 400);
        
frame.setLayout(new BorderLayout(10, 10));

        // Text area
        
JTextArea inputArea = new JTextArea("Enter news content here...");
        
inputArea.setLineWrap(true);
        
inputArea.setWrapStyleWord(true);
        
JScrollPane scrollPane = new JScrollPane(inputArea);

        // Button
        
JButton checkButton = new JButton("Check News");

        // Result label
        
JLabel resultLabel = new JLabel("Prediction will appear here");
        
resultLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Button action
        
checkButton.addActionListener((ActionEvent e) -> {
            
String newsText = inputArea.getText().trim();
            
try {
    // Run Python script with input
    
ProcessBuilder pb = new ProcessBuilder("python", "predict.py.py", newsText);
    
pb.redirectErrorStream(true);  // Merge stderr with stdout
    
Process process = pb.start();

    // Read output from Python
    
BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
    
StringBuilder fullOutput = new StringBuilder();
    
String line;
    
String result = "";

    
while ((line = reader.readLine()) != null) {
        
System.out.println("Python Output: " + line);
        
fullOutput.append(line).append("\n");
        
        // Try to set the result only once, based on first non-error line
        
if (result.isEmpty() && !line.toLowerCase().contains("error") && !line.toLowerCase().contains("traceback")) {
            
result = line.trim();
        
}
    
}

    
int exitCode = process.waitFor();
    
System.out.println("Python exited with code: " + exitCode);

    
if (!result.isEmpty()) {
        
resultLabel.setText("Prediction: " + result.toUpperCase());
    
} 
else {
        
resultLabel.setText("Prediction failed. Output:\n" + fullOutput.toString());
    
}


} catch (Exception ex) {
    
resultLabel.setText("Error: " + ex.getMessage());
    
ex.printStackTrace();
}      
});

        // Layout setup
        
JPanel bottomPanel = new JPanel(new BorderLayout());
        
bottomPanel.add(checkButton, BorderLayout.CENTER);
        
bottomPanel.add(resultLabel, BorderLayout.SOUTH);

        
frame.add(scrollPane, BorderLayout.CENTER);
        
frame.add(bottomPanel, BorderLayout.SOUTH);

        // Show frame
        
frame.setVisible(true);
    
}

}