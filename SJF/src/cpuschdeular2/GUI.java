package cpuschdeular2;


import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

public class GUI
{ private 	 JFrame frame1;
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel mainPanel2;
    private CustomPanel chartPanel;
    private JScrollPane tablePane;
    private JScrollPane chartPane;
    private JTable table;
    private JButton addBtn;
    private JButton removeBtn;
    private JButton computeBtn;

    private JTextField wtLabel;
    private JTextField  througLabel;
    private JTextField THResultLabel;
    private JTextField wtResultLabel;
    private JTextField Complabel;
    private JTextField  tatLabel;
    private JTextField  tatResultLabel;
private JTextField  CompResultLabel;
    private  JCheckBox option;
    private DefaultTableModel model;
    private JLabel DOne;
    private  JLabel Done;
        XYChart.Series series1;
    String Process;
            int nextStart =0;

    public GUI() throws IOException
    {

        model = new DefaultTableModel(new String[]{"Process", "ArivalTime", "BurstTime", "Completion Time", " Wating Time   ", "TurnAroundTime"}, 0);

        table = new JTable(model);
        
        table.setModel(model);
  
        
               int[] columnsWidth = {
            155,  155, 155, 155, 155, 155
        };
                 int i = 0;
        for (int width : columnsWidth) {
          TableColumn column = table.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }
        
           table.setForeground(Color.BLACK);
               table.setFont(new Font("Tahome", Font.BOLD, 15));

        JScrollPane pane = new JScrollPane(table);
JTableHeader header = table.getTableHeader();
  header.setBackground(Color.decode("#ffecb8"));
  header.setForeground(Color.BLACK);
  header.setFont(new Font("Tahome", Font.BOLD, 14));
         
        tablePane = new JScrollPane(table);
                table.setFillsViewportHeight(true);
        tablePane.setBounds(60, 45, 920, 260);
        
        addBtn = new JButton("Add");
        addBtn.setBackground(Color.decode("#f7cac9"));
        addBtn.setForeground(Color.WHITE);
        addBtn.setBounds(550, 310, 110, 40);
       
        addBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        
        addBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                model.addRow(new String[]{"", "", "", "", "", ""});
               
            } 
        });
        
        removeBtn = new JButton("Delete");
        
        removeBtn.setBounds(440, 310, 105, 40);
        
        removeBtn.setForeground(Color.WHITE);
        
        removeBtn.setBackground(Color.decode("#a2b5af"));
        
        removeBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        
        removeBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                
                if (row > -1) {
                    model.removeRow(row);
                }
            }
        });
        
    Done = new JLabel("");
    Done.setText("Done By Hala Azmi && ");
             DOne =new JLabel("");
             DOne.setText("Abderraouf Drine  :) ");
Done.setBounds(240,758, 150, 100);
DOne.setBounds(368,758,150,100);
        chartPanel = new CustomPanel();
//        chartPanel.setPreferredSize(new Dimension(700, 10));
        chartPanel.setBackground(Color.WHITE);
        chartPane = new JScrollPane(chartPanel);
        
        chartPane.setBounds(60, 360, 820, 180);

        wtLabel = new JTextField ("Average Waiting Time:");
        
        wtLabel.setBounds(199,545, 190, 25);
                wtLabel.setFont(new Font("Arial", Font.BOLD, 15));

              wtResultLabel = new JTextField("0.0");
        wtResultLabel.setBounds(400,545, 180, 25);
