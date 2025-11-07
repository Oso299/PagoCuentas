package Modelo;

public class Cliente {
    private int nro_cliente;
    private String nombre;
    private String direccion;
    private String telefono;

    // Constructores
    public Cliente() {}
    public Cliente(int nro_cliente, String nombre, String direccion, String telefono) {
        this.nro_cliente = nro_cliente;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Getters y Setters
    public int getNro_cliente() { return nro_cliente; }
    public void setNro_cliente(int nro_cliente) { this.nro_cliente = nro_cliente; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
