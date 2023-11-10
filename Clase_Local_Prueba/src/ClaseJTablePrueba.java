import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

public class ClaseJTablePrueba extends JFrame	{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	JTextField tfCampo = new JTextField(15);
	public ClaseJTablePrueba() {
			setSize(640,420);
			setLocationRelativeTo(null);
			setTitle(this.getClass().getSimpleName());
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			
			
			ArrayList<Persona> personas = new ArrayList<>();
			
			Persona persona1 = new Persona("Nombre1", "Apellido1" , LocalDate.of(1900, 1, 30),1,2);
			Persona persona2 = new Persona("Nombre2", "Apellido2" , LocalDate.of(1900, 1, 30),2,3);
			Persona persona3 = new Persona("Nombre3", "Apellido3" , LocalDate.of(1900, 1, 30),4,9);
			
			personas.add(persona1);
			personas.add(persona2);
			personas.add(persona3);
			
			class MyTableModel extends AbstractTableModel {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				
				private List<Persona> personas;
				private String[] cabeceras = {"Nombre","Apellidos","Fecha de Nacimiento","Goles" , "Asistencias"};
				
				public MyTableModel(List<Persona> personas) {
					this.personas = personas;
					System.out.println(personas.size());
				}
				
				@Override
				public int getColumnCount() {
					// TODO Auto-generated method stub
					return cabeceras.length;
				}
				
				@Override
				public String getColumnName(int column) {
					// TODO Auto-generated method stub
					return cabeceras[column];
				}
				
				@Override
				public int getRowCount() {
					return personas.size();
				}
				
				@Override
				public Object getValueAt(int row, int column) {
					switch(column) {
					case 0 : {return personas.get(row).getNombre();}
					case 1 : {return personas.get(row).getApellidos();}
					default : {return personas.get(row).getFechaNacimiento();}
					}
				}
				
				@Override
				public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
					switch(columnIndex) {
					case 0 : {personas.get(rowIndex).setNombre(aValue.toString());break;}
					case 1 : {personas.get(rowIndex).setApellidos(aValue.toString());break;}
					default : {
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
							LocalDate date = LocalDate.parse(aValue.toString(), formatter);
							personas.get(rowIndex).setFechaNacimiento(date);
						}
					}
					fireTableCellUpdated(rowIndex, columnIndex);
				}
				
				@Override
				public boolean isCellEditable(int rowIndex, int columnIndex) {
					// TODO Auto-generated method stub
				return columnIndex == 0;
				}
			
			}
			
			class MyRenderer extends JLabel implements TableCellRenderer {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					System.out.println(value.getClass().getSimpleName());
					setText(value.toString());
					if(column==0 &&value.toString().startsWith(tfCampo.getText()) && !tfCampo.getText().isBlank()) setBackground(Color.BLUE);
				
					else setBackground(Color.white);
					setOpaque(true);
					return this;
				}
				 
			}
			
			JTable tabla = new JTable(new MyTableModel(personas));
			tabla.setDefaultRenderer(Object.class, new MyRenderer());
			JScrollPane scrollPane = new JScrollPane(tabla);
			JButton btImprimir = new JButton("Imprimir");
			
			JPanel panelDeAbajo = new JPanel();
			
			
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			
			add(scrollPane);
			add(panelDeAbajo, BorderLayout.SOUTH);
			panelDeAbajo.add(btImprimir);
			panelDeAbajo.add(tfCampo);
			
			btImprimir.addActionListener(e->{
				for(Persona p : personas) {
					System.out.println(p);
				}
			});
			
			tfCampo.addActionListener(e->{
				repaint();
			});
			
			setVisible(true);
			
	}
	

	public static void main(String[] args) {
		ClaseJTablePrueba vent = new ClaseJTablePrueba();
			}
	
	
	
	
}
