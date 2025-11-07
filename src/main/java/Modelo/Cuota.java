package Modelo;

import java.sql.Date;

public class Cuota {
    private int id_cuota;
    private int id_prestamo;
    private int nro_cuota;
    private Date fecha_pago;
    private double monto;
    private String estado;

    public Cuota() {}

    public Cuota(int id_cuota, int id_prestamo, int nro, Date fecha, double monto, String estado) {
        this.id_cuota = id_cuota;
        this.id_prestamo = id_prestamo;
        this.nro_cuota = nro;
        this.fecha_pago = fecha;
        this.monto = monto;
        this.estado = estado;
    }

    public int getId_cuota() { return id_cuota; }
    public void setId_cuota(int id_cuota) { this.id_cuota = id_cuota; }

    public int getId_prestamo() { return id_prestamo; }
    public void setId_prestamo(int id_prestamo) { this.id_prestamo = id_prestamo; }

    public int getNro_cuota() { return nro_cuota; }
    public void setNro_cuota(int nro_cuota) { this.nro_cuota = nro_cuota; }

    public Date getFecha_pago() { return fecha_pago; }
    public void setFecha_pago(Date fecha_pago) { this.fecha_pago = fecha_pago; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
