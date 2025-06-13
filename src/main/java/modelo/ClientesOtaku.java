package modelo;

import java.time.LocalDate;

public class ClientesOtaku {
    private int id;            
    private String nombre;   
    private String email;     
    private int telefono;    
    private LocalDate fecha;  

    // Constructor con parámetros
    public ClientesOtaku(String nombre, String email, int telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    // Constructor vacío
    public ClientesOtaku() {}

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    // ToString: Para mostrar información del cliente
    @Override
    public String toString() {
        return "Clientes con id: " + id + ", nombre:" + nombre + ", email:" + email + ", teléfono:" + telefono;
    }
}
