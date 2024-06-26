/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

//Importan Librerias, principalmente la de Maestros
import controlador.Pacientes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author kenph
 */
//Creacion de la clase Maestros Dao y los parametros de la base de datos
public class PacientesDAO {
 
    private static final String SQL_SELECT = "SELECT codigo_paciente, nombre_paciente, apellido_paciente,  naciemiento_paciente, genero_paciente, direccion_paciente, telefono_paciente, estatus_paciente FROM pacientes";
    private static final String SQL_INSERT = "INSERT INTO pacientes(codigo_paciente, nombre_paciente, apellido_paciente, nacimineto_paciente, genero paciente, direccion_paciente, telefono_paciente, estatus_paciente) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE pacientes SET nombre_paciente=?, apellido_paciente=?, nacimiento_paciente=?, genero_paciente=?, direccion_paciente=?, telefono_paciente=?, estatus_paciente=? WHERE codigo_paciente = ?";
    private static final String SQL_DELETE = "DELETE FROM pacientes WHERE codigo_paciente=?";
    private static final String SQL_QUERY = "SELECT codigo_paciente, nombre_paciente, apellido_paciente, nacimiento_paciente, genero_paciente, direccion_paciente, telefono_paciente, estatus_paciente FROM pacientes WHERE codigo_paciente = ?";
    
    public List<Pacientes> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Pacientes paciente = null;
        List<Pacientes> pacientes = new ArrayList<Pacientes>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                String codigo = rs.getString("codigo_paciente");
                String nombre = rs.getString("nombre_paciente");
                String apellido = rs.getString("nombre_apellido");
                String nacimiento = rs.getString("nombre_nacimiento");
                String genero = rs.getString("genero_paciente");
                String direccion = rs.getString("direccion_paciente");
                String telefono = rs.getString("telefono_paciente");
                String estatus = rs.getString("estatus_paciente");
                
                paciente.setCodigo_paciente(codigo);
                paciente.setNombre_paciente(nombre);
                paciente.setApellido_paciente(apellido);
                paciente.setNacimiento_paciente(nacimiento);
                paciente.setDireccion_paciente(direccion);
                paciente.setGenero_paciente(genero);
                paciente.setDireccion_paciente(direccion);
                paciente.setTelefono_paciente(telefono);
                paciente.setEstatus_paciente(estatus);
                
                pacientes.add(paciente);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return pacientes;
    }
    
    public int insert(Pacientes paciente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, paciente.getCodigo_paciente());
            stmt.setString(2, paciente.getNombre_paciente());
            stmt.setString(3, paciente.getApellido_paciente());
            stmt.setString(4, paciente.getNacimiento_paciente());
            stmt.setString(5, paciente.getGenero_paciente());
            stmt.setString(6, paciente.getDireccion_paciente());
            stmt.setString(7, paciente.getTelefono_paciente());
            stmt.setString(8, paciente.getEstatus_paciente());
            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }
    
    public int update(Pacientes paciente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("ejecutando query: " + SQL_UPDATE);
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, paciente.getCodigo_paciente());
            stmt.setString(2, paciente.getNombre_paciente());
            stmt.setString(3, paciente.getApellido_paciente());
            stmt.setString(4, paciente.getNacimiento_paciente());
            stmt.setString(5, paciente.getGenero_paciente());
            stmt.setString(6, paciente.getDireccion_paciente());
            stmt.setString(7, paciente.getTelefono_paciente());
            stmt.setString(8, paciente.getEstatus_paciente());
            rows = stmt.executeUpdate();
            System.out.println("Registros actualizado:" + rows);

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }
    
    public int delete(Pacientes paciente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_DELETE);
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setString(1, paciente.getCodigo_paciente());
            rows = stmt.executeUpdate();
            System.out.println("Registros eliminados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }
    
    public Pacientes query(Pacientes paciente) {    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            System.out.println("Ejecutando query:" + SQL_QUERY);
            stmt = conn.prepareStatement(SQL_QUERY);
            stmt.setString(1, paciente.getCodigo_paciente());
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                String codigo = rs.getString("codigo_paciente");
                String nombre = rs.getString("nombre_paciente");
                String apellido = rs.getString("apellido_paciente");
                String nacimiento = rs.getString("nacimiento_paciente");
                String direccion = rs.getString("direccion_paciente");
                String genero = rs.getString("ganero_paciente");
                String telefono = rs.getString("telefono_paciente");
                String estatus = rs.getString("estatus_paciente");
                
                paciente = new Pacientes();
                paciente.setCodigo_paciente(codigo);
                paciente.setNombre_paciente(nombre);
                paciente.setApellido_paciente(apellido);
                paciente.setNacimiento_paciente(nacimiento);
                paciente.setDireccion_paciente(direccion);
                paciente.setGenero_paciente(genero);
                paciente.setTelefono_paciente(telefono);
                paciente.setEstatus_paciente(estatus);
        
            }
            //System.out.println("Registros buscado:" + vendedor);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return paciente;
    }
}
