package Modelo;

import java.sql.Date;

public class Prestamo {
    private int id_prestamo;
    private int nro_cliente;
    private double monto_total;
    private Date fecha_inicio;
    private int cantidad_cuotas;

    public Prestamo() {}
    public Prestamo(int id, int cliente, double monto, Date fecha, int cuotas) {
        this.id_prestamo = id;
        this.nro_cliente = cliente;
        this.monto_total = monto;
        this.fecha_inicio = fecha;
        this.cantidad_cuotas = cuotas;
    }

    public int getId_prestamo() { return id_prestamo; }
    public void setId_prestamo(int id_prestamo) { this.id_prestamo = id_prestamo; }

    public int getNro_cliente() { return nro_cliente; }
    public void setNro_cliente(int nro_cliente) { this.nro_cliente = nro_cliente; }

    public double getMonto_total() { return monto_total; }
    public void setMonto_total(double monto_total) { this.monto_total = monto_total; }

    public Date getFecha_inicio() { return fecha_inicio; }
    public void setFecha_inicio(Date fecha_inicio) { this.fecha_inicio = fecha_inicio; }

    public int getCantidad_cuotas() { return cantidad_cuotas; }
    public void setCantidad_cuotas(int cantidad_cuotas) { this.cantidad_cuotas = cantidad_cuotas; }
}
