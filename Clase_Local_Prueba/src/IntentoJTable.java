import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class IntentoJTable extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField buscando = new JTextField(10);
	private boolean estilo = false;
	public IntentoJTable() {
		setSize(600,420);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		
		ArrayList<Persona> personas = new ArrayList<>();
		JComboBox<String> comboBox = new JComboBox<>();
		
		
		
		comboBox.addItem("Soltero");
		comboBox.addItem("Casado");
		comboBox.addItem("Hablando");
		comboBox.addItem("Cansado");
		comboBox.addItem("Todas mentirosas");
		
		Persona persona1 = new Persona("Victor", "Redondo", LocalDate.of(2002, 10, 4),1,2);
		Persona persona2 = new Persona("Iker", "De Castro", LocalDate.of(2002, 12, 9),3,4);
		Persona persona3 = new Persona("Alvaro", "Quintanar", LocalDate.of(2002, 2, 5),2,3);
		
		personas.add(persona1);
		personas.add(persona2);
		personas.add(persona3);
		
		class MyTableModel extends AbstractTableModel{
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			String[] cabeceros = {"Nombre" , "Apellido" , "Fecha de nacimiento","Disponibilidad","Goles" , "Asistencias","Ratio G/A" };
			List < Persona > personas ;
			
			public MyTableModel ( List<Persona> personas) {
				this.personas = personas;
			}
			
			@Override
			public int getRowCount() {
				
				return personas.size();
			}

			@Override
			public int getColumnCount() {
				return cabeceros.length;
				
			}
			
			public String getColumnName(int column) {
				return cabeceros[column];
			}

			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
			    switch (columnIndex) {
			        case 0: return personas.get(rowIndex).getNombre();
			        case 1: return personas.get(rowIndex).getApellidos();
			        case 2: return personas.get(rowIndex).getFechaNacimiento();
			        case 3: return personas.get(rowIndex).getEStadoCivil();
			        case 4: return personas.get(rowIndex).getGoles();
			        case 5: return personas.get(rowIndex).getAsistencias();
			        case 6: // Nueva columna para la barra de progreso
			        	JProgressBar barra = new JProgressBar(0,100);
			        	barra.setStringPainted(true);
			            int goles = personas.get(rowIndex).getGoles();
			            int asistencias = personas.get(rowIndex).getAsistencias();
			            barra.setValue((asistencias > 0) ? (int) ((float) goles / asistencias * 100) : 0);
			            return barra;
			        default: return null;
			    }
			}

			
			
			@Override
			public void setValueAt(Object object,int rowIndex, int columnIndex) {
				switch(columnIndex) {
				case 0: { personas.get(rowIndex).setNombre(object.toString());break;}
				case 1:{personas.get(rowIndex).setApellidos(object.toString());break;}
				case 2:{
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
					LocalDate date = LocalDate.parse(object.toString(), formatter);
					personas.get(rowIndex).setFechaNacimiento(date);
					
					}
				case 3:
				case 4: { personas.get(rowIndex);}
				case 5: { personas.get(rowIndex);}
				default: { personas.get(rowIndex).setEstadoCivil(object.toString());}
				}
				fireTableCellUpdated(rowIndex, columnIndex);
			}
			
			
			
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex){
				return columnIndex == 0 || columnIndex == 3;
			}
			
			
			
		}
		
		class MyTableRenderer extends JLabel implements TableCellRenderer{

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
					boolean hasFocus, int row, int column) {
				setText(value.toString());
				if( table.getValueAt(row, 0).toString().startsWith(buscando.getText()) && !buscando.getText().isBlank()) {
					this.setBackground(Color.CYAN);
				}else {
					this.setBackground(Color.WHITE);
				}
				setOpaque(true);
				return this;
			}
			
			@Override
			public boolean equals(Object obj) {
				// TODO Auto-generated method stub
				return true;
			}
		}
			
			class MyRenderer extends JLabel implements TableCellRenderer{
			
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
						boolean hasFocus, int row, int column) {
					setText(value.toString());
					if((column==0 ) && value.toString().startsWith(buscando.getText()) && !buscando.getText().isBlank()) {
					
						this.setBackground(Color.GREEN);
						
					}else {
						
						this.setBackground(Color.WHITE);
					}
					
					setOpaque(true);
					return this;
					
					
				}
				
				
		}
		
			
			
			
		JTable tabla = new JTable( new MyTableModel(personas));
		tabla.setDefaultRenderer(Object.class, new MyRenderer());
		JScrollPane scrollPane = new JScrollPane(tabla);
		JButton bImprimir = new JButton("Imprimir");
		JPanel pInferior = new JPanel();
		JPanel pBusqueda = new JPanel();
		JButton cambiarEstilo = new JButton("Cambiar estilo");
		JLabel lBuscador = new JLabel("Buscador: ");
		TableCellEditor editor = new DefaultCellEditor(comboBox);
		TableColumn column = tabla.getColumnModel().getColumn(3);
		column.setCellEditor(editor);
		pBusqueda.add(lBuscador);
		pBusqueda.add(buscando);

		
		
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		
		pInferior.add(bImprimir);
		pInferior.add(cambiarEstilo);
		
		this.add(scrollPane);
		this.add(pInferior,BorderLayout.SOUTH);
		this.add(pBusqueda,BorderLayout.WEST);
		
		
		setVisible(true);
		
		bImprimir.addActionListener(e->{
			for(Persona nueva : personas) {
				System.out.println(nueva);
			}
		});
		
		buscando.addActionListener(e->{
			repaint();
		});
		
		cambiarEstilo.addActionListener(e->{
			if(estilo == false) {
				tabla.setDefaultRenderer(Object.class, new MyRenderer());
				estilo=!estilo;
			}else {
				estilo=!estilo;
				tabla.setDefaultRenderer(Object.class, new MyTableRenderer());
			}
			repaint();
			
		});
		
		}

		public static void main(String[] args) {
			IntentoJTable venta = new IntentoJTable();
			venta.pack();
		}
		
		
	
}