wtResultLabel.setFont(new Font("Arial", Font.BOLD, 15));

        tatLabel = new JTextField("Average Turn Around:");
        tatLabel.setBounds(199, 656, 190, 25);
        tatLabel.setFont(new Font("Arial", Font.BOLD, 15));
       
        
        tatResultLabel = new JTextField("0.0");
        tatResultLabel.setBounds(400, 656,180, 25);
         tatResultLabel.setFont(new Font("Arial", Font.BOLD, 15));

        
        option = new JCheckBox("SJF",null);

        option.setFont(new Font("Segoe UI", Font.BOLD, 25));
        option.setBounds(680, 600, 90, 40);
        
        computeBtn = new JButton("RUN");
        computeBtn.setBackground(Color.decode("#b88c8c"));
        computeBtn.setForeground(Color.WHITE);
        computeBtn.setBounds(680, 550,110, 40);
        computeBtn.setFont(new Font("Segoe UI", Font.BOLD, 15));

        computeBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String) option.getText();
                CPUScheduler scheduler;
                
                switch (selected) {
                    case "SJF":
                        scheduler = new ShortestJobFirst();
                        break;
                 
                    default:
                        return;
                }
      
                for (int i = 0; i < model.getRowCount(); i++)
                {
                    String process = (String) model.getValueAt(i, 0);
                    int at = Integer.parseInt((String) model.getValueAt(i, 1));
                    int bt = Integer.parseInt((String) model.getValueAt(i, 2));
                
                                        
                    scheduler.add(new Row(process, at, bt));
                
                }
                
                scheduler.process();
                
                for (int i = 0; i < model.getRowCount(); i++)
                {
                    String process = (String) model.getValueAt(i, 0);
                    Row row = scheduler.getRow(process);
                    model.setValueAt(row.getWaitingTime(), i, 4);
                    model.setValueAt(row.getTurnaroundTime(), i, 5);
                

                }
       

                wtResultLabel.setText(Double.toString(scheduler.getAverageWaitingTime()));
                tatResultLabel.setText(Double.toString(scheduler.getAverageTurnAroundTime()));
                
                chartPanel.setTimeline(scheduler.getTimeline());
            }
        });
        

	
        mainPanel = new JPanel(null);
        mainPanel.setPreferredSize(new Dimension(990, 820));
        mainPanel.add(DOne);
        mainPanel.add(Done);
        mainPanel.add(tablePane);
        mainPanel.add(addBtn);
        mainPanel.add(removeBtn);
        mainPanel.add(chartPane);
        mainPanel.add(wtLabel);
        mainPanel.add(tatLabel);
        mainPanel.add(wtResultLabel);
        mainPanel.add(tatResultLabel);
        mainPanel.add(option);
        mainPanel.add(computeBtn);
        mainPanel.setBackground( Color.decode("#b7ded2"));

        frame = new JFrame(" CPU Scheduler ** SFJ ** ");
  BorderPane border = new BorderPane();


frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
JMenuBar menubar = new JMenuBar();
JMenu menu = new JMenu("More");
JMenuItem size = new JMenuItem("AboutUs");
    size.addActionListener(new MenuActionListener());

menu.add(size);
menubar.add(menu);
  frame.setJMenuBar(menubar);

        frame.setVisible(true);
        frame.setResizable(true);
        frame.add(mainPanel);
        frame.pack();
Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\Admin\\CpuSchdeular2\\src\\cpuschdeular2\\images.png");  
frame.setIconImage(icon);  
          frame.setVisible(true);

         
    
        
    }
        class MenuActionListener implements ActionListener {
  @Override
  public void actionPerformed(ActionEvent e) {
  e.getActionCommand();
  ABOUTUS about=new ABOUTUS();
about.setVisible(true); 

  }
}

    
    public static void main(String[] args) throws IOException
    {
        new GUI();}
   
    
    class CustomPanel extends JPanel
    {   
        private List<Event> timeline;
        
        @Override
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            
            if (timeline != null)
            {
//                int width = 30;
                
                for (int i = 0; i < timeline.size(); i++)
                {
                            	                       
                  

                    Event event = timeline.get(i);
                    int x = 30 * (i + 1);
                    int y = 20;
                    
                

                    g.drawRect(x, y, 30, 30);
                    g.setFont(new Font("Segoe UI", Font.BOLD, 18  ));


                    g.drawString(event.getProcessName(), x + 10, y + 20);
                    g.setFont(new Font("Segoe UI", Font.PLAIN, 18));
                    g.drawString(Integer.toString(event.getStartTime()), x - 5, y + 45);
                    
                    if (i == timeline.size() - 1)
                    {
                        g.drawString(Integer.toString(event.getFinishTime()), x + 27, y + 45);
                    }
                    
                }
                

                           }
        }
        
	
        
        public void setTimeline(List<Event> timeline)
        {
            this.timeline = timeline;
            repaint();
        }
    }
}
